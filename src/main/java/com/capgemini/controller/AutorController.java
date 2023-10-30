package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.model.Autor;
import com.capgemini.service.AutorService;
import com.capgemini.service.CopiaService;
import com.capgemini.service.LibroService;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private LibroService libroService;

	@GetMapping("/author/{id}/update")
	public String authorUpdatingForm(@PathVariable(value="id") long id, Model model) {
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
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(value="id") long id) {
		this.libroService.deleteBookById(id);
		return "redirect:/author/list";
	}
	
	@PostMapping("/save")
	public String saveAuthor(@ModelAttribute("autor") Autor autor) {
		autorService.saveAutor(autor);
		return "redirect:/author/list";
	}

}
