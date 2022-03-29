package br.edu.ifms.calculadora.servico;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding (style = Style.RPC)
public interface Calculadora {
	@WebMethod
	public double somar(double x, double y);
	@WebMethod
	public double subtrair(double x, double y);
	@WebMethod
	public double multiplicar(double x, double y);
	@WebMethod
	public double dividir(double x, double y);
}
