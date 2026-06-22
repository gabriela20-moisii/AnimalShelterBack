create or replace view statistici_adoptie_adapost as
    select
        c.id_adapost,
        r.id_rasa,
        r.nume as nume_rasa,
        s.nume as nume_specie,
        count(a.id_animal) AS total_animale_intrate,
        count(ad.id_adoptie) AS total_adoptii_realizate,
        coalesce(
                round((count(ad.id_adoptie)::numeric / NULLIF(COUNT(a.id_animal), 0)) * 100, 2),
                0
        ) AS rata_adoptie_procent,
        COALESCE(
                ROUND(AVG(ad.data_adoptie - a.data_intrare),0),
                0
        ) AS timp_mediu_asteptare_zile
    FROM animal a
             JOIN cusca c ON a.id_cusca = c.id_cusca
             JOIN rasa r ON a.id_rasa = r.id_rasa
             JOIN specie s ON r.id_specie = s.id_specie
             LEFT JOIN adoptie ad ON a.id_animal = ad.id_animal
    GROUP BY c.id_adapost, r.id_rasa, r.nume, s.nume;
