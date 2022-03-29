package br.edu.ifms.aula1consumidor;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import br.edu.ifms.servico.BoaNoite;
import jakarta.xml.ws.Service;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
//       System.out.println( "Hello World!" );
    	URL url = new URL("http://localhost:8085/servico/cumprimento?wsdl");
  	  
        QName qname = new QName("http://servico.aula01.ifms.edu.br/", "BoaNoiteImplService");
  
        Service service = Service.create(url, qname);
  
        BoaNoite cumprimento = service.getPort(BoaNoite.class);
        
        System.out.println(cumprimento.digaBoaNoite("Pestana"));
    	
    }
}
