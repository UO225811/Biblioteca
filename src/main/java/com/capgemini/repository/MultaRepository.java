package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Multa;

public interface MultaRepository extends JpaRepository<Multa, Long> {

	
	@Query(value= "SELECT * from multas where fk_lector= :lectorid", nativeQuery = true)
	Multa getByLectorId(@Param("lectorid") long lectorId);
	
	@Query(value= "SELECT * from multas where fk_lector= :lectorid", nativeQuery = true)
	void deleteByLectorId(@Param("lectorid") long lectorId);
}
