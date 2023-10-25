package com.capgemini.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capgemini.model.Libro;
import com.capgemini.service.LibroService;

public class LibroServiceImpl implements LibroService {

	@Override
	public Libro getBookById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Libro> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBook(Libro l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBookById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Libro> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
