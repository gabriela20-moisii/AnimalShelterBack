create or replace function adaugare_angajat_adapost(
    p_nume angajat.nume%type,
    p_prenume angajat.prenume%type,
    p_telefon angajat.telefon%type,
    p_salariu angajat.salariu%type,
    p_id_adapost int,
    p_functie functie.nume_functie%type
) returns int as $$
    declare
      v_id_functie int;
      v_id_angajat int;
    begin
        select id_functie into v_id_functie from functie where nume_functie=p_functie;
        if v_id_functie is null then
          raise exception 'Functia % nu exista', p_functie;
        end if;
        insert into angajat(nume,prenume,telefon,salariu,id_adapost,id_functie) values (p_nume,p_prenume,p_telefon,p_salariu,p_id_adapost, v_id_functie) returning id_angajat into v_id_angajat;
        return v_id_angajat;
end;
$$ language plpgsql;
