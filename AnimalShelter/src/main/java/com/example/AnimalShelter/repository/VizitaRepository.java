package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.DTO.VizitaDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VizitaRepository {
    private final JdbcTemplate jdbcTemplate;
    public VizitaRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<VizitaDTO> getAllByIdAngajat(Integer idAngajat){
        String sql="Select * from view_istoric_vizite where id_angajat=? order by data_vizita desc";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VizitaDTO.class),idAngajat);
    }
    public void add(VizitaDTO vizita,Integer idAngajat,Integer idAdapost){
        String sql="Select adauga_vizita(?,?,?,?,?,?)";
        jdbcTemplate.queryForObject(sql, Integer.class,
                idAdapost,
                idAngajat,
                vizita.getOrasVizitat(),
                vizita.getMotiv(),
                vizita.getDataVizita(),
                vizita.getDurataZile()
                );
    }

}
