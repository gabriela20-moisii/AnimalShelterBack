DO $$
declare
 v_tari TEXT[]:=array['Albania', 'Andorra', 'Armenia', 'Austria', 'Azerbaidjan', 'Belarus',
                      'Belgia', 'Bosnia si Hertegovina', 'Bulgaria', 'Cehia', 'Cipru',
                      'Croatia', 'Danemarca', 'Elvetia', 'Estonia', 'Finlanda', 'Franta',
                      'Georgia', 'Germania', 'Grecia', 'Irlanda', 'Islanda', 'Italia',
                      'Kazahstan', 'Kosovo', 'Letonia', 'Liechtenstein', 'Lituania',
                      'Luxemburg', 'Macedonia de Nord', 'Malta', 'Marea Britanie',
                      'R. Moldova', 'Monaco', 'Muntenegru', 'Norvegia', 'Olanda', 'Polonia',
                      'Portugalia', 'Romania', 'Rusia', 'San Marino', 'Serbia', 'Slovacia',
                      'Slovenia', 'Spania', 'Suedia', 'Turcia', 'Ucraina', 'Ungaria', 'Vatican'];
 v_tara text;
 begin
  foreach v_tara in array v_tari loop
   if not exists (select 1 from tara where nume=v_tara) then
    insert into tara(nume) values (v_tara);
   end if;
  end loop;
 end;
$$ language plpgsql;