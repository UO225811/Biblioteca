package com.capgemini.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.Autor;
import com.capgemini.model.Copia;
import com.capgemini.model.Libro;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.AutorService;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;
	@Autowired
	private CopiaService copiaService;


	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "titulo", "asc", model);
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("libro") Libro libro, @ModelAttribute int numCopias) {
		Autor autor = autorService.getAuthorById(libro.getAutor().getId());
		libro.setAutor(autor);
		
		for (int i=0; i<numCopias; i++) {
			Copia c = new Copia();
			c.setEstado(EstadoCopia.BIBLIOTECA);
			c.setLibro(libro);
			copiaService.saveCopy(c);
		}
		
		List<Copia> copias = copiaService.findCopiasByLibroId(libro.getId());
		//libro.setCopias(copias);
		
		libroService.saveBook(libro);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(value="id") long id) {
		this.libroService.deleteBookById(id);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String showFormForUpdating(@PathVariable(value="id") long id, Model model) {
		Libro libro = this.libroService.getBookById(id);
		model.addAttribute("libro", libro);
		return "actualizar_libro";
	}

	@GetMapping("/add")
	public String showNewBookForm(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		return "nuevo_libro";
	}

	@GetMapping("/page/{pageNumber}")
	public String findPaginated(@PathVariable(value = "pageNumber") int pageNum,
			@RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder, Model model) {
		int pageSize = 4;
		Page<Libro> page = libroService.findPaginated(pageNum, pageSize, sortField, sortOrder);
		List<Libro> books = page.getContent();

		model.addAttribute("sortOrder", sortOrder);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("books", books);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("reverseSortDir", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");

		return "index";
	}
	
}
