package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.Copia;
import com.capgemini.model.Libro;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LibroService;

@Controller
public class CopiaController {

	@Autowired
	private CopiaService copiaService;
	@Autowired
	private LibroService libroService;
	
	@GetMapping("/book/{id}/copies/{pageNumber}")
	public String findPaginated(@PathVariable(value="id") long libroId, @PathVariable(value = "pageNumber") int pageNum,
			@RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder, Model model) {
		int pageSize = 4;
		Page<Copia> page = copiaService.findPaginated(libroId, pageNum, pageSize, sortField, sortOrder);
		List<Copia> copies = page.getContent();

		model.addAttribute("sortOrder", sortOrder);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("copies", copies);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("reverseSortDir", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");

		return "copy/list";
	}
	
	
	@GetMapping("/copy/update/{id}")
	public String showFormForUpdating(@PathVariable(value="id") long id, Model model) {
		Copia copia = this.copiaService.getCopyById(id);
		model.addAttribute("copia", copia);
		return "copy/update";
	}
	
	@GetMapping("/book/{id}/add_copies")
	public String showNewCopyForm(@PathVariable(value="id") long id, Model model) {
		Libro libro = libroService.getBookById(id);
		model.addAttribute("book", libro);
		return "copy/add";
	}
	
	@PostMapping("/copy/save")
	public String saveCopy(@ModelAttribute("copia") Copia copia, @RequestParam EstadoCopia e, Model model) {
		copia.setEstado(e);
		copiaService.saveCopy(copia);
		long id = copia.getLibro().getId();
		return findPaginated(id, 1, "id", "asc", model);
	}
	
	@PostMapping("/copy/saveMultiple")
	public String saveCopies(@ModelAttribute("book") Libro libro, @RequestParam int numCopias, Model model) {
		for(int i=0;i<numCopias;i++) {
			Copia c = new Copia();
			c.setEstado(EstadoCopia.BIBLIOTECA);
			c.setLibro(libro);
			copiaService.saveCopy(c);
		}
		long id = libro.getId();
		return findPaginated(id, 1, "id", "asc", model);
	}
	
	
}
