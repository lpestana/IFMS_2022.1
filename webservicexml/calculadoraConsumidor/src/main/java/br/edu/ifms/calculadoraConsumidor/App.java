package br.edu.ifms.calculadoraConsumidor;

import java.net.URL;
import javax.xml.namespace.QName;


import jakarta.xml.ws.Service;

public class App 
{
    public static void main( String[] args ) throws Exception {
    	URL url = new URL("http://localhost:8085/servico/calculadora?wsdl");
    	
    	QName qname = new QName("http://servico.calculadora.ifms.edu.br/", "CalculadoraImplService");
    	
    	Service service = Service.create(url, qname);
    	
    	Calculadora calcular = service.getPort(Calculadora.class);
    	double soma = calcular.somar(10, 5);
    	double subtracao = calcular.subtrair(10, 5);
    	double multiplicacao = calcular.multiplicar(10, 5);
    	double divisao = calcular.dividir(10, 5);
    	System.out.println("Soma = " + soma);
    	System.out.println("Subtração = " + subtracao);
    	System.out.println("Multiplicação  = " + multiplicacao);
    	System.out.println("Divisão = " + divisao);
    	
    	
    }
    
}
