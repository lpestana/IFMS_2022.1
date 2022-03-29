package br.edu.ifms.aula01.servico;

import jakarta.jws.WebService;

@WebService(endpointInterface = "br.edu.ifms.aula01.servico.BoaNoite")
public class BoaNoiteImpl implements BoaNoite {

	@Override
	public String digaBoaNoite(String name) {

		return "Boa noite " + name;
	}

}
