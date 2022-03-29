package br.edu.ifms.exercicio;

import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifms.exercicio.orm.Endereco;
import br.edu.ifms.exercicio.repository.JpaEnderecoRepository;

@SpringBootApplication
public class ExercicioApplication implements  CommandLineRunner{

	@Autowired
	private JpaEnderecoRepository jpaEnderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ExercicioApplication.class, args);
	}

	
	public void run(String... args) throws Exception{
		//inserir o endereco
		
//		Endereco endereco1 = new Endereco("Ayde roque","347","Campo Grande","jd Balsamo","79073710");
//		Endereco endereco2 = new Endereco("Afonsso pena","300","Campo Grande","Centro","79100000");
//		
//		jpaEnderecoRepository.save(endereco1);
//		jpaEnderecoRepository.save(endereco2);
		
		//Buscar lista de endereços
//		System.out.println("\nLista de Endereços");
//		Iterable<Endereco> enderecos = jpaEnderecoRepository.findAll();
//		enderecos.forEach(endereco -> System.out.println(endereco));
		
		//buscar endereço por id
		System.out.println("\nBuscando endereço por ID");
		long id = 1L;
		Optional<Endereco> enderecoOpt = jpaEnderecoRepository.findById(id);
		
		if(enderecoOpt.isPresent()) {
			System.out.println(enderecoOpt.get());
		}else {
			System.out.printf("Não existe usuário com o id %d%n", id);
		}
		
		
	
	}
	
	
}
