package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Copia;

public interface CopiaRepository extends JpaRepository<Copia, Long> {
	
	@Query("SELECT count(*) from copias where fk_libro= :libroid")
	int getNumCopiasByLibroId(@Param("libroid") long libroId);

	@Query("INSERT INTO copias(estado,fk_libro) values(3,:libroid)")
	int createCopiasByLibroId(@Param("libroid") long libroId, int numCopias);
	
}
