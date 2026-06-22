package com.example.AnimalShelter.controller;

import com.example.AnimalShelter.DTO.AnimalDTO;
import com.example.AnimalShelter.DTO.InterventieDTO;
import com.example.AnimalShelter.DTO.MotivDTO;
import com.example.AnimalShelter.DTO.TransferAnimalDTO;
import com.example.AnimalShelter.model.Adoptator;
import com.example.AnimalShelter.model.Animal;
import com.example.AnimalShelter.model.InterventieMedicala;

import com.example.AnimalShelter.service.AdoptieService;
import com.example.AnimalShelter.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/adaposturi/{idAdapost}/animale")
@CrossOrigin("*")public class AnimalController {
    private final AdoptieService adoptieService;
    private final AnimalService animalService;

    public AnimalController( AdoptieService adoptieService, AnimalService animalService){
        this.adoptieService = adoptieService;
        this.animalService = animalService;
    }
    @GetMapping
    public List<AnimalDTO> getAllAnimals(@PathVariable Integer idAdapost){
        return animalService.getAllAnimals(idAdapost);
    }
    @GetMapping("/{idAnimal}")
    public AnimalDTO getById(@PathVariable Integer idAnimal){

        return animalService.getById(idAnimal);
    }
    @PostMapping
    public AnimalDTO create(@RequestBody Animal animal, @PathVariable Integer idAdapost){
        return animalService.create(animal,idAdapost);
    }
    @PutMapping("/{idAnimal}")
    public AnimalDTO update(@RequestBody Animal animal,@PathVariable Integer idAnimal){
        return animalService.update(animal, idAnimal);
    }
    @PostMapping("/{idAnimal}/adoptie")
    public ResponseEntity<String> adopt(@PathVariable Integer idAnimal, @RequestBody Adoptator adoptator){
        try {
            adoptieService.createAdoption(idAnimal, adoptator);
            return ResponseEntity.ok("Adopție realizată cu succes pentru animalul " + idAnimal);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{idAnimal}/returnare")
    public ResponseEntity<String> returnare(@PathVariable Integer idAnimal, @RequestBody MotivDTO motiv){
        try {
            adoptieService.processReturn(idAnimal, motiv.getMotiv(), LocalDate.now());
            return ResponseEntity.ok("Returnare procesata! Animalul " + idAnimal + " este din nou in adapost.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{idAnimal}/adauga-interventie")
    public InterventieMedicala addIntervention(@PathVariable Integer idAnimal, @RequestBody InterventieDTO interventie){
        return animalService.addIntervention(interventie,idAnimal);
    }
    @PostMapping("/{idAnimal}/transfer")
    public ResponseEntity<String> transfer(@PathVariable Integer idAnimal, @PathVariable Integer idAdapost, @RequestBody TransferAnimalDTO transfer){
        try{
            animalService.addTransfer(transfer.getOrasDestinatie(), idAnimal, idAdapost);
            return ResponseEntity.ok("Transferul a fost realizat cu succes");
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
