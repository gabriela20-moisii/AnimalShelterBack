package com.example.AnimalShelter.DTO;

import java.time.LocalDate;

public class TransferAnimalDTO {
    private LocalDate dataTransfer;
    private String numeAdapostSursa;
    private String orasSursa;
    private String numeAdapostDestinatie;
    private String orasDestinatie;
    public TransferAnimalDTO(){};

    public LocalDate getDataTransfer() {
        return dataTransfer;
    }

    public void setDataTransfer(LocalDate dataTransfer) {
        this.dataTransfer = dataTransfer;
    }

    public String getNumeAdapostSursa() {
        return numeAdapostSursa;
    }

    public void setNumeAdapostSursa(String numeAdapostSursa) {
        this.numeAdapostSursa = numeAdapostSursa;
    }

    public String getOrasSursa() {
        return orasSursa;
    }

    public void setOrasSursa(String orasSursa) {
        this.orasSursa = orasSursa;
    }

    public String getNumeAdapostDestinatie() {
        return numeAdapostDestinatie;
    }

    public void setNumeAdapostDestinatie(String numeAdapostDestinatie) {
        this.numeAdapostDestinatie = numeAdapostDestinatie;
    }

    public String getOrasDestinatie() {
        return orasDestinatie;
    }

    public void setOrasDestinatie(String orasDestinatie) {
        this.orasDestinatie = orasDestinatie;
    }
}
