package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.repository.PrestamoRepository;
import com.capgemini.service.PrestamoService;

public class PrestamoServiceImpl implements PrestamoService{

	@Autowired PrestamoRepository prestamoRepository;

	@Override
	public int getNumPrestamos(long lectorId) {
		return prestamoRepository.getPrestamoCountByLectorId(lectorId);
	}

}
