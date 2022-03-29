package br.edu.ifms.calculadora.servico;

import jakarta.jws.WebService;

@WebService(endpointInterface = "br.edu.ifms.calculadora.servico.Calculadora")
public class CalculadoraImpl implements Calculadora {

	@Override
	public double somar(double x, double y) {
		// TODO Auto-generated method stub
		return x+y;
	}

	@Override
	public double subtrair(double x, double y) {
		// TODO Auto-generated method stub
		return x-y;
	}

	@Override
	public double multiplicar(double x, double y) {
		// TODO Auto-generated method stub
		return x*y;
	}

	@Override
	public double dividir(double x, double y) {
		// TODO Auto-generated method stub
		return x/y;
	}

}
