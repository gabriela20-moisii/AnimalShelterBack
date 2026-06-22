create or replace function add_animal_adapost(
    p_nume animal.nume%type,
    p_rasa rasa.nume%type,
    p_data_nastere animal.data_nastere%type,
    p_id_adapost int
) returns int as $$
declare
    v_id_animal int;
    v_id_rasa int;
    v_id_cusca int;
    v_id_specie int;
    v_adapost adapost.nume%type;
begin
    select id_rasa,id_specie into v_id_rasa, v_id_specie from rasa where nume ilike p_rasa;
    if v_id_rasa is null then
        raise exception 'Rasa % nu exista',p_rasa;
    end if;

    select c.id_cusca into v_id_cusca from cusca c
    left join animal a on c.id_cusca=a.id_cusca --facem left join pt a putea numara cate animale sunt intr-o cusca
    where c.id_adapost=p_id_adapost and c.id_specie=v_id_specie
    group by c.id_cusca, c.capacitate_maxima
    having count(a.id_animal)<c.capacitate_maxima
    order by c.id_cusca asc
    limit 1;

    if v_id_cusca is null then
        select nume into v_adapost from adapost where id_adapost=p_id_adapost;
        raise exception 'Nu exista locuri disponibile in adapostul % ', v_adapost;
    end if;

    insert into animal(nume,data_nastere,status,id_rasa,id_cusca, data_intrare) values (p_nume, p_data_nastere,'adapost',v_id_rasa,v_id_cusca,CURRENT_DATE) returning id_animal into v_id_animal;
    return v_id_animal;
end;
$$ language plpgsql;
