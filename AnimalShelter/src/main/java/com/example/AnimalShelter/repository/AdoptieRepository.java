package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.AdoptieDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AdoptieRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdoptieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<AdoptieDTO> findAllByIdAnimal(Integer idAnimal) {
        String sql = "SELECT * FROM istoric_adoptii WHERE id_animal = ? order by data_adoptie desc";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AdoptieDTO.class), idAnimal);
    }
    public void add(Integer idAnimal, Integer idAdoptator) {
        String sql = "INSERT INTO adoptie (data_adoptie, id_animal, id_adoptator) VALUES (CURRENT_DATE, ?, ?)";
        jdbcTemplate.update(sql, idAnimal, idAdoptator);
    }
    public void returneaza(Integer idAnimal, String motiv, LocalDate data){
        String sql= "Update adoptie set data_returnare=?, motiv_returnare=? where id_animal=? and data_returnare is null";
        int randuriAfectate = jdbcTemplate.update(sql, data, motiv, idAnimal);

        if (randuriAfectate == 0) {
            throw new RuntimeException("Nu exista nicio adoptie activa pt acest animal!");
        }
    }
}