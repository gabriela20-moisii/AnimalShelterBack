package com.example.AnimalShelter.model;

import org.springframework.data.annotation.Transient;

public class Cusca {
    private Integer idCusca;
    private String codIdentificare;
    private Integer idSpecie;
    private Integer idAdapost;
    private Integer capacitateMaxima;
    @Transient
    private String specie;
    Cusca(){};

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Integer getIdCusca() {
        return idCusca;
    }

    public void setIdCusca(Integer idCusca) {
        this.idCusca = idCusca;
    }

    public String getCodIdentificare() {
        return codIdentificare;
    }

    public void setCodIdentificare(String codIndentificare) {
        this.codIdentificare = codIndentificare;
    }

    public Integer getIdSpecie() {
        return idSpecie;
    }

    public void setIdSpecie(Integer idSpecie) {
        this.idSpecie = idSpecie;
    }

    public Integer getIdAdapost() {
        return idAdapost;
    }

    public void setIdAdapost(Integer idAdapost) {
        this.idAdapost = idAdapost;
    }

    public Integer getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public void setCapacitateMaxima(Integer capacitateMaxima) {
        this.capacitateMaxima = capacitateMaxima;
    }
}
