package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.model.Autor;
import com.capgemini.model.Copia;
import com.capgemini.model.Lector;
import com.capgemini.model.Libro;
import com.capgemini.model.Multa;
import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.service.AutorService;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LectorService;
import com.capgemini.service.LibroService;
import com.capgemini.service.PrestamoService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;
	@Autowired
	private CopiaService copiaService;
	@Autowired
	private PrestamoService prestamoService;
	@Autowired
	private LectorService lectorService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "redirect:home";
//		return findPaginated(1, "titulo", "asc", model);
	}
	
	@GetMapping("/home")
	public String goHomePage(Model model) {
		return "home";
	}
	
	@GetMapping("/book/list")
	public String showList(Model model) {
		return findPaginated(1, "titulo", "asc", model);
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("libro") Libro libro, @RequestParam("numCopias") int numCopias) {
		Autor autor = autorService.getAuthorById(libro.getAutor().getId());
		libro.setAutor(autor);		
		
		libroService.saveBook(libro);
		
		
		for (int i=0; i<numCopias; i++) {
			Copia c = new Copia();
			c.setEstado(EstadoCopia.BIBLIOTECA);
			c.setLibro(libro);
			copiaService.saveCopy(c);
		}
		
		return "redirect:/book/list";
	}
	
	@PostMapping("/save_updated")
	public String saveBook(@ModelAttribute("libro") Libro libro) {
		Autor autor = autorService.getAuthorById(libro.getAutor().getId());
		libro.setAutor(autor);		
		
		libroService.saveBook(libro);
		
		return "redirect:/book/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(value="id") long id) {
		this.libroService.deleteBookById(id);
		return "redirect:/book/list";
	}

	@GetMapping("/book/{id}/update")
	public String showFormForUpdating(@PathVariable(value="id") long id, Model model) {
		Libro libro = this.libroService.getBookById(id);
		List<Autor> autores = autorService.getAllAutores();
		model.addAttribute("libro", libro);
		model.addAttribute("authors", autores);
		return "/book/update";
	}

	@GetMapping("/book/add")
	public String showNewBookForm(Model model) {
		Libro libro = new Libro();
		List<Autor> autores = autorService.getAllAutores();
		model.addAttribute("libro", libro);
		model.addAttribute("authors", autores);
		return "/book/add";
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

		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
				.stream().anyMatch(a -> a.getAuthority().equals("ROLE_LECTOR"))) {
			Lector lector = lectorService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			long lectorId = lector.getnSocio();
			
			boolean isMultado = false;
			Set<Multa> multas = lector.getMultas();
			for (Multa m : multas) {
				if (m.getfFin().isAfter(LocalDate.now())) {
					isMultado = true;
					break;
				}
			}
			lector.setMultado(isMultado);
			
			model.addAttribute("maxPrestamos", prestamoService.getNumPrestamos(lectorId)<3);
			model.addAttribute("isMultado", lector.isMultado());
		}
		
		return "/book/list";
	}
}
