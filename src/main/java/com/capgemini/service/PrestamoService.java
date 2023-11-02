package com.capgemini.service;

import com.capgemini.model.Prestamo;

public interface PrestamoService {
	public int getNumPrestamos(long lectorId);
	
	public void savePrestamo(Prestamo p);
}
