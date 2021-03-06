package com.fourcatsdev.aula13.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fourcatsdev.aula13.modelo.Papel;
import com.fourcatsdev.aula13.modelo.Usuario;
import com.fourcatsdev.aula13.repository.PapelRepository;
import com.fourcatsdev.aula13.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PapelRepository papelRepository;

	// início spring security
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
	public String index(@CurrentSecurityContext(expression = "authentication.name") String login, Model model) {

		Usuario usuarioLogado = usuarioRepository.findByLogin(login);

		String redirectURL = "";
		if (temAutorizacao(usuarioLogado, "ADMIN")) {
			redirectURL = "/auth/admin/admin-index";
		} else if (temAutorizacao(usuarioLogado, "USER")) {
			redirectURL = "/auth/user/user-index";
		} else if (temAutorizacao(usuarioLogado, "BIBLIOTECARIO")) {
			redirectURL = "/auth/biblio/biblio-index";
		}
		model.addAttribute("usuarioLogado", usuarioLogado);
		return redirectURL;
	}
	// fim spring security

	@GetMapping("/novo")
	public String adicionarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
	}

	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}

		Usuario usr = usuarioRepository.findByLogin(usuario.getLogin());
		if (usr != null) {
			model.addAttribute("loginExiste", "Login já existe cadastrado");
			return "/publica-criar-usuario";
		}

		// Busca o papel básico de usuário
		Papel papel = papelRepository.findByPapel("USER");
		List<Papel> papeis = new ArrayList<Papel>();
		papeis.add(papel);
		usuario.setPapeis(papeis); // associa o papel de USER ao usuário

		// início spring security
		String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);
		// fim spring security

		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/usuario/novo";
	}

	@RequestMapping("/admin/listar")
	public String listarUsuario(@CurrentSecurityContext(expression = "authentication.name") String login, Model model) {
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("usuarioLogado", usuarioLogado);
		return "/auth/admin/admin-listar-usuario";
	}

	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		usuarioRepository.delete(usuario);
		return "redirect:/usuario/admin/listar";
	}

	@GetMapping("/editar/{id}")
	public String editarUsuario(@CurrentSecurityContext(expression = "authentication.name") String login,
			@PathVariable("id") long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário inválido:" + id);
		}
		Usuario usuario = usuarioVelho.get();

		/*
		 * if (usuario.getEndereco() == null) { usuario.setEndereco(new Endereco()); }
		 */
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		model.addAttribute("usuarioLogado", usuarioLogado);

		model.addAttribute("usuario", usuario);
		return "/auth/user/user-alterar-usuario";
	}

	@PostMapping("/editar/{id}")
	public String editarUsuario(@CurrentSecurityContext(expression = "authentication.name")
								String login,@PathVariable("id") long id,
								@Valid Usuario usuario, 
								BindingResult result, 
								Model model) {
		
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		model.addAttribute("usuarioLogado", usuarioLogado);
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) { 
			       String fieldErrors = ((FieldError) error).getField();
			       if (!fieldErrors.equals("password") &&
			    	   !fieldErrors.equals("login") ) {
			    	   usuario.setId(id);					   			
				       return "/auth/user/user-alterar-usuario";
			       } 
			}
		}
		
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário inválido:" + id);
	    } else {
	    	//isto é necessário para não alterar o login e senha
	      	Usuario usr = usuarioVelho.get();
	       	usr.setNome(usuario.getNome());
	       	usr.setCpf(usuario.getCpf());
	       	usr.setDataNascimento(usuario.getDataNascimento());
	       	usr.setEmail(usuario.getEmail());	       		
	       	usuarioRepository.save(usr);
		}
		
		
		return "redirect:/usuario/index";
	}

	@GetMapping("/editarPapel/{id}")
	public String selecionarPapel(@CurrentSecurityContext(expression = "authentication.name") String login,
			@PathVariable("id") long id, Model model) {
		Usuario usuarioLogado = usuarioRepository.findByLogin(login);
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id);
		if (!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário inválido:" + id);
		}
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("listaPapeis", papelRepository.findAll());
		model.addAttribute("usuario", usuario);
		model.addAttribute("usuarioLogado", usuarioLogado);
		return "/auth/admin/admin-editar-papel-usuario";
	}

	@PostMapping("/editarPapel/{id}")
	public String atribuirPapel(@PathVariable("id") long idUsuario,
			@RequestParam(value = "pps", required = false) int[] pps, Usuario usuario, RedirectAttributes attributes) {
		if (pps == null) {
			usuario.setId(idUsuario);
			attributes.addFlashAttribute("mensagem", "Pelo menos um papel deve ser informado");
			return "redirect:/usuario/editarPapel/" + idUsuario;
		} else {
			// Obtém a lista de papéis selecionada pelo usuário do banco
			List<Papel> papeis = new ArrayList<Papel>();
			for (int i = 0; i < pps.length; i++) {
				long idPapel = pps[i];
				Optional<Papel> papelOptional = papelRepository.findById(idPapel);
				if (papelOptional.isPresent()) {
					Papel papel = papelOptional.get();
					papeis.add(papel);
				}
			}
			Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
			if (usuarioOptional.isPresent()) {
				Usuario usr = usuarioOptional.get();
				usr.setPapeis(papeis); // relaciona papéis ao usuário

				usr.setAtivo(usuario.isAtivo());// usa o usuário passado como parâmetro para alterar a instância de
												// usuário no banco.

				usuarioRepository.save(usr);
			}
		}
		return "redirect:/usuario/admin/listar";
	}

}
