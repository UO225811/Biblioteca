package com.capgemini.rest.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Libro;

public interface LibroRestService {
	
	Libro getBookById(long id);

	List<Libro> getAllBooks();

	void saveBook(Libro l);

	void deleteBookById(long id);

	Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder);

}
