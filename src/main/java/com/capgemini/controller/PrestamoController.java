package com.capgemini.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.model.Copia;
import com.capgemini.model.Prestamo;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LectorService;
import com.capgemini.service.LibroService;
import com.capgemini.service.PrestamoService;

@Controller
public class PrestamoController {

	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private LectorService lectorService;
	@Autowired
	private CopiaService copiaService;

	@GetMapping("/book/{id}/lease")
	public String leaseBook(@PathVariable(value = "id") long libroId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Prestamo p = new Prestamo();
		p.setLector(lectorService.findByEmail(email));
		Copia c = copiaService.findCopiasByLibroId(libroId).get(0);
		c.setEstado(EstadoCopia.PRESTADO);
		p.setCopia(c);
		p.setInicio(LocalDate.now());
		p.setFin(LocalDate.now().plusDays(30));

		prestamoService.savePrestamo(p);

		return "redirect:/book/list";
	}

}
