package br.edu.ifms.inpe.servico;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.edu.ifms.inpe.modelo.Cidade;

public class InpeServiceImpl implements InpeService {

	@Override
	public Cidade previsaoCidade(String codigoCidade) {
		Cidade cidade = null;
		try {
			URL url = new URL("http://servicos.cptec.inpe.br/XML/cidade/"+codigoCidade+"/previsao.xml");		
			JAXBContext context = JAXBContext.newInstance(Cidade.class);
			Unmarshaller um = context.createUnmarshaller();		
			cidade = (Cidade) um.unmarshal(url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return cidade;
	}

}
