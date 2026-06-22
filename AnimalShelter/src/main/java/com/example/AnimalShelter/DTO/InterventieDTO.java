package com.example.AnimalShelter.DTO;

import java.time.LocalDate;

public class InterventieDTO {
    private Integer idFisa;
    private String numeMedic;
    private String prenumeMedic;
    private String tipInterventie;
    private String observatii;
    private LocalDate data;
    public InterventieDTO(){};

    public Integer getIdFisa() {
        return idFisa;
    }

    public void setIdFisa(Integer idFisa) {
        this.idFisa = idFisa;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setNumeMedic(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public String getPrenumeMedic() {
        return prenumeMedic;
    }

    public void setPrenumeMedic(String prenumeMedic) {
        this.prenumeMedic = prenumeMedic;
    }

    public String getTipInterventie() {
        return tipInterventie;
    }

    public void setTipInterventie(String tipInterventie) {
        this.tipInterventie = tipInterventie;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
