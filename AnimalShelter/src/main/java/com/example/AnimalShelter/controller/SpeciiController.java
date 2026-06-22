package com.example.AnimalShelter.controller;


import com.example.AnimalShelter.model.Rasa;
import com.example.AnimalShelter.repository.SpeciiRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specii")
@CrossOrigin("*")//se accespta requesteri de pe orice port
public class SpeciiController {
    private final SpeciiRepository speciiRepository;
    public SpeciiController(SpeciiRepository speciiRepository){
        this.speciiRepository=speciiRepository;
    }
    @GetMapping
    public List<Rasa> getAll(){
        return  speciiRepository.findAll();
    }
}
