package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.model.Adoptator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdoptatorRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public AdoptatorRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Adoptator> findAll(){
        String sql="Select * from adoptator";
        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Adoptator.class));
    }
    public Integer findIdByTelefon(String telefon){
        String sql="Select id_adoptator from adoptator where telefon=?";
        try{
            return jdbcTemplate.queryForObject(sql,Integer.class,telefon);
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public Adoptator findById(Integer id){
        String sql="Select * from adoptator where id_adoptator=?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Adoptator.class),id);
    }
    public Adoptator add(Adoptator adoptator){
        String sql="Insert into adoptator (nume,prenume,telefon,email) values (?,?,?,?) returning id_adoptator ";
        Integer id=jdbcTemplate.queryForObject(sql,Integer.class, adoptator.getNume(),adoptator.getPrenume(),adoptator.getTelefon(),adoptator.getEmail());
        return findById(id);
    }

}
