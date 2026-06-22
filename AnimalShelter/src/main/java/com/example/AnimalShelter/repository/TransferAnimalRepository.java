package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.TransferAnimalDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferAnimalRepository {
    private final JdbcTemplate jdbcTemplate;
    public TransferAnimalRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public void add(String oras, Integer idAnimal, Integer idAdapostSursa){
        String sql="Select proceseaza_transfer(?,?,?)";
        jdbcTemplate.queryForList(sql,idAnimal,idAdapostSursa,oras);
    }
    public List<TransferAnimalDTO> findAllByAnimalId(Integer idAnimal){
        String sql="Select * from view_istoric_transfer where id_animal=? order by data_transfer desc";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TransferAnimalDTO.class),idAnimal);
    }
}
