package com.example.AnimalShelter.service;

import com.example.AnimalShelter.DTO.StatisticiDTO;
import com.example.AnimalShelter.repository.StatisticiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticiService {
    private final StatisticiRepository statisticiRepository;

    StatisticiService(StatisticiRepository statisticiRepository){
        this.statisticiRepository=statisticiRepository;
    }

    public List<StatisticiDTO> getStatistics(Integer idAdapost){
        return statisticiRepository.getAllForShelter(idAdapost);
    }

}
