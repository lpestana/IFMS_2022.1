package br.edu.ifms.aula04.servico;

import br.edu.ifms.aula04.modelo.Noticia;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.edu.ifms.aula04.servico.SEINoticia")
public class SIBNoticia implements SEINoticia {

	@Override
	public boolean adicionar(Noticia Noticia) {
		
		return false;
	}

}
