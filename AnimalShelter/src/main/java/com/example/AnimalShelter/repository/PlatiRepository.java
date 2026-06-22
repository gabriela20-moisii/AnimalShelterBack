package com.example.AnimalShelter.repository;

import com.example.AnimalShelter.model.Plata;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlatiRepository {
    private final JdbcTemplate jdbcTemplate;
    public PlatiRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Plata> getAllByShelter(Integer id){
        String sql="Select * from cheltuieli where id_adapost=? order by data_factura desc";
        return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Plata.class),id);
    }
    public void add(Integer id, Plata plata){
        String sql="Insert into cheltuieli(id_adapost, suma, descriere, data_factura) values (?,?,?,?)";
        this.jdbcTemplate.update(sql, id, plata.getSuma(), plata.getDescriere(), plata.getDataFactura());
    }
}
