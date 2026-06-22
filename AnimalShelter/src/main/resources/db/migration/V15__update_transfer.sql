create or replace function proceseaza_transfer(
    p_id_animal int,
    p_id_adapost_sursa int,
    p_oras_dest oras.nume%type
) returns void as $$
declare
    v_id_adapost_destinatie int;
    v_id_cusca_noua int;
    v_id_specie int;
    v_id_adapost_curent int;
begin
    select c.id_adapost into v_id_adapost_curent
    from animal a
             join cusca c on a.id_cusca = c.id_cusca
    where a.id_animal = p_id_animal and a.status = 'adapost';--in ce adapost este animalul ca sa nu pot int=registra aceeasi cerere de transfer de mai multe ori
    if not found then
        raise exception 'Animalul cu id % nu este disponibil in niciun adapost!', p_id_animal;
    end if;

    if v_id_adapost_curent != p_id_adapost_sursa then
        raise exception 'Animalul nu se afla in adapostul sursa specificat!';
    end if;
    select a.id_adapost into v_id_adapost_destinatie
    from adapost a
             join oras o on a.id_oras = o.id_oras
    where trim(lower(o.nume)) = trim(lower(p_oras_dest));

    if not found then
        raise exception 'Nu exista niciun adapost in orasul %!', p_oras_dest;
    end if;

    if p_id_adapost_sursa = v_id_adapost_destinatie then
        raise exception 'Animalul se afla deja in adapostul din orasul %!', p_oras_dest;
    end if;

    select r.id_specie into v_id_specie
    from animal a
             join rasa r on a.id_rasa = r.id_rasa
    where a.id_animal = p_id_animal;

    select c.id_cusca into v_id_cusca_noua
    from cusca c
             left join animal a on c.id_cusca = a.id_cusca and a.status = 'adapost'
    where c.id_adapost = v_id_adapost_destinatie and c.id_specie = v_id_specie
    group by c.id_cusca, c.capacitate_maxima
    having count(a.id_animal) < c.capacitate_maxima
    limit 1;

    if v_id_cusca_noua is null then
        raise exception 'Adapostul din % este plin sau nu are custi pentru aceasta specie!', p_oras_dest;
    end if;
    update animal set id_cusca = v_id_cusca_noua where id_animal = p_id_animal;
    insert into istoric_transfer (data_transfer, id_animal, id_adapost_sursa, id_adapost_destinatie)
    values (current_date, p_id_animal, p_id_adapost_sursa, v_id_adapost_destinatie);

end;
$$ language plpgsql;