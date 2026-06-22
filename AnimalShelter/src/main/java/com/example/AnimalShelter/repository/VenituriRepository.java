package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.model.Plata;
import com.example.AnimalShelter.model.Venit;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VenituriRepository {
    private final JdbcTemplate jdbcTemplate;
    public VenituriRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Venit> getAllByShelter(Integer id){
        String sql="Select * from venituri where id_adapost=? order by data_incasare desc";
        return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Venit.class),id);
    }
    public void add(Integer id, Venit venit){
        String sql="Insert into venituri(id_adapost, suma,descriere, data_incasare) values (?,?,?,?)";
        this.jdbcTemplate.update(sql,
                id, venit.getSuma(), venit.getDescriere(),venit.getDataIncasare());
    }
}
