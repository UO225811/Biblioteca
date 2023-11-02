package com.capgemini.service.impl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public void deletePrestamo(Prestamo p) {
		prestamoRepository.delete(p);		
	}

	@Override
	public Prestamo getPrestamoById(long id) {
		Optional<Prestamo> opt = prestamoRepository.findById(id);
		Prestamo prestamo = null;
		if (opt.isPresent())
			prestamo = opt.get();
		else
			throw new RuntimeException("Prestamo with id " + id + " not found");
		return prestamo;
	}

	@Override
	public List<Prestamo> getPrestamosByLectorId(long id) {
		return prestamoRepository.getPrestamosByLectorId(id);
	}

	
}
