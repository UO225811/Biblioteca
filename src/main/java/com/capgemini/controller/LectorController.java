package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.model.Lector;
import com.capgemini.service.LectorService;
import com.capgemini.service.RolesService;

@Controller
public class LectorController {

	@Autowired
	private LectorService lectorService;

	@Autowired
	private RolesService rolesService;

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("lector", new Lector());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(Lector lector) {
		lector.setRole(rolesService.getRoles()[1]);
		lectorService.saveLector(lector);
//        securityService.autoLogin(lector.getEmail(), lector.getPassword());
		return "redirect:login";
	}

}
