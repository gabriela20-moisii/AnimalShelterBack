package com.example.AnimalShelter.model;

import java.time.LocalDate;

public class InterventieMedicala {
    private Integer idInterventie;
    private LocalDate data;
    private Integer idFisa;
    private Integer idTipInterventie;
    private String observatii;
    private Integer idAngajat;
    public InterventieMedicala(){};

    public Integer getIdInterventie() {
        return idInterventie;
    }

    public void setIdInterventie(Integer idInterventie) {
        this.idInterventie = idInterventie;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getIdFisa() {
        return idFisa;
    }

    public void setIdFisa(Integer idFisa) {
        this.idFisa = idFisa;
    }

    public Integer getIdTipInterventie() {
        return idTipInterventie;
    }

    public void setIdTipInterventie(Integer idTipInterventie) {
        this.idTipInterventie = idTipInterventie;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public Integer getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Integer idAngajat) {
        this.idAngajat = idAngajat;
    }
}
