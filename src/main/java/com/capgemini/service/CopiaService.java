package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Copia;

public interface CopiaService {
	Copia getCopyById(long id);

	List<Copia> getAllCopies();

	void saveCopy(Copia c);

	void deleteCopyById(long id);
	
	int getNumberOfAvailableCopiesByTitle();

}
