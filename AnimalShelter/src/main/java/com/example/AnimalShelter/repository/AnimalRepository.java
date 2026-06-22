package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.AnimalDTO;
import com.example.AnimalShelter.DTO.InterventieDTO;
import com.example.AnimalShelter.model.Animal;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AnimalRepository {

    private final JdbcTemplate jdbcTemplate;
    public AnimalRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<AnimalDTO> listAll(Integer idAdapost){
        String sql="Select * from animal_detalii where id_adapost=? ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AnimalDTO.class),idAdapost);
    }
    public AnimalDTO findbyId(Integer idAnimal){
        String sql="Select * from animal_detalii where id_animal=?";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(AnimalDTO.class),idAnimal);
    }
    public AnimalDTO add(Animal animal, Integer idAdapost){
        String sql="select add_animal_adapost(?,?,?,?)";
        Integer id=jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                animal.getNume(),
                animal.getRasa(),
                animal.getDataNastere(),
                idAdapost
        );
        return findbyId(id);
    }
    public AnimalDTO update(Animal animal){
        String sql="update animal set nume=?,data_nastere=? where id_animal=?";
        jdbcTemplate.update(sql,
                animal.getNume(),
                animal.getDataNastere(),
                animal.getIdAnimal()
        );
        return findbyId(animal.getIdAnimal());
    }
    public void updateAdoptie(Integer idAnimal, String status, Integer idCusca) {
        String sql = "update animal set status=?, id_cusca=? where id_animal=?";
        jdbcTemplate.update(sql, status, idCusca, idAnimal);
    }
}
