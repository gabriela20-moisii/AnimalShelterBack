create or replace function  adauga_cusca(
       p_cod varchar,
       p_id_adapost int,
       p_capacitate int,
       p_specie varchar
) returns int as $$
declare
       v_id_specie specie.id_specie%type;
       v_id_cusca cusca.id_cusca%type;
begin
  select id_specie into v_id_specie from specie where nume ilike p_specie;
  if v_id_specie is null then
     raise exception 'Specia % nu exista in baza de date',p_specie;
  end if;
  insert into cusca (cod_identificare,capacitate_maxima,id_adapost,id_specie) values (p_cod,p_capacitate,p_id_adapost,v_id_specie) returning id_cusca into v_id_cusca;
  return v_id_cusca;
end;
$$ language plpgsql;
