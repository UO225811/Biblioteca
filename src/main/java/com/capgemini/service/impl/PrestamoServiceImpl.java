package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Prestamo;
import com.capgemini.repository.PrestamoRepository;
import com.capgemini.service.PrestamoService;

@Service
public class PrestamoServiceImpl implements PrestamoService{

	@Autowired PrestamoRepository prestamoRepository;

	@Override
	public int getNumPrestamos(long lectorId) {
		return prestamoRepository.getPrestamoCountByLectorId(lectorId);
	}

	@Override
	public void savePrestamo(Prestamo p) {
		prestamoRepository.save(p);		
	}

}
