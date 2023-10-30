package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Autor;

public interface AutorService {

	public Autor getAuthorById(long id);

	List<Autor> getAllAutores();

	void saveAutor(Autor l);

	void deleteAutorById(long id);

}
