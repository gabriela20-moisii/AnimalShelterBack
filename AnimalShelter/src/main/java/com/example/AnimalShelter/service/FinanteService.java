package com.example.AnimalShelter.service;

import com.example.AnimalShelter.model.Plata;
import com.example.AnimalShelter.model.Venit;
import com.example.AnimalShelter.repository.PlatiRepository;
import com.example.AnimalShelter.repository.VenituriRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanteService {
    private final PlatiRepository platiRepository;
    private final VenituriRepository venituriRepository;
    public FinanteService(PlatiRepository platiRepository, VenituriRepository venituriRepository){
        this.platiRepository=platiRepository;
        this.venituriRepository=venituriRepository;
    }
    public List<Venit> getIncome(Integer id){
        return venituriRepository.getAllByShelter(id);
    }
    public List<Plata> getPayments(Integer id){
        return platiRepository.getAllByShelter(id);
    }
    public void addIncome(Integer id,Venit venit){
         venituriRepository.add(id, venit);
    }
    public void addPayments(Integer id,Plata plata){
         platiRepository.add(id,plata);
    }

}
