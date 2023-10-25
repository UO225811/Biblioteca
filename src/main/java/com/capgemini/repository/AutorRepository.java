package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
