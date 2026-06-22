package com.example.AnimalShelter.model;


import org.springframework.data.annotation.Transient;

public class Adapost {
    private Integer idAdapost;
    private String nume;
    private String adresa;
    private Integer idOras;
    @Transient
    private String oras;

    public Adapost() {
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public Integer getIdAdapost() { return idAdapost; }
    public void setIdAdapost(Integer idAdapost) { this.idAdapost = idAdapost; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }

    public Integer getIdOras() { return idOras; }
    public void setIdOras(Integer idOras) { this.idOras = idOras; }
}