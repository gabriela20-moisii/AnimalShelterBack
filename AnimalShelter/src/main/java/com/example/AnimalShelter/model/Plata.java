package com.example.AnimalShelter.model;

import java.time.LocalDate;

public class Plata {
    private int suma;
    private LocalDate dataFactura;
    private String descriere;

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public LocalDate getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(LocalDate dataFactura) {
        this.dataFactura = dataFactura;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
