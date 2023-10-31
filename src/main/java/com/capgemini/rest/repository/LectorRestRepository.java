package com.capgemini.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.model.Lector;

public interface LectorRestRepository extends CrudRepository<Lector, Long> {
	Lector findById(long id);
	Lector save(Lector u);
	void delete(Lector u);
	List<Lector> findAll();
	Lector findByEmail(String email);
}
