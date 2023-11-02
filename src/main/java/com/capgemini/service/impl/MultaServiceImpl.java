package com.capgemini.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Lector;
import com.capgemini.model.Multa;
import com.capgemini.repository.LectorRepository;
import com.capgemini.repository.MultaRepository;
import com.capgemini.service.MultaService;

@Service
public class MultaServiceImpl implements MultaService {

	@Autowired
	private MultaRepository multaRepository;
	@Autowired
	private LectorRepository lectorRepository;

	@Override
	public void multar(LocalDate finPrestamo, Lector l) {
		// calcular dias de multa
		long multa = ChronoUnit.DAYS.between(finPrestamo, LocalDate.now()) * 2;
		// ver si ya hay una multa
		Multa m = null;
		// si hay, sumar los dias (actualizar)
		if (l.getMulta() != null) {
			m = l.getMulta();
			m.setfFin(m.getfFin().plusDays(multa));
		}
		// else: crear ulta con duracion
		else {
			m = new Multa();
			m.setfInicio(LocalDate.now());
			m.setfFin(LocalDate.now().plusDays(multa));
		}
		l.setMulta(m);
		multaRepository.save(m);
		lectorRepository.save(l);
	}

	@Override
	public Multa getMultaByLectorId(long id) {
		return multaRepository.getByLectorId(id);
	}

	@Override
	public void deleteMultaByLectorId(long id) {

		multaRepository.deleteByLectorId(id);
	}

	@Override
	public void saveMulta(Multa m) {
		multaRepository.save(m);
	}

	@Override
	public void updateMulta(Multa m) {
		multaRepository.save(m);
	}

	@Override
	public List<Multa> findAll() {
		return multaRepository.findAll();
	}

	@Override
	public void deleteMulta(Multa m) {
		multaRepository.delete(m);
	}

}
