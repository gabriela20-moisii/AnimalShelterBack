create or replace function proceseaza_transfer(
    p_id_animal int,
    p_id_adapost_sursa int,
    p_oras_dest oras.nume%type
) returns void as $$
declare
    v_id_adapost_destinatie int;
    v_id_cusca_noua int;
    v_id_specie int;
begin
    select a.id_adapost into v_id_adapost_destinatie
    from adapost a
             join oras o on a.id_oras = o.id_oras
    where trim(lower(o.nume)) = trim(lower(p_oras_dest));
    if not found then
        raise exception 'Nu exista niciun adapost in orasul %!', p_oras_dest;
    end if;
    if p_id_adapost_sursa = v_id_adapost_destinatie then
        raise exception 'Animalul se afla deja în adapostul din orasul %!', p_oras_dest;
    end if;
    select r.id_specie into v_id_specie
    from animal a
             join rasa r on a.id_rasa = r.id_rasa
    where a.id_animal = p_id_animal;

    select c.id_cusca into v_id_cusca_noua from cusca c
                                                    left join animal a on c.id_cusca = a.id_cusca and a.status='adapost' --animalul sa nu fie adpotat
    where c.id_adapost=v_id_adapost_destinatie and c.id_specie=v_id_specie
    group by c.id_cusca, c.capacitate_maxima
    having count(a.id_animal)<c.capacitate_maxima
    limit 1;
    if v_id_cusca_noua is null then
        raise exception 'Adapostul din % este plin!',p_oras_dest;
    end if;
    insert into istoric_transfer (data_transfer, id_animal, id_adapost_sursa, id_adapost_destinatie)
    values (current_date, p_id_animal, p_id_adapost_sursa, v_id_adapost_destinatie);
end;
$$ language plpgsql;

create or replace view view_istoric_transfer as
select
    t.id_animal,
    t.data_transfer,
    sursa.nume as nume_adapost_sursa,
    o_sursa.nume as oras_sursa,
    dest.nume as nume_adapost_destinatie,
    o_dest.nume as oras_destinatie
from istoric_transfer t
         join adapost sursa on t.id_adapost_sursa = sursa.id_adapost
         join oras o_sursa on sursa.id_oras = o_sursa.id_oras
         join adapost dest on t.id_adapost_destinatie = dest.id_adapost
         join oras o_dest on dest.id_oras = o_dest.id_oras;