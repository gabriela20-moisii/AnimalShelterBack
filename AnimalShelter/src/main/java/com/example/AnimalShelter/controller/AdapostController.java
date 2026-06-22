package com.example.AnimalShelter.controller;

import com.example.AnimalShelter.DTO.AdapostDTO;
import com.example.AnimalShelter.DTO.StatisticiDTO;
import com.example.AnimalShelter.model.Adapost;
import com.example.AnimalShelter.model.Plata;
import com.example.AnimalShelter.model.Venit;
import com.example.AnimalShelter.repository.AdapostRepository;
import com.example.AnimalShelter.repository.VenituriRepository;
import com.example.AnimalShelter.service.FinanteService;
import com.example.AnimalShelter.service.StatisticiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/adaposturi")
@CrossOrigin("*")//se accespta requesteri de pe orice port
public class AdapostController {
    private final AdapostRepository adapostRepository;
    private final StatisticiService statisticiService;
    private final VenituriRepository venituriRepository;
    private final FinanteService finanteService;

    public AdapostController(AdapostRepository ar, StatisticiService statisticiRepository, VenituriRepository venituriRepository, FinanteService finanteService){
        this.adapostRepository=ar;
        this.statisticiService =statisticiRepository;
        this.venituriRepository = venituriRepository;
        this.finanteService = finanteService;
    }
    @GetMapping
    public List<AdapostDTO> getAllShelters(){
        return adapostRepository.findAll();
    }
    @GetMapping("/{id}") //cand apasam pe un singur adapost
    public AdapostDTO getById(@PathVariable Integer id){
        return adapostRepository.findById(id);
    }
    @PostMapping
    public AdapostDTO create(@RequestBody Adapost adapost){
        return adapostRepository.add(adapost);
    }
    @PutMapping("/{id}")
    public AdapostDTO update(@RequestBody Adapost adapost,@PathVariable Integer id){
        adapost.setIdAdapost(id);
        return adapostRepository.update(adapost);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){ return  adapostRepository.delete(id); }
    @GetMapping("/{id}/statistics")
    public List<StatisticiDTO> statistcs(@PathVariable Integer id){
        return statisticiService.getStatistics(id);
    }
    @GetMapping("/{id}/income")
    public List<Venit> income(@PathVariable Integer id){
        return finanteService.getIncome(id);
    }
    @GetMapping("/{id}/payments")
    public List<Plata> payments(@PathVariable Integer id){
        return finanteService.getPayments(id);
    }
    @PostMapping("/{id}/income")
    public ResponseEntity<String> addIncome(@PathVariable Integer id, @RequestBody Venit venit){
        try {
            finanteService.addIncome(id,venit);
            return ResponseEntity.ok("Inregistrare venit realizata cu succes");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{id}/payments")
    public ResponseEntity<String> addPayment(@PathVariable Integer id, @RequestBody Plata plata){
        try{
            finanteService.addPayments(id,plata);
            return ResponseEntity.ok("Plata inregistrata cu succes");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
