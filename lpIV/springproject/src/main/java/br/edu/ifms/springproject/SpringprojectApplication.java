package br.edu.ifms.springproject;



import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifms.springproject.orm.Usuario;
import br.edu.ifms.springproject.repository.CrudUsuarioRepository;

@SpringBootApplication
public class SpringprojectApplication implements CommandLineRunner {

	@Autowired
	private CrudUsuarioRepository crudUsuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringprojectApplication.class, args);
	}


	
	@Override
	public void run(String... args) throws Exception {
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario1 = new Usuario("Maria", sdf.parse("21/12/1982"),"maria@gmail.com", 5);
		Usuario usuario2 = new Usuario("Paulo", sdf.parse("30/11/1990"),"paulo@gmail.com", 4.5);
		
		crudUsuarioRepository.save(usuario1);
		crudUsuarioRepository.save(usuario2);
		*/
		
		/*
		System.out.println("\nLista de usuários...");
		Iterable<Usuario> usuarios = crudUsuarioRepository.findAll();
        usuarios.forEach(usuario -> System.out.println(usuario));
		*/
		
		System.out.println("\nBuscando usuário por id...");
		long id = 1L;
        Optional<Usuario> usuarioOpt = crudUsuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            System.out.println(usuarioOpt.get());
        } else {
            System.out.printf("Não existe usuário com o id %d%n", id);
        }
	}

}
