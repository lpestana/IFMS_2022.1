package br.edu.ifms.noticiaCliente;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import br.edu.ifms.noticiaCliente.cliente.Noticia;
import br.edu.ifms.noticiaCliente.cliente.SEINoticia;

import jakarta.xml.ws.Service;

public class App 
{
    public static void main( String[] args  )throws Exception
    {
    	URL url = new URL("http://localhost:8089/noticias?wsdl");
    	
    	QName qname = new QName("http://servico.aula04.ifms.edu.br/", 
        		"SIBNoticiaService");
  
        Service service = Service.create(url, qname);
    	
        SEINoticia servicoNoticia = service.getPort(SEINoticia.class);
        Noticia noticia = new Noticia();
        //noticia.setId(6l);
        noticia.setData(gerarData());
        noticia.setTitulo("eliminatórias da copa do mundo");
        noticia.setTexto("Cristiano Ronaldo celebra vaga de Portugal na Copa...");
        
        boolean retorno = servicoNoticia.adicionar(noticia);  
        System.out.println(retorno);
    }
    
    private static XMLGregorianCalendar gerarData(){
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
         
        try {
            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }        
        return null;
    }
}
