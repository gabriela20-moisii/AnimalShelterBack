package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.StatisticiDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatisticiRepository {
    private final JdbcTemplate jdbcTemplate;

    public StatisticiRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<StatisticiDTO> getAllForShelter(Integer id){
        String sql="Select * from statistici_adoptie_adapost where id_adapost=? order by rata_adoptie_procent desc";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StatisticiDTO.class),id);
    }
}
