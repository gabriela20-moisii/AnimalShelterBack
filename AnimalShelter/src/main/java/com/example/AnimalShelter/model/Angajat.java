package com.example.AnimalShelter.model;

import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;

public class Angajat {
    private String nume;
    private String prenume;
    private Integer idAngajat;
    private Integer idAdapost;
    private Integer idFunctie;
    private String telefon;
    private BigDecimal salariu;
    @Transient
    private String functie;
    Angajat(){};

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Integer getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Integer idAngajat) {
        this.idAngajat = idAngajat;
    }

    public Integer getIdAdapost() {
        return idAdapost;
    }

    public void setIdAdapost(Integer idAdapost) {
        this.idAdapost = idAdapost;
    }

    public Integer getIdFunctie() {
        return idFunctie;
    }

    public void setIdFunctie(Integer idFunctie) {
        this.idFunctie = idFunctie;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public BigDecimal getSalariu() {
        return salariu;
    }

    public void setSalariu(BigDecimal salariu) {
        this.salariu = salariu;
    }
}
