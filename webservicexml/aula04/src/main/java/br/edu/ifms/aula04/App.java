package br.edu.ifms.aula04;

import br.edu.ifms.aula04.servico.SIBNoticia;
import jakarta.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
    	String porta = "8089";
		System.out.println("Publicando o serviço na porta: " + porta);
		Endpoint.publish("http://localhost:"+porta+"/noticias", new SIBNoticia());
		System.out.println("Serviço publicado na porta: " + porta);
    }
}
