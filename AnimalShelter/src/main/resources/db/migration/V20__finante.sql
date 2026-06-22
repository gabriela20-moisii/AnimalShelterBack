create or replace procedure generare_finante()
language plpgsql as $$
    declare
    r_adapost record;
    v_total_salarii numeric(10,2);
    v_cheltuieli text[]:=array['Furnizori hrana','Consumabile medicale','Facturi utilitati','Combustibil ambulanta', 'Reparatii','Campanii promovare'];
    v_venituri text[]:=array['Donatie online','Sponsorizare companie','Donator recurent','Targ de adoptii','Bal de caritate','Strangere de fonduri','Donatie la adoptie','Redirectionare impozit'];

    v_zi_random int;
    begin
        for r_adapost in select id_adapost from adapost loop
            select coalesce(sum(salariu),0) into v_total_salarii from angajat where id_adapost=r_adapost.id_adapost;

            if v_total_salarii >0 then
                insert into cheltuieli(suma, data_factura, descriere, id_adapost) values (v_total_salarii, current_date-interval '1 month', 'Plata salarii',r_adapost.id_adapost);
                insert into cheltuieli(suma, data_factura, descriere, id_adapost) values (v_total_salarii, current_date, 'Plata salarii',r_adapost.id_adapost);
            end if;
            for i in 1..6 loop
                    v_zi_random := floor(random() * 60);
                    insert into cheltuieli(suma, data_factura, descriere, id_adapost)
                    values (
                               (random() * 2500 + 100)::numeric(10,2),
                               current_date - v_zi_random,
                               v_cheltuieli[floor(random() * array_length(v_cheltuieli, 1)) + 1],
                               r_adapost.id_adapost
                           );
            end loop;
            for i in 1..10 loop
                    v_zi_random := floor(random() * 60);
                    insert into venituri(suma, data_incasare, descriere, id_adapost)
                    values (
                               (random() * 4500 + 50)::numeric(10,2),
                               current_date - v_zi_random,
                               v_venituri[floor(random() * array_length(v_venituri, 1)) + 1],
                               r_adapost.id_adapost
                           );
                end loop;
        end loop;
end;
$$;
call generare_finante();