CREATE OR REPLACE VIEW animal_detalii AS
SELECT
    a.id_animal,
    a.nume AS nume_animal,
    a.data_nastere,
    a.status,
    a.data_intrare,
    r.nume AS nume_rasa,
    s.nume AS nume_specie,
    c.cod_identificare AS cod_identificare_cusca
FROM animal a
         JOIN rasa r ON a.id_rasa = r.id_rasa
         JOIN specie s ON r.id_specie = s.id_specie
         JOIN cusca c ON a.id_cusca = c.id_cusca;

CREATE OR REPLACE VIEW adapost_detalii AS
SELECT
    a.id_adapost,
    a.nume AS nume_adapost,
    a.adresa,
    o.nume AS nume_oras,
    t.nume AS nume_tara
FROM adapost a
         JOIN oras o ON o.id_oras = a.id_oras
         JOIN tara t ON o.id_tara = t.id_tara;


CREATE OR REPLACE VIEW angajat_detalii AS
SELECT
    ang.id_angajat,
    ang.nume AS nume_angajat,
    ang.prenume,
    ang.telefon,
    ang.salariu,
    f.nume_functie AS functie,
    ad.nume_adapost,
    ad.adresa AS adresa_adapost,
    ad.nume_oras,
    ad.nume_tara
FROM angajat ang
         JOIN functie f ON f.id_functie = ang.id_functie
         JOIN adapost_detalii ad ON ad.id_adapost = ang.id_adapost;