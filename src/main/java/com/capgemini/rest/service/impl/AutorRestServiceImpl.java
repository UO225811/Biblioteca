package com.capgemini.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Autor;
import com.capgemini.rest.repository.AutorRestRepository;
import com.capgemini.rest.service.AutorRestService;

@Service
public class AutorRestServiceImpl implements AutorRestService {

	@Autowired
	private AutorRestRepository repo;

	@Override
	public Autor getAuthorById(long id) {
		return repo.findById(id);
	}

	@Override
	public List<Autor> getAllAutores() {
		return repo.findAll();
	}

	@Override
	public Autor saveAutor(Autor l) {
		return repo.save(l);
	}

	@Override
	public void deleteAutorById(long id) {
		repo.deleteById(id);
	}

}
