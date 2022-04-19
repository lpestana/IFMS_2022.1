package br.edu.ifms.projetoweb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "/auth/admin/admin-listar-usuario";
	}

	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		usuarioRepository.delete(usuario);
		return "redirect:/usuario/admin/listar";
	}

	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}

	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		// Busca o papel básico de usuário
		Papel papel = papelRepository.findByPapel("USER");
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(papel);

		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);

		usuario.setPapeis(papeis); // associa o papel de USER ao usuário

		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/usuario/novo";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuario invalido: " + id);
		}
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("usuario", usuario);
		return "auth/user/user-alterar-usuario";
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") long id,
			@Valid Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			usuario.setId(id);
			return "/auth/user/user-alterar-usuario";
		}
		usuarioRepository.save(usuario);
		return "redirect:/usuario/admin/listar";
	}
	

}
