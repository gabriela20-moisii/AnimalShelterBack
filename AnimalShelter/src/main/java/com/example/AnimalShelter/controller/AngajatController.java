package com.example.AnimalShelter.controller;

import com.example.AnimalShelter.DTO.AngajatDTO;
import com.example.AnimalShelter.DTO.VizitaDTO;
import com.example.AnimalShelter.service.AngajatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adaposturi/{idAdapost}/angajati")
@CrossOrigin("*")
public class AngajatController {
    private final AngajatService angajatService;
    public AngajatController(AngajatService angajatService){

        this.angajatService =angajatService;
    }
    @GetMapping
    public List<AngajatDTO> getAll(@PathVariable Integer idAdapost){
        return angajatService.getAll(idAdapost);
    }
    @GetMapping("/{idAngajat}")
    public AngajatDTO getById(@PathVariable Integer idAngajat){
        return angajatService.getById(idAngajat);
    }
    @PostMapping
    public AngajatDTO create(@RequestBody AngajatDTO angajat,@PathVariable Integer idAdapost){
        return angajatService.create(angajat,idAdapost);
    }
    @PostMapping("/{idAngajat}")
    public ResponseEntity<String> addVizita(@RequestBody VizitaDTO vizta, @PathVariable Integer idAngajat, @PathVariable Integer idAdapost){
        try{
            angajatService.addVizita(vizta, idAngajat, idAdapost);
            return ResponseEntity.ok("Vizita a fost inregistrata cu succes!");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
