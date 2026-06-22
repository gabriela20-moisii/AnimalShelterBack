package com.example.AnimalShelter.service;

import com.example.AnimalShelter.DTO.AngajatDTO;
import com.example.AnimalShelter.DTO.VizitaDTO;
import com.example.AnimalShelter.repository.AngajatRepository;
import com.example.AnimalShelter.repository.VizitaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AngajatService {
    private final AngajatRepository angajatRepository;
    private final VizitaRepository vizitaRepository;

    public AngajatService(AngajatRepository angajatRepository, VizitaRepository vizitaRepository){
        this.angajatRepository=angajatRepository;
        this.vizitaRepository = vizitaRepository;
    }
    public List<AngajatDTO> getAll( Integer idAdapost){
        return angajatRepository.listAll(idAdapost);
    }

    public AngajatDTO getById(Integer idAngajat){

        AngajatDTO angajat= angajatRepository.findById(idAngajat);
        angajat.setIstoricVizite(vizitaRepository.getAllByIdAngajat(idAngajat));
        return angajat;
    }
    public AngajatDTO create(AngajatDTO angajat, Integer idAdapost){
        return angajatRepository.add(angajat,idAdapost);
    }
    public void addVizita(VizitaDTO vizita, Integer idAngajat, Integer idAdapost){
        vizitaRepository.add(vizita, idAngajat,idAdapost);
    }
}
