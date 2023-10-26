package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.Autor;
import com.capgemini.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
}
