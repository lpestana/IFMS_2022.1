package com.fourcatsdev.aula13.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourcatsdev.aula13.repository.UsuarioRepository;
import com.fourcatsdev.aula13.modelo.Endereco;
import com.fourcatsdev.aula13.modelo.Usuario;
import com.fourcatsdev.aula13.repository.EnderecoRepository;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@PostMapping("/salvar/{id}")
	public String salvarEndereco(@CurrentSecurityContext(expression="authentication.name")
				String login,
				@Valid Endereco endereco, 				
				BindingResult result, 
				Model model,
				@PathVariable("id") long idUsuario) {
		Optional<Usuario> usuarioBanco = usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioBanco.get();
		if (result.hasErrors()) {
			endereco.setUsuario(usuario);
			Usuario usuarioLogado = usuarioRepository.findByLogin(login);
			model.addAttribute("usuarioLogado", usuarioLogado);
			return "/auth/user/user-criar-endereco";
		}
		
		endereco.setUsuario(usuario);
		enderecoRepository.save(endereco);
		return "redirect:/usuario/editar/"+endereco.getUsuario().getId();
	}
	

	@GetMapping("/editarEndereco/{id}")
	public String editarEndereco(@CurrentSecurityContext(expression="authentication.name")
								String login,
								@PathVariable("id") long id, Model model) {
		
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
            throw new IllegalArgumentException("Usuário inválido:" + id);
        } 
		Usuario usuario = usuarioVelho.get();
		Endereco endereco = null;
		String url = "";
		if (usuario.getEndereco() == null) {
			endereco = new Endereco();
			endereco.setUsuario(usuario);
			url = "/auth/user/user-criar-endereco";
		} else {
			endereco = usuario.getEndereco();
			url = "/auth/user/user-alterar-endereco";
		}
		
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		model.addAttribute("usuarioLogado", usuarioLogado);
		
		model.addAttribute("endereco", endereco);
	    return url;
	}
	
	@PostMapping("/editarEndereco/{idUsuario}/{id}")
	public String editarUsuario(@CurrentSecurityContext(expression="authentication.name")
								String login, 
								@PathVariable("idUsuario") long idUsuario,
								@PathVariable("id") long id, 
								@Valid Endereco endereco, 
								BindingResult result,
								Model model) {
		
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		model.addAttribute("usuarioLogado", usuarioLogado);
		
		Optional<Usuario> usuarioBanco = usuarioRepository.findById(idUsuario);
		Usuario usuario = usuarioBanco.get();
		
		if (result.hasErrors()) {
			endereco.setId(id);
			endereco.setUsuario(usuario);
			return "/auth/user/user-alterar-endereco";
		} 
		
		endereco.setUsuario(usuario);		
		enderecoRepository.save(endereco);
		return "redirect:/usuario/editar/"+endereco.getUsuario().getId();
	}
	
}
