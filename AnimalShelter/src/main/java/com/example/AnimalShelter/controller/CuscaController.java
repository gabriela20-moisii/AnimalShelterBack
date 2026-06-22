package com.example.AnimalShelter.controller;

import com.example.AnimalShelter.model.Cusca;
import com.example.AnimalShelter.repository.CuscaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adaposturi/{idAdapost}/custi")
@CrossOrigin("*")
public class CuscaController {
    private final CuscaRepository cuscaRepository;
    public CuscaController(CuscaRepository cuscaRepository){
        this.cuscaRepository=cuscaRepository;
    }
    @GetMapping
    public List<Cusca> getAll(@PathVariable Integer idAdapost){
            return cuscaRepository.listAll(idAdapost);
    }
    @GetMapping("/{idCusca}")
    public Cusca getById(@PathVariable Integer idCusca){
        return cuscaRepository.findById(idCusca);
    }
    @PostMapping
    public Cusca create(@PathVariable Integer idAdapost, @RequestBody Cusca cusca){
        cusca.setIdAdapost(idAdapost);
        return cuscaRepository.add(cusca);
    }
}
