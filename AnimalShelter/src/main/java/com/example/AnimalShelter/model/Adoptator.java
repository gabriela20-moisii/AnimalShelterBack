package com.example.AnimalShelter.model;

public class Adoptator {
    private Integer idAdoptator;
    private String nume;
    private String prenume;
    private String telefon;
    private String email;
    Adoptator(){};
    public Integer getIdAdoptator() {
        return idAdoptator;
    }

    public void setIdAdoptator(Integer idAdoptator) {
        this.idAdoptator = idAdoptator;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
