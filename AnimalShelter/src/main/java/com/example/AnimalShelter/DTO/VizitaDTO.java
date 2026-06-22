package com.example.AnimalShelter.DTO;

import java.time.LocalDate;

public class VizitaDTO {
    private LocalDate dataVizita;
    private Integer durataZile;
    private String motiv;
    private String numeAdapostVizitat;
    private String orasVizitat;
    public VizitaDTO(){};

    public LocalDate getDataVizita() {
        return dataVizita;
    }

    public void setDataVizita(LocalDate dataVizita) {
        this.dataVizita = dataVizita;
    }

    public Integer getDurataZile() {
        return durataZile;
    }

    public void setDurataZile(Integer durataZile) {
        this.durataZile = durataZile;
    }

    public String getMotiv() {
        return motiv;
    }

    public void setMotiv(String motiv) {
        this.motiv = motiv;
    }

    public String getNumeAdapostVizitat() {
        return numeAdapostVizitat;
    }

    public void setNumeAdapostVizitat(String numeAdapostVizitat) {
        this.numeAdapostVizitat = numeAdapostVizitat;
    }

    public String getOrasVizitat() {
        return orasVizitat;
    }

    public void setOrasVizitat(String orasVizitat) {
        this.orasVizitat = orasVizitat;
    }
}
