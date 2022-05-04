package br.edu.ifms.inpe;

import br.edu.ifms.inpe.modelo.Cidade;
import br.edu.ifms.inpe.modelo.Previsao;
import br.edu.ifms.inpe.servico.InpeService;
import br.edu.ifms.inpe.servico.InpeServiceImpl;

public class App {
	public static void main(String[] args) {
		InpeService inpe = new InpeServiceImpl();
		Cidade cidade = inpe.previsaoCidade("224");
        System.out.println("**Cidade***");
        System.out.println(cidade.getId());
    	System.out.println(cidade.getNome());
    	System.out.println(cidade.getUf());
    	
    	System.out.println("---------------------");
    	for (Previsao p : cidade.getPrevisao()) {
    		System.out.println(p);
    		System.out.println("---------------------");
    	}
	}
}
