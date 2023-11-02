package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/leases")
	public String showLeases(Model model) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		long id = lectorService.findByEmail(email).getnSocio();
		List<Prestamo> prestamos = prestamoService.getPrestamosByLectorId(id);
		
		model.addAttribute("prestamos", prestamos);
		
		return "/leases";
	}
	
	
	@GetMapping("/book/{id}/lease")
	public String leaseBook(@PathVariable(value = "id") long libroId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Prestamo p = new Prestamo();
		p.setLector(lectorService.findByEmail(email));
		Copia c = copiaService.findCopiasDisponiblesByLibroId(libroId).get(0);
		c.setEstado(EstadoCopia.PRESTADO);
		p.setCopia(c);
		p.setInicio(LocalDate.now());
		p.setFin(LocalDate.now().plusDays(30));

		prestamoService.savePrestamo(p);

		return "redirect:/book/list";
	}

	@GetMapping("/prestamo/{id}/return")
	public String returnBook(@PathVariable(value = "id") long id) {
		Prestamo p = prestamoService.getPrestamoById(id);
		if(LocalDate.now().isAfter(p.getFin())) {
			//multar
		}
		p.getCopia().setEstado(EstadoCopia.BIBLIOTECA);
		prestamoService.deletePrestamo(p);
		return "redirect:/leases";
	}
	
}
