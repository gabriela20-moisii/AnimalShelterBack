create or replace view istoric_adoptii as
    select a.id_animal, a.data_adoptie, a.data_returnare,a.motiv_returnare,
           ad.nume as nume_adoptator,ad.prenume as prenume_adoptator,
           ad.telefon as telefon_adoptator,ad.email as email_adoptator from adoptie a
join adoptator ad on a.id_adoptator = ad.id_adoptator;