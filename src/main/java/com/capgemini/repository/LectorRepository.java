package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long>{
	
	@Query(value= "SELECT * FROM lectores WHERE email= :email", nativeQuery = true)
	Lector findLectorByEmail(@Param("email") String email);

}
