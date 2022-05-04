package br.edu.ifms.inpe.servico;

import br.edu.ifms.inpe.modelo.Cidade;

public interface InpeService {
	//public Cidades listarCidades();
	public Cidade previsaoCidade(String codigoCidade);
}
