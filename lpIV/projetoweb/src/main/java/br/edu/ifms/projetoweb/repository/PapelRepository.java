package br.edu.ifms.projetoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.projetoweb.orm.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	Papel findByPapel(String papel);
}
