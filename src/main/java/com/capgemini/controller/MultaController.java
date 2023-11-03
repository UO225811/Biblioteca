package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.capgemini.model.Copia;
import com.capgemini.model.Lector;
import com.capgemini.model.Multa;
import com.capgemini.model.Prestamo;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LectorService;
import com.capgemini.service.PrestamoService;

@Controller
public class MultaController {

	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private CopiaService copiaService;
	@Autowired
	private LectorService lectorService;

	@GetMapping("/multas")
	public String gestionaMultas() {

		List<Lector> lectores = lectorService.findAll();

		for (Lector l : lectores) {
			boolean isMultado = false;
			Set<Multa> multas = l.getMultas();
			for (Multa m : multas) {
				if (m.getfFin().isAfter(LocalDate.now())) {
					isMultado = true;
					break;
				}
			}
			l.setMultado(isMultado);
//			lectorService.saveLector(l);
		}

		// lista de prestamos retrasados
		List<Prestamo> prestamos = prestamoService.getPrestamosAtrasados();
		for (Prestamo p : prestamos) {
			Copia c = p.getCopia();
			c.setEstado(EstadoCopia.RETRASO);
			copiaService.saveCopy(c);
			// marcar al lector
			Lector lector = p.getLector();
			lector.setMultado(true);
			lectorService.saveLector(lector);
		}

		return "/home";
	}

}
