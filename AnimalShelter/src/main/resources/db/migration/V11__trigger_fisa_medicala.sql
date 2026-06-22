create or replace function creare_fisa_medicala()
returns trigger as $$
    begin
     insert into fisa_medicala(id_animal) values (NEW.id_animal);
    return new;
    end;
$$ language plpgsql;

create trigger trg_creeaza_fisa after insert on animal
   for each row execute function creare_fisa_medicala();