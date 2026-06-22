package com.example.AnimalShelter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class AnimalShelterApplication {

	public static void main(String[] args) {

		SpringApplication.run(AnimalShelterApplication.class, args);
	}


}
