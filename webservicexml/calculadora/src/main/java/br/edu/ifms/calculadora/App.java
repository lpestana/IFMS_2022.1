package br.edu.ifms.calculadora;

import br.edu.ifms.calculadora.servico.CalculadoraImpl;
import jakarta.xml.ws.Endpoint;

public class App 
{
    public static void main( String[] args )
    {
    	CalculadoraImpl calculo = new CalculadoraImpl();
    	Endpoint.publish("http://localhost:8085/servico/calculadora", calculo);
        System.out.println( "Serviço Publicado com Sucesso" );
    }
}
