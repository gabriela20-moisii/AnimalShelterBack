package com.example.AnimalShelter.service;

import com.example.AnimalShelter.DTO.AnimalDTO;
import com.example.AnimalShelter.DTO.InterventieDTO;
import com.example.AnimalShelter.model.Animal;
import com.example.AnimalShelter.model.InterventieMedicala;
import com.example.AnimalShelter.repository.AdoptieRepository;
import com.example.AnimalShelter.repository.AnimalRepository;
import com.example.AnimalShelter.repository.InterventieRepository;
import com.example.AnimalShelter.repository.TransferAnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final InterventieRepository interventieRepository;
    private final AdoptieRepository adoptieRepository;
    private final TransferAnimalRepository transferAnimalRepository;

    public AnimalService(AnimalRepository animalRepository, InterventieRepository interventieRepository, AdoptieRepository adoptieRepository, TransferAnimalRepository transferAnimalRepository){
        this.animalRepository=animalRepository;
        this.interventieRepository=interventieRepository;
        this.adoptieRepository = adoptieRepository;
        this.transferAnimalRepository = transferAnimalRepository;
    }
    public List<AnimalDTO> getAllAnimals(Integer idAdapost){
        return animalRepository.listAll(idAdapost);
    }
    public AnimalDTO getById(Integer idAnimal){

        AnimalDTO animal=animalRepository.findbyId(idAnimal);
        animal.setIstoricMedical(interventieRepository.findAllByIdAnimal(idAnimal));
        animal.setIstoricAdoptie(adoptieRepository.findAllByIdAnimal(idAnimal));
        animal.setIstoricTransfer(transferAnimalRepository.findAllByAnimalId(idAnimal));
        return animal;
    }
    public AnimalDTO create(Animal animal, Integer idAdapost){
        return animalRepository.add(animal,idAdapost);
    }
    public AnimalDTO update( Animal animal, Integer idAnimal){
        animal.setIdAnimal(idAnimal);
        return animalRepository.update(animal);
    }
    public InterventieMedicala addIntervention(InterventieDTO interventie, Integer idAnimal){
        return interventieRepository.add(interventie, idAnimal);
    }
    public void addTransfer(String oras, Integer idAnimal, Integer idAdapost){
        transferAnimalRepository.add(oras, idAnimal,idAdapost);
    }
}
