create or replace procedure generare_date_adoptii()
    language plpgsql as $$
declare
    v_nume_lista text[] := array['Ionescu', 'Popescu', 'Dumitru', 'Stan', 'Radu', 'Marin', 'Stoica', 'Gheorghe', 'Matei', 'Munteanu'];
    v_prenume_lista text[] := array['Ana', 'Ion', 'Maria', 'Andrei', 'Elena', 'Mihai', 'Alex', 'Diana', 'Vlad', 'Ioana'];

    r_animal record;
    v_id_adoptator int;
    v_telefon_counter bigint := 730000000;
    v_data_adoptie date;
    v_nume text;
    v_prenume text;
begin
    for i in 1..30 loop
            v_telefon_counter := v_telefon_counter + 1;
            v_nume:=v_nume_lista[floor(random() * array_length(v_nume_lista, 1) + 1)];
            v_prenume:=v_prenume_lista[floor(random() * array_length(v_nume_lista, 1) + 1)];
            insert into adoptator (nume, prenume, telefon, email)
            values (
                       v_nume, v_prenume,
                       '0' || v_telefon_counter::text,
                       lower(v_nume)||lower(v_prenume)||i||'@gmail.com'
                   );
        end loop;

    for r_animal in select id_animal, data_intrare from animal where status = 'adapost' loop

            if random() < 0.40 then
                select id_adoptator into v_id_adoptator from adoptator order by random() limit 1;

                v_data_adoptie := r_animal.data_intrare + floor(random() * 55 + 5)::int; -- intre 5 si 60 zile de la intrare adapost
                if v_data_adoptie > current_date then v_data_adoptie := current_date; end if;

                insert into adoptie (data_adoptie, id_animal, id_adoptator)
                values (v_data_adoptie, r_animal.id_animal, v_id_adoptator);
            end if;

        end loop;
end;
$$;

call generare_date_adoptii();