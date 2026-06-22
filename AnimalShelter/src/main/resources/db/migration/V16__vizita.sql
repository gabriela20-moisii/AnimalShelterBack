
create or replace function adauga_vizita(
    p_id_adapost_sursa int,
    p_id_angajat int,
    p_oras oras.nume%type,
    p_motiv text,
    p_data date,
    p_durata int
) returns int as $$
    declare
    v_id_adapost_vizitat int;
    begin
        select a.id_adapost into v_id_adapost_vizitat
        from adapost a
                 join oras o on a.id_oras = o.id_oras
        where trim(lower(o.nume)) = trim(lower(p_oras));
        if not found then
            raise exception 'Nu exista niciun adapost in orasul %!', p_oras;
        end if;
        if p_id_adapost_sursa = v_id_adapost_vizitat then
            raise exception 'angajatul lucreaza deja in orasul!';
        end if;
        insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
        values (coalesce(p_data, current_date), p_motiv, p_id_angajat, v_id_adapost_vizitat, p_durata);
        return v_id_adapost_vizitat;
    end;
    $$ language plpgsql;
create or replace view view_istoric_vizite as
select
    v.id_vizita,
    v.id_angajat,
    a.nume as nume_angajat,
    v.data_vizita,
    v.durata_zile,
    v.motiv,
    ad.nume as nume_adapost_vizitat,
    o.nume as oras_vizitat
from vizita_angajat v
         join angajat a on v.id_angajat = a.id_angajat
         join adapost ad on v.id_adapost = ad.id_adapost
         join oras o on ad.id_oras = o.id_oras;

