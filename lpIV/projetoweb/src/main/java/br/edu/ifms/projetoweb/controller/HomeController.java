package br.edu.ifms.projetoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class HomeController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("msnBemVindo", "Bem-vindo à biblioteca");
		return "publica-index";
	}
}
