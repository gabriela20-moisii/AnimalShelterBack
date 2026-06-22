package com.example.AnimalShelter.model;

import org.springframework.data.annotation.Transient;

import java.time.LocalDate;

public class Animal {
    private Integer idAnimal;
    private String nume;
    private Integer idRasa;
    private Integer idCusca;
    private LocalDate dataNastere;
    private LocalDate dataIntrare;
    private String status;
    @Transient
    private String rasa;
    public Animal(){};

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public LocalDate getDataIntrare() {
        return dataIntrare;
    }

    public void setDataIntrare(LocalDate dataIntrare) {
        this.dataIntrare = dataIntrare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getIdRasa() {
        return idRasa;
    }

    public void setIdRasa(Integer idRasa) {
        this.idRasa = idRasa;
    }

    public Integer getIdCusca() {
        return idCusca;
    }

    public void setIdCusca(Integer idCusca) {
        this.idCusca = idCusca;
    }

    public LocalDate getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(LocalDate dataNastere) {
        this.dataNastere = dataNastere;
    }
}
