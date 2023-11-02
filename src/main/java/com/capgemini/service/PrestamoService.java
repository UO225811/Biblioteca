package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Prestamo;

public interface PrestamoService {
	public int getNumPrestamos(long lectorId);
	
	public void savePrestamo(Prestamo p);
	
	public void deletePrestamo(Prestamo p);
	
	public Prestamo getPrestamoById(long id);
	
	public List<Prestamo> getPrestamosByLectorId(long id);
	
	public List<Prestamo> getPrestamosAtrasados();
}
