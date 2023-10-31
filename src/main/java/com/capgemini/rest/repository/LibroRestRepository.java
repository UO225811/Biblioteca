package com.capgemini.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.model.Libro;

public interface LibroRestRepository extends CrudRepository<Libro, Long> {

	Libro findById(long id);
	List<Libro> findAll();
	Libro save(Libro u);
	void delete(Libro u);
}
