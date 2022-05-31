package com.fourcatsdev.aula13.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourcatsdev.aula13.modelo.Endereco;



public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	
}
