package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.capgemini.model.Copia;
import com.capgemini.model.Multa;
import com.capgemini.model.Prestamo;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.CopiaService;
import com.capgemini.service.MultaService;
import com.capgemini.service.PrestamoService;

@Controller
public class MultaController {

	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private MultaService multaService;
	@Autowired
	private CopiaService copiaService;
	
	@GetMapping("/multas")
	public String gestionaMultas() {
		// lista de prestamos retrasados
		List<Prestamo> prestamos = prestamoService.getPrestamosAtrasados();
		for(Prestamo p: prestamos) {
			Copia c = p.getCopia();
			c.setEstado(EstadoCopia.RETRASO);
			copiaService.saveCopy(c);
			// marcar al lector
		}

		List<Multa> multas = multaService.findAll();
		for(Multa m: multas) {
			if(m.getfFin().isBefore(LocalDate.now())){
				multaService.deleteMulta(m);
			}
		}
		return "/home";
	}
	
}
