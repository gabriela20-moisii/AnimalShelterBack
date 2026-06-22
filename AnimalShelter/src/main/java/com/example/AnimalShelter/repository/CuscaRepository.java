package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.model.Cusca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CuscaRepository {
    private final JdbcTemplate jdbcTemplate;
    public CuscaRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Cusca> listAll(Integer idAdapost){
        String sql="Select * from cusca where id_adapost=?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Cusca.class),idAdapost);
    }
    public Cusca findById(Integer idCusca){
        String sql="Select * from cusca where id_cusca=?";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Cusca.class),idCusca);
    }
    public Cusca add(Cusca cusca){
        String sql="select adauga_cusca(?,?,?,?)";
        Integer id;
        System.out.println(cusca.getCodIdentificare());
        id=jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                cusca.getCodIdentificare(),
                cusca.getIdAdapost(),
                cusca.getCapacitateMaxima(),
                cusca.getSpecie()
        );
        return findById(id);
    }
}
