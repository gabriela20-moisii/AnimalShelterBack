package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.model.Rasa;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpeciiRepository {
    private final JdbcTemplate jdbcTemplate;
    public SpeciiRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Rasa> findAll(){
        return this.jdbcTemplate.query("Select nume from specie", BeanPropertyRowMapper.newInstance(Rasa.class));
    }
}
