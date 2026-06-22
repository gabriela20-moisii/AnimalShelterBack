package com.example.AnimalShelter.DTO;

import java.time.LocalDate;

public class AdoptieDTO {
        private LocalDate dataAdoptie;
        private LocalDate dataReturnare;
        private String motivReturnare;
        private String numeAdoptator;
        private String prenumeAdoptator;
        private String telefonAdoptator;
        private String emailAdoptator;

    public AdoptieDTO(){}
    public String getEmailAdoptator() {
        return emailAdoptator;
    }

    public void setEmailAdoptator(String emailAdoptator) {
        this.emailAdoptator = emailAdoptator;
    }

    public LocalDate getDataAdoptie() {
        return dataAdoptie;
    }

    public void setDataAdoptie(LocalDate dataAdoptie) {
        this.dataAdoptie = dataAdoptie;
    }

    public LocalDate getDataReturnare() {
        return dataReturnare;
    }

    public void setDataReturnare(LocalDate dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    public String getMotivReturnare() {
        return motivReturnare;
    }

    public void setMotivReturnare(String motivReturnare) {
        this.motivReturnare = motivReturnare;
    }

    public String getNumeAdoptator() {
        return numeAdoptator;
    }

    public void setNumeAdoptator(String numeAdoptator) {
        this.numeAdoptator = numeAdoptator;
    }

    public String getPrenumeAdoptator() {
        return prenumeAdoptator;
    }

    public void setPrenumeAdoptator(String prenumeAdoptator) {
        this.prenumeAdoptator = prenumeAdoptator;
    }

    public String getTelefonAdoptator() {
        return telefonAdoptator;
    }

    public void setTelefonAdoptator(String telefonAdoptator) {
        this.telefonAdoptator = telefonAdoptator;
    }
}
