package com.fourcatsdev.aula02;

import java.net.URL;
import javax.xml.namespace.QName;
import com.fourcatsdev.aula02.servico.CertidaoNascimento;

import jakarta.xml.ws.Service;
public class App 
{
    public static void main( String[] args ) throws Exception    {
    	URL url = new URL("http://localhost:8085/servico/certidao?wsdl");
    	  
        QName qname = new QName("http://servico.aula01.fourcatsdev.com/", "CertidaoNascimentoImplService");
  
        Service service = Service.create(url, qname);
  
        CertidaoNascimento certidao = service.getPort(CertidaoNascimento.class);
        int idade = certidao.calcularIdade("20/10/1981");
        String diaSemana = certidao.diaSemanaNascimento("20/10/1981");        
        System.out.println("Idade: " + idade);
        System.out.println("Dia da semana: " + diaSemana);
    }
}
