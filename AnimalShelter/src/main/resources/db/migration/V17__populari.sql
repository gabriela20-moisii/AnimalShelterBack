
insert into oras (nume, id_tara) values
                                     ('Bucuresti', (select id_tara from tara where nume = 'Romania')),
                                     ('Cluj', (select id_tara from tara where nume = 'Romania')),
                                     ('Iasi', (select id_tara from tara where nume = 'Romania')),
                                     ('Budapesta', (select id_tara from tara where nume = 'Ungaria')),
                                     ('Sofia', (select id_tara from tara where nume = 'Bulgaria')),
                                     ('Belgrad', (select id_tara from tara where nume = 'Serbia')),
                                     ('Chisinau', (select id_tara from tara where nume = 'R. Moldova')),
                                     ('Berlin', (select id_tara from tara where nume = 'Germania')),
                                     ('Viena', (select id_tara from tara where nume = 'Austria')),
                                     ('Roma', (select id_tara from tara where nume = 'Italia')),
                                     ('Madrid', (select id_tara from tara where nume = 'Spania')),
                                     ('Paris', (select id_tara from tara where nume = 'Franta')),
                                     ('Londra', (select id_tara from tara where nume = 'Marea Britanie')),
                                     ('Amsterdam', (select id_tara from tara where nume = 'Olanda')),
                                     ('Bruxelles', (select id_tara from tara where nume = 'Belgia')),
                                     ('Berna', (select id_tara from tara where nume = 'Elvetia')),
                                     ('Atena', (select id_tara from tara where nume = 'Grecia'));


insert into functie (nume_functie) values
                                       ('Medic veterinar sef'), ('Medic veterinar'), ('Asistent veterinar'), ('Ingrijitor animale'),
                                       ('Coordonator adoptii'), ('Manager adapost'), ('Receptioner'), ('Sofer ambulanta'),
                                       ('Specialist comportament'), ('Voluntar'), ('Contabil'), ('Responsabil curatenie'),
                                       ('Fotograf animale'), ('Specialist IT');

insert into specie (nume) values
                              ('Caine'), ('Pisica'), ('Iepure'), ('Hamster'), ('Porcusor de guineea'),
                              ('Papagal'), ('Canar'), ('Broasca testoasa'), ('Iguana'), ('Chinchilla'),
                              ('Cal'), ('Magar'), ('Capra'), ('Oaie'), ('Porc spinos');

insert into rasa (nume, hrana_zi, id_specie) values
                                                 ('Ciobanesc german', 500, 1), ('Labrador', 450, 1), ('Golden retriever', 450, 1),
                                                 ('Bulldog', 300, 1), ('Beagle', 250, 1), ('Pudel', 200, 1), ('Rottweiler', 600, 1),
                                                 ('Maidanez', 300, 1),
                                                 ('Persana', 100, 2), ('Siameza', 80, 2), ('Maine coon', 150, 2),
                                                 ('British shorthair', 100, 2), ('Ragdoll', 120, 2), ('Sfinx', 90, 2), ('Europeana', 100, 2),
                                                 ('Cap de leu', 50, 3), ('Berbec german', 80, 3), ('Urias belgian', 150, 3),
                                                 ('Sirian', 20, 4), ('Pitic rusesc', 10, 4), ('Roborovski', 10, 4),
                                                 ('Perus', 15, 6), ('Nimfa', 25, 6), ('Micul alexandru', 40, 6), ('Jako', 60, 6);


insert into tip_interventie (denumire_interventie) values
                                                       ('Vaccin antirabic'), ('Vaccin polivalent'), ('Deparazitare interna'), ('Deparazitare externa'),
                                                       ('Sterilizare'), ('Castrare'), ('Microcipare'), ('Consult general'), ('Ecografie'), ('Radiografie'),
                                                       ('Analize sange'), ('Toaletare'), ('Operatie fractura'), ('Extractie dentara'), ('Tratament otita'),
                                                       ('Asistenta la fatare'), ('Operatie cezariana'), ('Administrare perfuzii');


insert into adapost (nume, adresa, id_oras)
select 'Animal Rescue ' || nume, 'Bulevardul Central nr. ' || id_oras, id_oras
from oras;


create or replace procedure generare_date_angajati()
    language plpgsql as $$
declare
    v_nume_familie text[] := array[
        'Popescu', 'Ionescu', 'Radulescu', 'Stan', 'Marin', 'Constantin', 'Vasile', 'Gheorghe',
        'Matei', 'Toma', 'Barbu', 'Nita', 'Dima', 'Stoica', 'Munteanu', 'Dumitrescu', 'Iancu',
        'Teodorescu', 'Puscasu', 'Enache', 'Vasilescu', 'Georgescu', 'Muresan', 'Dragomir',
        'Lungu', 'Balan', 'Sava', 'Badea', 'Cojocaru', 'Tudor'
        ];
    v_prenume_lista text[] := array[
        'Ion', 'Maria', 'Andrei', 'Elena', 'Mihai', 'Ioana', 'Radu', 'Carmen', 'Stefan',
        'Alexandra', 'Bogdan', 'Diana', 'Florin', 'Cristina', 'Victor', 'Ana', 'Dan',
        'Laura', 'Paul', 'Gabriel', 'Alina', 'George', 'Mihaela', 'Vasile', 'Simona',
        'Dragos', 'Monica', 'Adrian', 'Oana', 'Catalin'
        ];
    r_adapost record;
    r_specie record;
    v_id_angajat int;
    v_nume text;
    v_prenume text;
    v_telefon_counter bigint := 720000000;
    v_adapost_vizita int;
    v_salariu numeric;
begin
    for r_adapost in select id_adapost from adapost loop

            -- 1 medic sef
            v_nume := v_nume_familie[floor(random()*array_length(v_nume_familie,1))+1];
            v_prenume := v_prenume_lista[floor(random() * array_length(v_prenume_lista, 1) + 1)];
            v_telefon_counter := v_telefon_counter + 1;

            insert into angajat (nume, prenume, id_functie, id_adapost, telefon, salariu)
            values (v_nume, v_prenume, 1, r_adapost.id_adapost, '0' || v_telefon_counter::text, 8500.00)
            returning id_angajat into v_id_angajat;

            for k in 1..2 loop
                    select id_adapost into v_adapost_vizita from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                    insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
                    values (current_date - floor(random() * 100 + 1)::int, 'Schimb de experienta', v_id_angajat, v_adapost_vizita, 7)
                    on conflict do nothing;
                end loop;

            for i in 1..2 loop -- 2 medici
                    v_nume := v_nume_familie[floor(random() * array_length(v_nume_familie, 1) + 1)];
                    v_prenume := v_prenume_lista[floor(random() * array_length(v_prenume_lista, 1) + 1)];
                    v_telefon_counter := v_telefon_counter + 1;

                    insert into angajat (nume, prenume, id_functie, id_adapost, telefon, salariu)
                    values (v_nume, v_prenume, 2, r_adapost.id_adapost, '0' || v_telefon_counter::text, 7000.00)
                    returning id_angajat into v_id_angajat;

                    for k in 1..2 loop
                            select id_adapost into v_adapost_vizita from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                            insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
                            values (current_date - floor(random() * 100 + 1)::int, 'Schimb de experienta', v_id_angajat, v_adapost_vizita, 7)
                            on conflict do nothing;
                        end loop;
                end loop;


            for i in 1..2 loop -- asistenti
                    v_nume := v_nume_familie[floor(random() * array_length(v_nume_familie, 1) + 1)];
                    v_prenume := v_prenume_lista[floor(random() * array_length(v_prenume_lista, 1) + 1)];
                    v_telefon_counter := v_telefon_counter + 1;

                    insert into angajat (nume, prenume, id_functie, id_adapost, telefon, salariu)
                    values (v_nume, v_prenume, 3, r_adapost.id_adapost, '0' || v_telefon_counter::text, 5000.00)
                    returning id_angajat into v_id_angajat;

                    for k in 1..2 loop
                            select id_adapost into v_adapost_vizita from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                            insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
                            values (current_date - floor(random() * 100 + 1)::int, 'Schimb de experienta', v_id_angajat, v_adapost_vizita, 7)
                            on conflict do nothing;
                        end loop;
                end loop;


            for r_specie in select distinct id_specie from rasa loop-- cate un ingrijitor pt fiecare specie
                    v_nume := v_nume_familie[floor(random() * array_length(v_nume_familie, 1) + 1)];
                    v_prenume := v_prenume_lista[floor(random() * array_length(v_prenume_lista, 1) + 1)];
                    v_telefon_counter := v_telefon_counter + 1;

                    insert into angajat (nume, prenume, id_functie, id_adapost, telefon, salariu)
                    values (v_nume, v_prenume, 4, r_adapost.id_adapost, '0' || v_telefon_counter::text, 4000.00)
                    returning id_angajat into v_id_angajat;

                    insert into specializare_angajat (id_angajat, id_specie)
                    values (v_id_angajat, r_specie.id_specie)
                    on conflict do nothing;

                    for k in 1..2 loop
                            select id_adapost into v_adapost_vizita from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                            insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
                            values (current_date - floor(random() * 100 + 1)::int, 'Schimb de experienta', v_id_angajat, v_adapost_vizita, 7)
                            on conflict do nothing;
                        end loop;
                end loop;

            for i_func in 5..14 loop --celelalte functii
                    v_nume := v_nume_familie[floor(random() * array_length(v_nume_familie, 1) + 1)];
                    v_prenume := v_prenume_lista[floor(random() * array_length(v_prenume_lista, 1) + 1)];
                    v_telefon_counter := v_telefon_counter + 1;

                    if i_func = 10 then -- voluntarii
                        v_salariu := 0.00;
                    else
                        v_salariu := 4000.00;
                    end if;

                    insert into angajat (nume, prenume, id_functie, id_adapost, telefon, salariu)
                    values (v_nume, v_prenume, i_func, r_adapost.id_adapost, '0' || v_telefon_counter::text, v_salariu)
                    returning id_angajat into v_id_angajat;

                    for k in 1..2 loop
                            select id_adapost into v_adapost_vizita from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                            insert into vizita_angajat (data_vizita, motiv, id_angajat, id_adapost, durata_zile)
                            values (current_date - floor(random() * 100 + 1)::int, 'Schimb de experienta', v_id_angajat, v_adapost_vizita, 7)
                            on conflict do nothing;
                        end loop;
                end loop;

        end loop;
end;
$$;


create or replace procedure generare_date_animale()
language plpgsql as $$
    declare
v_nume_lista text[] := array[

        'Rex', 'Max', 'Bella', 'Luna', 'Charlie', 'Lucy', 'Cooper', 'Daisy', 'Milo', 'Sophie',
        'Rocky', 'Sadie', 'Bear', 'Molly', 'Teddy', 'Lola', 'Duke', 'Bailey', 'Leo', 'Stella',
        'Oscar', 'Felix', 'Simba', 'Misty', 'Lucky', 'Patch', 'Smokey', 'Shadow', 'Tiger', 'Casper',
        'Buster', 'Bruno', 'Cleo', 'Coco', 'Dexter', 'Fiona', 'Gigi', 'Harley', 'Jasper', 'Koda',
        'Loki', 'Maya', 'Nala', 'Ollie', 'Penny', 'Rosie', 'Ruby', 'Sam', 'Toby', 'Zeus', 'Zoe',
        'Ziggy', 'Thor', 'Apollo', 'Athena', 'Buddy', 'Chloe', 'Diesel', 'Ellie', 'Finn', 'Frankie',
        'George', 'Hazel', 'Henry', 'Izzy', 'Jax', 'Kona', 'Leia', 'Lexi', 'Louie', 'Mac', 'Marley',
        'Millie', 'Murphy', 'Oakley', 'Otis', 'Peanut', 'Pearl', 'Pepper', 'Piper', 'Riley', 'Romeo',
        'Roxy', 'Rufus', 'Rusty', 'Scout', 'Sparky', 'Tucker', 'Willow', 'Winston',
        'Grivei', 'Azorel', 'Pufi', 'Bobita', 'Codita', 'Misu', 'Tica', 'Rica', 'Gica', 'Bubu',
        'Peticel', 'Nuca', 'Alun', 'Fulg', 'Negrut', 'Haiduc', 'Ursu', 'Mura', 'Fram', 'Norocel',
        'Roscovan', 'Pongo', 'Tasha', 'Kira', 'Lulu', 'Zuzu', 'Piki', 'Bibi', 'Suzi', 'Totto',
        'Bugs', 'Jerry', 'Tom', 'Sylvester', 'Tweety', 'Duffy', 'Pluto', 'Goofy', 'Scooby', 'Scrappy',
        'Kiwi', 'Mango', 'Rio', 'Pikachu', 'Yoshi', 'Sonic', 'Flash', 'Oreo', 'Biscuit', 'Muffin'
    ];
    r_adapost record;
    r_specie record;

    v_id_rasa int;
    v_id_cusca int;
    v_id_animal int;
    v_id_fisa int;
    v_id_angajat_medical int;
    v_id_tip_interventie int;
    v_adapost_sursa int;

    v_data_nastere date;
    v_data_intrare date;
    v_nume_ales text;
    v_cod_cusca int;
    begin
        for r_adapost in select id_adapost from adapost loop
            v_cod_cusca:=1;
            for r_specie in select distinct id_specie from rasa loop --id ul speciei din tabelul de rase pt ca nu am introdus rase pt toate speciile

                for i in 1..2 loop
                    insert into cusca(cod_identificare, capacitate_maxima, id_adapost,id_specie) values ('c'||v_cod_cusca,10,r_adapost.id_adapost,r_specie.id_specie)
                    returning id_cusca into v_id_cusca;
                    v_cod_cusca:=v_cod_cusca+1;
                    end loop;

                for j in 1..5 loop
                    v_nume_ales:=v_nume_lista[floor(random()*array_length(v_nume_lista,1)+1)];
                    v_data_nastere := current_date - floor(random() * 3000 + 100)::int;
                    v_data_intrare := v_data_nastere + floor(random() * 100 + 30)::int;
                    if v_data_intrare > current_date then v_data_intrare := current_date; end if;

                    select id_rasa into v_id_rasa from rasa where id_specie = r_specie.id_specie order by random() limit 1;

                    insert into animal (nume, data_nastere, data_intrare, status, id_rasa,id_cusca)
                    values (v_nume_ales, v_data_nastere, v_data_intrare, 'adapost', v_id_rasa,v_id_cusca)
                    returning id_animal into v_id_animal;

                    select id_fisa into v_id_fisa from fisa_medicala where id_animal = v_id_animal;
                    select id_angajat into v_id_angajat_medical from angajat a
                    join functie f on a.id_functie=f.id_functie
                    where id_adapost = r_adapost.id_adapost and nume_functie ilike '%medic%' order by random();

                    for k in 1..2 loop
                            select id_tip_interventie into v_id_tip_interventie from tip_interventie order by random() limit 1;

                            insert into interventie_medicala (data, id_fisa, id_tip_interventie, observatii, id_angajat)
                            values (v_data_intrare + k, v_id_fisa, v_id_tip_interventie, 'Inspectie de rutina', v_id_angajat_medical)
                            on conflict do nothing;
                        end loop;

                    if random()<0.20 then --pt 20% din animale generam transferuri
                        select id_adapost into v_adapost_sursa from adapost where id_adapost != r_adapost.id_adapost order by random() limit 1;
                        if v_adapost_sursa is not null then
                            insert into istoric_transfer (data_transfer, id_animal, id_adapost_sursa, id_adapost_destinatie)
                            values (v_data_intrare, v_id_animal, v_adapost_sursa, r_adapost.id_adapost)
                            on conflict do nothing;
                        end if;
                    end if;

                    end loop;
                end loop;
            end loop;
end;
$$;

call generare_date_angajati();
call generare_date_animale();