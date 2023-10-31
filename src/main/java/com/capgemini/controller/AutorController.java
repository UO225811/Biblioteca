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

import com.capgemini.model.Autor;
import com.capgemini.service.AutorService;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;

	public String findPaginated(
			@PathVariable(value = "pageNumber") int pageNum, @RequestParam("sortField") String sortField,
			@RequestParam("sortOrder") String sortOrder, Model model) {
		int pageSize = 4;
		Page<Autor> page = autorService.findPaginated(pageNum, pageSize, sortField, sortOrder);
		List<Autor> autores = page.getContent();

		model.addAttribute("sortOrder", sortOrder);
		model.addAttribute("sortField", sortField);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("autores", autores);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("reverseSortDir", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");

		return "/author/list";
	}

	@GetMapping("/author/list")
	public String showList(Model model) {
		return findPaginated(1, "id", "asc", model);
	}

	@GetMapping("/author/{id}/update")
	public String authorUpdatingForm(@PathVariable(value = "id") long id, Model model) {
		Autor autor = this.autorService.getAuthorById(id);
		model.addAttribute("autor", autor);

		return "/author/update";
	}

	@GetMapping("/author/add")
	public String showNewAuthorForm(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
		return "/author/add";
	}

	@GetMapping("/author/{id}/delete")
	public String deleteAuthor(@PathVariable(value = "id") long id) {
		autorService.deleteAutorById(id);
		return "redirect:/author/list";
	}

	@PostMapping("/author/add")
	public String saveAuthor(@ModelAttribute("autor") Autor autor) {
		autorService.saveAutor(autor);
		return "redirect:/author/list";
	}

}
