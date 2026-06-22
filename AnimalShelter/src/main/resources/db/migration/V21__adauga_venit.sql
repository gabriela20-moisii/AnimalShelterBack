DO $$
    DECLARE
        r_adapost RECORD;
    BEGIN
        FOR r_adapost IN SELECT id_adapost FROM adapost LOOP
                INSERT INTO venituri (suma, data_incasare, descriere, id_adapost)
                VALUES (
                           1000000.00,
                           current_date - interval '2 months',
                           'Subventie anuala de stat',
                           r_adapost.id_adapost
                       );
            END LOOP;

        RAISE NOTICE 'S-au alocat cate 100.000 RON pentru fiecare adapost!';
    END $$;