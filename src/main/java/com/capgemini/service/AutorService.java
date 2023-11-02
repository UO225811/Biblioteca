package com.capgemini.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Autor;

public interface AutorService {

	public Autor getAuthorById(long id);

	List<Autor> getAllAutores();

	void saveAutor(Autor l);

	void deleteAutorById(long id);
	
	void updateAutor(Autor autor);
	
	Page<Autor> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder);

}
