DROP VIEW IF EXISTS angajat_detalii CASCADE;
DROP VIEW IF EXISTS adapost_detalii CASCADE;
DROP VIEW IF EXISTS animal_detalii CASCADE;
CREATE OR REPLACE VIEW animal_detalii AS
SELECT
    a.id_animal,
    a.nume AS nume_animal,
    a.data_nastere,
    a.status,
    a.data_intrare,

    a.id_rasa,
    r.nume AS nume_rasa,

    r.id_specie,
    s.nume AS nume_specie,

    a.id_cusca,
    c.cod_identificare AS cod_identificare_cusca,

    c.id_adapost,
    ad.nume AS nume_adapost
FROM animal a
         JOIN rasa r ON a.id_rasa = r.id_rasa
         JOIN specie s ON r.id_specie = s.id_specie
         JOIN cusca c ON a.id_cusca = c.id_cusca
         JOIN adapost ad ON c.id_adapost = ad.id_adapost;

CREATE OR REPLACE VIEW adapost_detalii AS
SELECT
    a.id_adapost,
    a.nume AS nume_adapost,
    a.adresa,

    a.id_oras,
    o.nume AS nume_oras,

    o.id_tara,
    t.nume AS nume_tara
FROM adapost a
         JOIN oras o ON o.id_oras = a.id_oras
         JOIN tara t ON o.id_tara = t.id_tara;


CREATE OR REPLACE VIEW angajat_detalii AS
SELECT
    ang.id_angajat,
    ang.id_adapost,
    ang.nume AS nume_angajat,
    ang.prenume,
    ang.telefon,
    ang.salariu,
    f.nume_functie AS functie,
    ad.nume_adapost,
    ad.adresa AS adresa_adapost,
    ad.nume_oras,
    ad.nume_tara,
    COALESCE(STRING_AGG(s.nume, ', '), null) as specializare
FROM angajat ang
         JOIN functie f ON f.id_functie = ang.id_functie
         JOIN adapost_detalii ad ON ad.id_adapost = ang.id_adapost
         LEFT JOIN specializare_angajat sa ON ang.id_angajat = sa.id_angajat -- left pt ang fara specializ
         LEFT JOIN specie s ON s.id_specie = sa.id_specie
GROUP BY
    ang.id_angajat, ang.nume, ang.prenume, ang.telefon, ang.salariu,
    f.nume_functie, ad.nume_adapost, ad.adresa, ad.nume_oras, ad.nume_tara;