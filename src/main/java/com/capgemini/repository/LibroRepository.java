package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
