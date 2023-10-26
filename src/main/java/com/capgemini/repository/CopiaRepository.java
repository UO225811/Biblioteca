package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Copia;

public interface CopiaRepository extends JpaRepository<Copia, Long> {
	
	@Query(value = "SELECT count(*) from copias where fk_libro= :libroid", nativeQuery = true)
	int getNumCopiasByLibroId(@Param("libroid") long libroId);

	@Query(value= "SELECT * FROM copias WHERE fk_libro= :libroid", nativeQuery = true)
	List<Copia> findCopiasByLibroId(@Param("libroid") long libroId);

}
