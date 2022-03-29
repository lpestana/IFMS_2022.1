package br.edu.ifms.aula01;

import br.edu.ifms.aula01.servico.BoaNoiteImpl;
import jakarta.xml.ws.Endpoint;
public class App 
{
    public static void main( String[] args )
    {
    	Endpoint.publish("http://localhost:8085/servico/cumprimento", new BoaNoiteImpl());
        System.out.println("Serviço publicado com sucesso");
        
    }
}
