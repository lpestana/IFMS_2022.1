package com.fourcatsdev.aula13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourcatsdev.aula13.repository.UsuarioRepository;
import com.fourcatsdev.aula13.modelo.Usuario;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping("/") 
	public String index(@CurrentSecurityContext(expression="authentication.name")
	String login,Model model) {
		
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);	
		model.addAttribute("usuarioLogado", usuarioLogado);
		
		model.addAttribute("msnBemVindo", "Bem-vindo à biblioteca");
		return "publica-index";
	}
	
	
		
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
