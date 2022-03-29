package br.edu.ifms.projetoweb.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifms.projetoweb.orm.Papel;
import br.edu.ifms.projetoweb.orm.Usuario;
import br.edu.ifms.projetoweb.repository.PapelRepository;
import br.edu.ifms.projetoweb.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private boolean temAutorizacao(Usuario usuario, String papel) {
		for (Papel pp : usuario.getPapeis()) {
			if (pp.getPapel().equals(papel)) {
				return true;
			}
	    }
		return false;
	}
	
	
	 
	@RequestMapping("/index")
	public String index(Principal principal, Model model) {
		String login = principal.getName();
		Usuario usuario = usuarioRepository.findByLogin(login);		
		model.addAttribute("usuario", usuario);
		
		String redirectURL = "";
		if (temAutorizacao(usuario, "ADMIN")) {
            redirectURL = "/auth/admin/admin-index";
        } else if (temAutorizacao(usuario, "USER")) {
            redirectURL = "/auth/user/user-index";
        }
		return redirectURL;		
	}

	 
	@GetMapping("/adicionar")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "formularioUsuario";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return "formularioUsuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/";
	}
}
