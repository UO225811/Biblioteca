package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.model.Lector;
import com.capgemini.model.Multa;

public interface MultaService {

	public void multar(LocalDate fin, Lector l);
	
	public Multa getMultaByLectorId(long id);
	
	public void deleteMultaByLectorId(long id);
	
	public void saveMulta(Multa m);
	
	public void updateMulta(Multa m);
	
	public List<Multa> findAll();

	public void deleteMulta(Multa m);
}
