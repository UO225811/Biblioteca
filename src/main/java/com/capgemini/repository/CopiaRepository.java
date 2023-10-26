package com.capgemini.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Copia;

public interface CopiaRepository extends JpaRepository<Copia, Long> {
	
	@Query("SELECT count(*) from copias where fk_libro= :libroid")
	int getNumCopiasByLibroId(@Param("libroid") long libroId);

	@Query("SELECT * FROM copias WHERE fk_libro= :libroid")
	Set<Copia> findCopiasByLibroId(@Param("libroid") long libroId);

}
