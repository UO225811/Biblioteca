package com.capgemini.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Lector;
import com.capgemini.rest.service.LectorRestService;
import com.capgemini.service.RolesService;

@RestController
public class LectorRestController {

	@Autowired
	private LectorRestService lectorService;

	@Autowired
	private RolesService rolesService;

	@GetMapping("/rest/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/rest/signup")
	public String signup(Model model) {
		model.addAttribute("lector", new Lector());
		return "signup";
	}

	@PostMapping("/rest/signup")
	public String signup(Lector lector) {
		lector.setRole(rolesService.getRoles()[0]);
		lectorService.saveLector(lector);
		return "redirect:login";
	}

}
