package com.fourcatsdev.aula13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.aula13.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);
}
