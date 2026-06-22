package com.example.AnimalShelter.DTO;

import java.time.LocalDate;
import java.util.*;

public class AnimalDTO {
    private Integer idAnimal;
    private String numeAnimal;
    private LocalDate dataNastere;
    private String status;
    private LocalDate dataIntrare;

    private Integer idRasa;
    private String numeRasa;
    private String numeSpecie;

    private String codIdentificareCusca;

    private String numeAdapost;
    private List<InterventieDTO> istoricMedical;
    private List<AdoptieDTO> istoricAdoptie;
    private List<TransferAnimalDTO> istoricTransfer;

    public List<TransferAnimalDTO> getIstoricTransfer() {
        return istoricTransfer;
    }

    public void setIstoricTransfer(List<TransferAnimalDTO> istoricTransfer) {
        this.istoricTransfer = istoricTransfer;
    }

    public List<InterventieDTO> getIstoricMedical() {
        return istoricMedical;
    }

    public List<AdoptieDTO> getIstoricAdoptie() {
        return istoricAdoptie;
    }

    public void setIstoricAdoptie(List<AdoptieDTO> istoricAdoptie) {
        this.istoricAdoptie = istoricAdoptie;
    }

    public void setIstoricMedical(List<InterventieDTO> istoricMedical) {
        this.istoricMedical = istoricMedical;
    }

    public Integer getIdRasa() {
        return idRasa;
    }

    public void setIdRasa(Integer idRasa) {
        this.idRasa = idRasa;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNumeAnimal() {
        return numeAnimal;
    }

    public void setNumeAnimal(String numeAnimal) {
        this.numeAnimal = numeAnimal;
    }

    public LocalDate getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(LocalDate dataNastere) {
        this.dataNastere = dataNastere;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataIntrare() {
        return dataIntrare;
    }

    public void setDataIntrare(LocalDate dataIntrare) {
        this.dataIntrare = dataIntrare;
    }

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

    public String getCodIdentificareCusca() {
        return codIdentificareCusca;
    }

    public void setCodIdentificareCusca(String codIdentificareCusca) {
        this.codIdentificareCusca = codIdentificareCusca;
    }




    public String getNumeAdapost() {
        return numeAdapost;
    }

    public void setNumeAdapost(String numeAdapost) {
        this.numeAdapost = numeAdapost;
    }


}
