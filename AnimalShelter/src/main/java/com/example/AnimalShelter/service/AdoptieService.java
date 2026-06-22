package com.example.AnimalShelter.service;

import com.example.AnimalShelter.DTO.AnimalDTO;
import com.example.AnimalShelter.model.Adoptator;
import com.example.AnimalShelter.model.Animal;
import com.example.AnimalShelter.repository.AdoptatorRepository;
import com.example.AnimalShelter.repository.AdoptieRepository;
import com.example.AnimalShelter.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AdoptieService {
    private final AdoptatorRepository adoptatorRepository;
    private final AdoptieRepository adoptieRepository;
    private final AnimalRepository animalRepository;
    public AdoptieService(AdoptatorRepository adoptatorRepository,
                             AdoptieRepository adoptieRepository,
                             AnimalRepository animalRepository){
        this.adoptatorRepository=adoptatorRepository;
        this.adoptieRepository=adoptieRepository;
        this.animalRepository=animalRepository;
    }
    @Transactional
    public void createAdoption(Integer idAnimal, Adoptator adoptator){
        AnimalDTO animal= animalRepository.findbyId(idAnimal);
        if(animal==null) throw new RuntimeException("Animalul nu exista");
        if(!animal.getStatus().equalsIgnoreCase("adapost")){
            throw new RuntimeException("Animalul nu este disponibil, status "+animal.getStatus());
        }
        Integer idFinalAdoptator;
        Integer idCautat = adoptatorRepository.findIdByTelefon(adoptator.getTelefon());

        if (idCautat != null) {
            Adoptator a2 = adoptatorRepository.findById(idCautat);
            if (!a2.getNume().equalsIgnoreCase(adoptator.getNume())) {
                throw new RuntimeException("Eroare: Numele nu se potrivește cu telefonul!");
            }
            idFinalAdoptator = a2.getIdAdoptator();//daca adoptatorul exista deja, il luam din baza de date
        } else {
            idFinalAdoptator = adoptatorRepository.add(adoptator).getIdAdoptator();//daca nu, il adaugam si dupa ii preluam id ul
        }
        adoptieRepository.add(idAnimal, idFinalAdoptator);

    }
    @Transactional
    public void processReturn(Integer idAnimal, String motiv, LocalDate data){
        AnimalDTO animal= animalRepository.findbyId(idAnimal);
        if(!animal.getStatus().equalsIgnoreCase("adoptat")){
            throw new RuntimeException("Animalul nu poate fi returnat");
        }
        adoptieRepository.returneaza(idAnimal,motiv,data);
    }
}
