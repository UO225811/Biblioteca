package com.capgemini.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Autor;

public interface AutorRestService {

	public Autor getAuthorById(long id);

	List<Autor> getAllAutores();

	void saveAutor(Autor l);

	void deleteAutorById(long id);
	
}
