package com.capgemini.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Libro;
import com.capgemini.rest.repository.LibroRestRepository;
import com.capgemini.rest.service.LibroRestService;

@Service
public class LibroRestServiceImpl implements LibroRestService {

	@Autowired
	LibroRestRepository repository;
	
	@Override
	public Libro getBookById(long id) {		
		return repository.findById(id);
	}

	@Override
	public List<Libro> getAllBooks() {
		return repository.findAll();
	}

	@Override
	public Libro saveBook(Libro l) {
		return repository.save(l);
	}

	@Override
	public void deleteBookById(long id) {
		repository.delete(repository.findById(id));
	}

	@Override
	public Libro modificar(Libro l) {
		Libro original = repository.findById(l.getId()).get();
		
		if(l.getTitulo() != null)
			original.setTitulo(l.getTitulo());
		if(l.getAutor() != null)
			original.setAutor(l.getAutor());
		if(l.getCopias() != null)
			original.setCopias(l.getCopias());
		if(l.getEditorial() != null)
			original.setEditorial(l.getEditorial());
		if(l.getAnyo() != 0)
			original.setAnyo(l.getAnyo());
		
		
		return repository.save(original);
	}

	
}
