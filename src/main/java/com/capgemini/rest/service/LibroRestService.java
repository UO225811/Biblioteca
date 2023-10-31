package com.capgemini.rest.service;

import java.util.List;


import com.capgemini.model.Libro;

public interface LibroRestService {

	Libro getBookById(long id);

	List<Libro> getAllBooks();

	Libro saveBook(Libro l);
	
	Libro modificar(Libro l);

	void deleteBookById(long id);
}
