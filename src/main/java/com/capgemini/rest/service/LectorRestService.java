package com.capgemini.rest.service;

import java.util.List;

import com.capgemini.model.Lector;

public interface LectorRestService {

	Lector getLectorById(long id);

	List<Lector> getAllLectores();

	void saveLector(Lector l);

	void deleteLectorById(long id);
	
	Lector findByEmail(String email);
	
}
