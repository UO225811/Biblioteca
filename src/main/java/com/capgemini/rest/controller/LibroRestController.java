package com.capgemini.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Libro;
import com.capgemini.rest.service.LibroRestService;

@RestController
@RequestMapping("/rest/book")
public class LibroRestController {
	
	@Autowired
	private LibroRestService libroService;
//	@Autowired
//	private AutorService autorService;
//	@Autowired
//	private CopiaService copiaService;


	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping("/list")		
	public List<Libro> listar()	{
		return libroService.getAllBooks();
	}
	@GetMapping(path= {"/{id}"}) 
	public Libro listarId(@PathVariable("id") long id) {
		return libroService.getBookById(id);
	}
	@PostMapping("/add")
	public Libro agregar(@RequestBody Libro l) {	//damos altas
		return libroService.saveBook(l);
	}
	@PutMapping(path={"/{id}"})
	public Libro editar(@PathVariable("id") long id, @RequestBody Libro l) {	//actualizamos
		l.setId(id);
		return libroService.modificar(l);
	}
	@DeleteMapping(path= {"/{id}"})
	public void delete(@PathVariable("id") long id) {
		libroService.deleteBookById(id);
	}

	
}
