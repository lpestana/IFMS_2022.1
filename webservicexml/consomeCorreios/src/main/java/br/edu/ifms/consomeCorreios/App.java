package br.edu.ifms.consomeCorreios;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import br.edu.ifms.consomeCorreios.servico.AtendeCliente;
import br.edu.ifms.consomeCorreios.servico.EnderecoERP;
import jakarta.xml.ws.Service;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	URL url = new URL("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl");   
    
    	QName qname = new QName("http://cliente.bean.master.sigep.bsb.correios.com.br/", "AtendeClienteService");
    
    	Service service = Service.create(url, qname);
    	
    	AtendeCliente correio = service.getPort(AtendeCliente.class);
    	
    	EnderecoERP enderecoERP =correio.consultaCEP("79073710");
    	String cidade = enderecoERP.getCidade();
    	String bairro = enderecoERP.getBairro();
    	String rua = enderecoERP.getEnd();
    	String uf = enderecoERP.getUf();
    	
    	
    	System.out.println("Cidade "+ cidade);
    	System.out.println("bairro "+ bairro);
    	System.out.println("rua "+ rua);
    	System.out.println("uf "+ uf);
    	
    	
    	
    	
    }
    
    
}
