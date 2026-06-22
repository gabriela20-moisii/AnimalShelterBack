package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.InterventieDTO;
import com.example.AnimalShelter.model.InterventieMedicala;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class InterventieRepository {
    private final JdbcTemplate jdbcTemplate;
    public InterventieRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<InterventieDTO> findAllByIdAnimal(Integer idAnimal){
        String sql="Select * from view_istoric_medical where id_animal=? order by data desc;";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(InterventieDTO.class),idAnimal);
    }
    public InterventieMedicala findById(Integer id){
        String sql="Select * from interventie_medicala where id_interventie=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(InterventieMedicala.class),id);
    }
    public InterventieMedicala add(InterventieDTO interventie,Integer idAnimal){
        String sql="select adauda_interventie(?,?,?,?,?,?)";
        Integer id=jdbcTemplate.queryForObject(sql, Integer.class,
                interventie.getNumeMedic(),
                interventie.getPrenumeMedic(),
                interventie.getTipInterventie(),
                interventie.getObservatii(),
                interventie.getData(),
                idAnimal
        );
        return findById(id);
    }
}
