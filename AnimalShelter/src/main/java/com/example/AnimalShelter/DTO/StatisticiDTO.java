package com.example.AnimalShelter.DTO;

public class StatisticiDTO {
    //private int idAdapost;
    //private int idRasa;
    private String numeRasa;
    private String numeSpecie;
    private int totalAnimaleIntrate;
    private int totalAdoptiiRealizate;
    private double rataAdoptieProcent;
    private int timpMediuAsteptareZile;

    public String getNumeRasa() {
        return numeRasa;
    }

    public void setNumeRasa(String numeRasa) {
        this.numeRasa = numeRasa;
    }

    public String getNumeSpecie() {
        return numeSpecie;
    }

    public void setNumeSpecie(String numeSpecie) {
        this.numeSpecie = numeSpecie;
    }

    public int getTotalAnimaleIntrate() {
        return totalAnimaleIntrate;
    }

    public void setTotalAnimaleIntrate(int totalAnimaleIntrate) {
        this.totalAnimaleIntrate = totalAnimaleIntrate;
    }

    public int getTotalAdoptiiRealizate() {
        return totalAdoptiiRealizate;
    }

    public void setTotalAdoptiiRealizate(int totalAdoptiiRealizate) {
        this.totalAdoptiiRealizate = totalAdoptiiRealizate;
    }

    public double getRataAdoptieProcent() {
        return rataAdoptieProcent;
    }

    public void setRataAdoptieProcent(double rataAdoptieProcent) {
        this.rataAdoptieProcent = rataAdoptieProcent;
    }

    public int getTimpMediuAsteptareZile() {
        return timpMediuAsteptareZile;
    }

    public void setTimpMediuAsteptareZile(int timpMediuAsteptareZile) {
        this.timpMediuAsteptareZile = timpMediuAsteptareZile;
    }
}
