package br.edu.ifms.noticiaCliente;

import java.net.URL;

import javax.xml.namespace.QName;

import br.edu.ifms.noticiaCliente.cliente.SEINoticia;
import jakarta.xml.ws.Service;

public class Conexao {

	private static final SEINoticia sei = construirConexao();
	 
    private static SEINoticia construirConexao() {
        try { 
        	URL url = new URL("http://localhost:8089/noticias?wsdl");

    		QName qname = new QName("http://servico.aula04.ifms.edu.br/", "SIBNoticiaService");
               
            Service service = Service.create(url, qname);
             
            return service.getPort(SEINoticia.class);
 
        } catch (Exception ex) {
            System.err.println("Problemas ao conectar no serviço: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
 
    public static SEINoticia getConnection() {
        return sei;
    }
	
}
