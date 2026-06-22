create table tara(
                     id_tara serial primary key,
                     nume varchar(100) not null unique
);

create table oras(
                     id_oras serial primary key,
                     nume varchar(100) not null unique,
                     id_tara int not null,
                     constraint fk_oras_tara foreign key (id_tara) references tara(id_tara) on delete cascade
);

create table adapost(
                        id_adapost serial primary key,
                        nume varchar(100) not null,
                        adresa varchar(255) not null,
                        id_oras int not null unique, --intr-un oras avem un sg adapost
                        constraint fk_adapost_oras foreign key (id_oras) references oras(id_oras) on delete restrict --orasele nu pot fi sterse daca exista adaposturi in ele
);

create table functie (
                         id_functie serial primary key,
                         nume_functie varchar(100) not null unique
);

create table angajat (
                         id_angajat serial primary key,
                         nume varchar(50) not null,
                         prenume varchar(50) not null,
                         id_functie int not null,
                         id_adapost int not null,
                         telefon varchar(15) not null unique,
                         salariu numeric(10,2),
                         constraint fk_angajat_functie foreign key (id_functie) references functie(id_functie),
                         constraint fk_angajat_adapost foreign key (id_adapost) references adapost(id_adapost)
);

create table specie(
                       id_specie serial primary key,
                       nume varchar(50) not null unique
);

create table rasa(
                     id_rasa serial primary key,
                     nume varchar(100) not null,
                     hrana_zi int not null check (hrana_zi > 0), --necesarul de hrana pe zi in grame (ajustat la >0 pentru teste)
                     id_specie int not null,
                     constraint fk_rasa_specie foreign key (id_specie) references specie(id_specie),
                     constraint uq_rasa_specie unique (nume, id_specie)
);

create table cusca(
                      id_cusca serial primary key,
                      cod_identificare varchar(20) not null,
                      capacitate_maxima int not null check (capacitate_maxima > 0),
                      id_adapost int not null,
                      id_specie int not null,
                      constraint fk_cusca_adapost foreign key (id_adapost) references adapost(id_adapost),
                      constraint fk_cusca_specie foreign key (id_specie) references specie(id_specie),
                      constraint uq_cusca_adapost unique (cod_identificare, id_adapost)
);

create table animal(
                       id_animal serial primary key,
                       nume varchar(50) not null,
                       data_nastere date,
                       data_intrare date not null,
                       status varchar(30) check(status in ('adapost','adoptat')),
                       id_rasa int not null,
                       id_cusca int not null,
                       constraint fk_animal_rasa foreign key (id_rasa) references rasa(id_rasa),
                       constraint fk_animal_cusca foreign key (id_cusca) references cusca(id_cusca)
);

create table fisa_medicala(
                              id_fisa serial primary key,
                              id_animal int not null unique,
                              constraint fk_fisa_animal foreign key (id_animal) references animal(id_animal) on delete cascade
);

create table tip_interventie(
                                id_tip_interventie serial primary key,
                                denumire_interventie varchar(100) not null unique
);

create table interventie_medicala(
                                     id_interventie serial primary key,
                                     data date not null,
                                     id_fisa int not null,
                                     id_tip_interventie int not null,
                                     observatii text,
                                     id_angajat int not null, --trb trigger ca sa verificam daca angajatul este medic
                                     constraint fk_interventie_fisa foreign key (id_fisa) references fisa_medicala(id_fisa),
                                     constraint fk_interventie_tip foreign key (id_tip_interventie) references tip_interventie(id_tip_interventie),
                                     constraint fk_interventie_angajat foreign key (id_angajat) references angajat(id_angajat),
                                     constraint uq_interventie_dubla unique (id_fisa, id_tip_interventie, id_angajat, data)
);

create table adoptator(
                          id_adoptator serial primary key,
                          nume varchar(50) not null,
                          prenume varchar(50) not null,
                          telefon varchar(15) not null unique,
                          email varchar(100) not null unique
);

create table adoptie(
                        id_adoptie serial primary key,
                        data_adoptie date not null,
                        data_returnare date,
                        motiv_returnare text,
                        id_animal int not null,
                        id_adoptator int not null,
                        constraint fk_adoptie_animal foreign key (id_animal) references animal(id_animal),
                        constraint fk_adoptie_adoptator foreign key (id_adoptator) references adoptator(id_adoptator),
                        constraint uq_adoptie_dubla unique (id_animal, data_adoptie)
);

create table istoric_transfer(
                                 id_transfer serial primary key,
                                 data_transfer date not null,
                                 id_animal int not null,
                                 id_adapost_sursa int not null,
                                 id_adapost_destinatie int not null,
                                 constraint fk_transfer_animal foreign key (id_animal) references animal(id_animal),
                                 constraint fk_transfer_sursa foreign key (id_adapost_sursa) references adapost(id_adapost),
                                 constraint fk_transfer_dest foreign key (id_adapost_destinatie) references adapost(id_adapost),
                                 constraint uq_transfer_animal_dublu unique (id_animal, data_transfer, id_adapost_destinatie)
);

create table specializare_angajat(
                                     id_angajat int not null,
                                     id_specie int not null,
                                     primary key (id_angajat, id_specie),
                                     constraint fk_specializare_angajat foreign key (id_angajat) references angajat(id_angajat) on delete cascade,
                                     constraint fk_specializare_specie foreign key (id_specie) references specie(id_specie) on delete cascade
);

create table vizita_angajat(
                               id_vizita serial primary key,
                               data_vizita date not null,
                               motiv text,
                               durata_zile int check (durata_zile > 0),
                               id_angajat int not null,
                               id_adapost int not null, --adapostul vizitat
                               constraint fk_viz_angajat foreign key (id_angajat) references angajat(id_angajat),
                               constraint fk_viz_adapost foreign key (id_adapost) references adapost(id_adapost),
                               constraint uq_vizita_dubla unique (id_angajat, id_adapost, data_vizita)
);



create table cheltuieli(
                           id_cheltuiala serial primary key,
                           suma numeric(10,2) not null check (suma > 0),
                           data_factura date not null,
                           descriere text,
                           id_adapost int not null,
                           constraint fk_cheltuiala_adapost foreign key (id_adapost) references adapost(id_adapost)
);
create table venituri(
                      id_venit serial primary key,
                      suma numeric(10,2) not null check (suma > 0),
                      data_incasare date not null,
                      descriere text,
                      id_adapost int not null,
                      constraint fk_venit_adapost foreign key (id_adapost) references adapost(id_adapost)
);