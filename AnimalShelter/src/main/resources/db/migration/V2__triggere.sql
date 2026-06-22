create or replace function check_angajat_este_medic()
returns trigger as $$
declare
p_functie functie.nume_functie%type;
begin
 select f.nume_functie into p_functie
 from angajat a
 join functie f on a.id_functie=f.id_functie
 where a.id_angajat= new.id_angajat;

 if p_functie not ilike '%medic%' then
  raise exception 'Angajatutl cu id=% nu este medic, ci %',new.id_angajat, p_functie;
 end if;
 return new;
end;
$$ language plpgsql; --anunta compilatorul ca este PL/SQL

create trigger trg_validare_medic
before insert or update on interventie_medicala
for each row
execute function check_angajat_este_medic();

create or replace function update_status_animal()
returns trigger as $$
begin
 if tg_op='INSERT' then --tg_op variabila de sistem ca new
   update animal set status='adoptat' where id_animal=new.id_animal;
 elsif tg_op='UPDATE' and new.data_returnare is not null then --pt o anumita adoptie, se retine data de returnare
  update animal set status='adapost' where id_animal=new.id_animal;
 end if;
 return new;
end;
$$ language plpgsql;

create trigger trg_update_status_adoptie
after insert or update on adoptie
for each row execute function update_status_animal();

create or replace function check_capacitate_cusca()
returns trigger as $$
declare
 nr_animale_curente int;
 cap_max int;
begin
 select capacitate_maxima into cap_max from cusca where id_cusca=new.id_cusca;
 select count(*) into nr_animale_curente from animal where id_cusca=new.id_cusca and status in ('adapost','clinica');

 if nr_animale_curente>=cap_max then
  raise exception 'Cusca % este plina, capacitate max=%',new.id_cusca, cap_max;
 end if;
 return new;
end;
$$ language plpgsql;
create trigger trg_verificare_capacitate
before insert or update of id_cusca on animal
for each row execute function check_capacitate_cusca();

create or replace function check_specie()
returns trigger as $$
declare
 id_specie_animal int;
 id_specie_cusca int;
 specie_animal specie.nume%type;
 specie_cusca specie.nume%type;
begin
 select id_specie into id_specie_animal from rasa where id_rasa=new.id_rasa; --ne trebuie doar id ul speciei care este stocat in rasa
 select id_specie into id_specie_cusca from cusca where id_cusca=new.id_cusca;

 select nume into specie_animal from specie where id_specie=id_specie_animal;
 select nume into specie_cusca from specie where id_specie=id_specie_cusca;
 if id_specie_animal!= id_specie_cusca then
  raise exception 'Incompatibilitate specie animal-cusca, animalul este de specia %, iar cusca tine specia %',specie_animal, specie_cusca;
 end if;
 return new;
end;
$$ language plpgsql;

create trigger trg_verificare_specie
before insert or update on animal
for each row
execute function check_specie();