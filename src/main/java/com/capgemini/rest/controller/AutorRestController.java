package com.capgemini.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Autor;
import com.capgemini.rest.service.AutorRestService;

@RestController
@RequestMapping("/rest/author")
public class AutorRestController {

	@Autowired
	private AutorRestService autorService;

	@GetMapping("/list")
	public List<Autor> showList(Model model) {
		return autorService.getAllAutores();
	}

	@GetMapping("/add")
	public String showNewAuthorForm(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
		return "/author/add";
	}

	@GetMapping("/{id}/delete")
	public String deleteAuthor(@PathVariable(value = "id") long id) {
		autorService.deleteAutorById(id);
		return "redirect:/author/list";
	}

	@PostMapping("/add")
	public String saveAuthor(@ModelAttribute("autor") Autor autor) {
		autorService.saveAutor(autor);
		return "redirect:/author/list";
	}

}
