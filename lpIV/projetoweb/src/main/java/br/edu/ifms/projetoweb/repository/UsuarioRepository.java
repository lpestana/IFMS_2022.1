package br.edu.ifms.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.projetoweb.orm.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login); 
}
