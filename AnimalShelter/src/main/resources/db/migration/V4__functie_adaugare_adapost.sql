create or replace function adauga_adapost(
    p_nume adapost.nume%type,
    p_adresa adapost.adresa%type,
    p_oras varchar
) returns int as $$
declare
    v_id_oras int;
    v_id_adapost_nou int;
    r_specie record;
    v_counter int := 1;
begin
    select id_oras into v_id_oras from oras where nume ilike p_oras;
    if v_id_oras is null then
        raise exception 'Orasul % nu a fost gasit in sistem!', p_oras;
    end if;

    insert into adapost (nume, adresa, id_oras)
    values (p_nume, p_adresa, v_id_oras)
    returning id_adapost into v_id_adapost_nou;
    for r_specie in select id_specie from specie loop
            insert into cusca (cod_identificare, capacitate_maxima, id_adapost, id_specie)
            values ('c' || v_counter, 10, v_id_adapost_nou, r_specie.id_specie);
            v_counter := v_counter + 1;
        end loop;
    return v_id_adapost_nou;
end;
$$ language plpgsql;