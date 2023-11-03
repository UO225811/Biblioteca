package com.capgemini.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

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
		long diasMulta = ChronoUnit.DAYS.between(finPrestamo, LocalDate.now()) * 2;

		l.setMultado(true);
		
		// si hay, sumar los dias (actualizar)
		Set<Multa> multas = l.getMultas();
		boolean isMultado = false;
		for (Multa m : multas) {
			if (m.getfFin().isAfter(LocalDate.now())) {
				m.setfFin(m.getfFin().plusDays(diasMulta));
				isMultado = true;
				multaRepository.save(m);
			}
		}

		if (!isMultado) {
			Multa multa = new Multa();
			multa.setfInicio(LocalDate.now());
			multa.setfFin(LocalDate.now().plusDays(diasMulta));
			multa.setLector(l);

			l.addMulta(multa);
			multaRepository.save(multa);
		}
		
//		lectorRepository.save(l);
	}

	@Override
	public Multa getMultaByLectorId(long id) {
		return multaRepository.getByLectorId(id);
	}

	@Override
	public void deleteMultaByLectorId(long id) {
		multaRepository.deleteById(id);
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
