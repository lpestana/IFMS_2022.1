package br.edu.ifms.exercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.exercicio.orm.Endereco;

@Repository
public interface JpaEnderecoRepository extends JpaRepository<Endereco, Long> {

	
	
}
