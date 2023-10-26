package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Copia;
import com.capgemini.repository.CopiaRepository;
import com.capgemini.service.CopiaService;

@Service
public class CopiaServiceImpl implements CopiaService{

	@Autowired
	private CopiaRepository copiaRepository;

	@Override
	public Copia getCopyById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Copia> getAllCopies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCopy(Copia c) {
		copiaRepository.save(c);
		
	}

	@Override
	public void deleteCopyById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfAvailableCopiesById() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Copia> findCopiasByLibroId(long libroId) {
		return copiaRepository.findCopiasByLibroId(libroId);
	} 
}
