package com.example.AnimalShelter.controller;

import com.example.AnimalShelter.model.Adoptator;
import com.example.AnimalShelter.repository.AdoptatorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptatori")
@CrossOrigin("*")
public class AdoptatorController {
    private final AdoptatorRepository adoptatorRepository;
    public AdoptatorController(AdoptatorRepository adoptatorRepository){
        this.adoptatorRepository=adoptatorRepository;
    }
    @GetMapping
    public List<Adoptator> listAll(){
        return adoptatorRepository.findAll();
    }
    @PostMapping
    public Adoptator create(@RequestBody Adoptator adoptator){
        Integer id=adoptatorRepository.findIdByTelefon(adoptator.getTelefon());
        if(id!=null){
            Adoptator a2=adoptatorRepository.findById(id);
            if(!a2.getNume().equalsIgnoreCase(adoptator.getNume()) || !adoptator.getPrenume().equalsIgnoreCase(a2.getPrenume()))
                throw new RuntimeException("Acest nr de telefon exista deja, dar persoana nu corespunde!");
            return a2;
        }
        return adoptatorRepository.add(adoptator);
    }
    @GetMapping("/{id}")
    public Adoptator getById(@PathVariable Integer id) {
        return adoptatorRepository.findById(id);
    }
}
