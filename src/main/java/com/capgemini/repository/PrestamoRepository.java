package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	@Query(value= "SELECT count(*) from prestamos where fk_lector= :lectorid", nativeQuery = true)
	int getPrestamoCountByLectorId(@Param("lectorid") long lectorId);

}
