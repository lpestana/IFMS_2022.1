package br.edu.ifms.springproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifms.springproject.orm.Usuario;

@Repository
public interface CrudUsuarioRepository extends CrudRepository<Usuario, Long>{
	List<Usuario> findByNome(String nome);
	
}
