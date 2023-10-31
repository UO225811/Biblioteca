package com.capgemini.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Lector;
import com.capgemini.rest.repository.LectorRestRepository;
import com.capgemini.rest.service.LectorRestService;

@Service
public class LectorRestServiceImpl implements LectorRestService {
	@Autowired
	private LectorRestRepository repo;
	
	@Override
	public Lector getLectorById(long id) {
		return repo.findById(id);
	}

	@Override
	public List<Lector> getAllLectores() {
		return repo.findAll();
	}

	@Override
	public void saveLector(Lector l) {
		repo.save(l);
	}

	@Override
	public void deleteLectorById(long id) {
		repo.deleteById(id);
	}

	@Override
	public Lector findByEmail(String email) {
		return repo.findByEmail(email);
	}

}
