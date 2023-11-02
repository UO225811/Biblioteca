package com.capgemini.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Autor;
import com.capgemini.rest.service.AutorRestService;

@RestController
@RequestMapping("/rest/author")
public class AutorRestController {

	@Autowired
	private AutorRestService servicio;

	@GetMapping("/list")
	public List<Autor> showList(Model model) {
		return servicio.getAllAutores();
	}

	@PostMapping("/add")
	public Autor agregar(@RequestBody Autor user) {
		return servicio.saveAutor(user);
	}
	
//	@PutMapping(path = "/{id1}")
//	public Autor editarUsuario(@PathVariable("id1") long id, @RequestBody Autor u) {
//		u.setId(id);
//		return servicio.modificar(u);
//	}
	
	@DeleteMapping("/{id1}")
	public void deleteUser(@PathVariable("id1") long id) {
		servicio.deleteAutorById(id);
	}

}
