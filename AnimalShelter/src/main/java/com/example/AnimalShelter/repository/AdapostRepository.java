package com.example.AnimalShelter.repository;

import java.util.*;

import com.example.AnimalShelter.DTO.AdapostDTO;
import com.example.AnimalShelter.model.Adapost;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Repository
public class AdapostRepository {
    private final JdbcTemplate jdbcTemplate;
    public AdapostRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<AdapostDTO> findAll(){
        String sql="Select * from adapost_detalii";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AdapostDTO.class));
        //se mapeaza automat un nou obiect Adapost cu toate campurile
    }
    public AdapostDTO findById(Integer id){
        String sql="Select * from adapost_detalii where id_adapost=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AdapostDTO.class),id);
    }
    public AdapostDTO add(Adapost adapost){
        String sql = "select adauga_adapost(?, ?, ?)";
        Integer idGenerat = jdbcTemplate.queryForObject(
                sql,
                Integer.class, // Îi spunem lui Spring că ne așteptăm la un număr întreg
                adapost.getNume(),
                adapost.getAdresa(),
                adapost.getOras()
        );

        return findById(idGenerat);
    }
    public AdapostDTO update(Adapost adapost){
        String sql = "UPDATE adapost SET nume = ?, adresa = ?, id_oras = ? WHERE id_adapost = ?";
        jdbcTemplate.update(sql, adapost.getNume(), adapost.getAdresa(), adapost.getIdOras(), adapost.getIdAdapost());
        return findById(adapost.getIdAdapost());
    }
    public String delete(Integer id){
        String sql="delete from adapost where id_adapost=?";
        jdbcTemplate.update(sql,id);
        return "Adapostul cu id= "+id+ " a fost sters";
    }
}
