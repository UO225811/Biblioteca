package com.capgemini.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Copia;

public interface CopiaService {
	Copia getCopyById(long id);

	List<Copia> getAllCopies();

	void saveCopy(Copia c);

	void deleteCopyById(long id);
	
	int getNumberOfAvailableCopiesById(long id);
	
	List<Copia> findCopiasByLibroId(long libroId);
	
	List<Copia> findCopiasDisponiblesByLibroId(long libroId);
	
	// Objeto página
	Page<Copia> findPaginated(long libroId, int pageNum, int pageSize, String sortField, String sortOrder);

}
