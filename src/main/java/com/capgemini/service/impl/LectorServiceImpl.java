package com.capgemini.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.model.Lector;
import com.capgemini.repository.LectorRepository;
import com.capgemini.service.LectorService;

@Service
public class LectorServiceImpl implements LectorService {
	
	@Autowired
    private LectorRepository lectorRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public Lector getLectorById(long id) {
		Optional<Lector> opt = lectorRepository.findById(id);
		Lector lector = null;
		if (opt.isPresent())
			lector = opt.get();
		else
			throw new RuntimeException("Lector con id " + id + " no encontrado");
		return lector;
	}

	@Override
	public List<Lector> getAllLectores() {
		return lectorRepository.findAll();
	}

	@Override
	public void saveLector(Lector l) {
	    l.setPassword(passwordEncoder.encode(l.getPassword()));
		lectorRepository.save(l);
	}

	@Override
	public void deleteLectorById(long id) {
		lectorRepository.deleteById(id);
	}

	@Override
	public Lector findByEmail(String email) {
		Lector lector = lectorRepository.findLectorByEmail(email);
		return lector;
	}

	@Override
	public List<Lector> findAll() {
		return lectorRepository.findAll();
	}

}
