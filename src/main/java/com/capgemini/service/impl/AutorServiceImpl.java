package com.capgemini.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Autor;
import com.capgemini.repository.AutorRepository;
import com.capgemini.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public Autor getAuthorById(long id) {
		Optional<Autor> opt = autorRepository.findById(id);
		Autor autor = null;
		if (opt.isPresent())
			autor = opt.get();
		else
			throw new RuntimeException("Author with id " + id + " not found");
		return autor;
	}

}
