package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.AngajatDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AngajatRepository {
    private final JdbcTemplate jdbcTemplate;
    public AngajatRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<AngajatDTO> listAll(Integer idAdapost){
        String sql="select * from angajat_detalii where id_adapost=?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AngajatDTO.class),idAdapost);
    }
    public AngajatDTO findById(Integer idAngajat){
        String sql="select * from angajat_detalii where id_angajat=?";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(AngajatDTO.class),idAngajat);
    }
    public AngajatDTO add(AngajatDTO angajat,Integer idAdapost){
        String sql="select adaugare_angajat_adapost(?,?,?,?,?,?)";
        Integer id=jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                angajat.getNumeAngajat(),
                angajat.getPrenume(),
                angajat.getTelefon(),
                angajat.getSalariu(),
                idAdapost,
                angajat.getFunctie()
        );
        return findById(id);
    }
}
