package com.fourcatsdev.aula13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.aula13.modelo.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	Papel findByPapel(String papel);
}
