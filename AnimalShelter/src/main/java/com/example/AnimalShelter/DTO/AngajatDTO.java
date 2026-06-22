package com.example.AnimalShelter.DTO;

import java.math.BigDecimal;
import java.util.List;

public class AngajatDTO {
    private Integer idAngajat;
    private String numeAngajat;
    private String prenume;
    private String telefon;
    private BigDecimal salariu;
    private String functie;
    private String numeAdapost;
    private String adresaAdapost;
    private String numeOras;
    private String numeTara;
    private String specializare;
    private List<VizitaDTO> istoricVizite;

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public List<VizitaDTO> getIstoricVizite() {
        return istoricVizite;
    }

    public void setIstoricVizite(List<VizitaDTO> istoricVizite) {
        this.istoricVizite = istoricVizite;
    }

    public Integer getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Integer idAngajat) {
        this.idAngajat = idAngajat;
    }

    public String getNumeAngajat() {
        return numeAngajat;
    }

    public void setNumeAngajat(String numeAngajat) {
        this.numeAngajat = numeAngajat;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
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

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getNumeAdapost() {
        return numeAdapost;
    }

    public void setNumeAdapost(String numeAdapost) {
        this.numeAdapost = numeAdapost;
    }

    public String getAdresaAdapost() {
        return adresaAdapost;
    }

    public void setAdresaAdapost(String adresaAdapost) {
        this.adresaAdapost = adresaAdapost;
    }

    public String getNumeOras() {
        return numeOras;
    }

    public void setNumeOras(String numeOras) {
        this.numeOras = numeOras;
    }

    public String getNumeTara() {
        return numeTara;
    }

    public void setNumeTara(String numeTara) {
        this.numeTara = numeTara;
    }
}

