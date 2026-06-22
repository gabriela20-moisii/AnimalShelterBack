package com.example.AnimalShelter.model;

import java.time.LocalDate;

public class Venit {
    private int suma;
    private LocalDate dataIncasare;
    private String descriere;

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public LocalDate getDataIncasare() {
        return dataIncasare;
    }

    public void setDataIncasare(LocalDate dataIncasare) {
        this.dataIncasare = dataIncasare;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
