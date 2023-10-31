package com.capgemini.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.model.Autor;

public interface AutorRestRepository extends CrudRepository<Autor, Long> {
	Autor findById(long id);
	Autor save(Autor u);
	void delete(Autor u);
	List<Autor> findAll();
}
