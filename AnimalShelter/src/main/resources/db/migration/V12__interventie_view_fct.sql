create or replace view view_istoric_medical as
    select a.nume AS nume_medic,
           a.prenume AS prenume_medic,
           t.denumire_interventie AS tip_interventie,
           i.observatii,
           i.data,
           f.id_animal -- ca sa putem cauta interventia in  fct de anima;
from interventie_medicala i
join angajat a on i.id_angajat=a.id_angajat
join tip_interventie t on i.id_tip_interventie=t.id_tip_interventie
join fisa_medicala f on i.id_fisa=f.id_fisa;

-- trb scrisa fct de adaugare animal
create or replace function adauda_interventie(
    p_nume_medic angajat.nume%type,
    p_prenume_medic angajat.prenume%type,
    p_denumire_interventie tip_interventie.denumire_interventie%type,
    p_observatii TEXT,
    p_data DATE,
    p_id_animal INT
) returns int as $$
    declare
        v_id_fisa int;
        v_id_medic int;
        v_id_tip int;
        v_id_interv int;
        v_id_adapost int;
    begin
        select id_fisa into v_id_fisa from fisa_medicala where id_animal=p_id_animal;
        if not found then
            raise exception 'Animalul cu id=% nu exista', p_id_animal;
        end if;
        select ad.id_adapost into v_id_adapost from adapost ad
        join cusca c on c.id_adapost=ad.id_adapost
        join animal a on a.id_cusca=c.id_cusca
        where id_animal=p_id_animal;
        select id_angajat into v_id_medic from angajat where trim(lower(nume))=trim(lower(p_nume_medic)) and trim(lower(prenume))=trim(lower(p_prenume_medic)) and id_adapost=v_id_adapost;
        -- pt a evita erorile pt cazul in care exista 2 medici cu acelasi nume => il vom lua pe cel din acelasi adapost cu animalul
        if not found then
            raise exception 'Medicul % % nu exista in acelasi adapost cu animalutul', p_nume_medic, p_prenume_medic;
        end if;
        select id_tip_interventie into v_id_tip
        from tip_interventie
        where TRIM(LOWER(denumire_interventie)) = TRIM(LOWER(p_denumire_interventie));
        if not found then
            raise exception 'Inerventia nu exista, verificati typo';
        end if;
        INSERT INTO interventie_medicala (data, id_fisa, id_tip_interventie, observatii, id_angajat)
        VALUES (COALESCE(p_data, CURRENT_DATE), v_id_fisa, v_id_tip, p_observatii, v_id_medic) returning id_interventie into v_id_interv;
        return v_id_interv;
    end;
    $$ language plpgsql;
