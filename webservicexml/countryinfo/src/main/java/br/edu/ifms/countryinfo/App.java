package br.edu.ifms.countryinfo;

import java.net.URL;

import javax.xml.namespace.QName;

import br.edu.ifms.countryinfo.servico.*;
import jakarta.xml.ws.Service;

public class App {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL");

		QName qname = new QName("http://www.oorsprong.org/websamples.countryinfo", "CountryInfoService");

		Service service = Service.create(url, qname);

		CountryInfoServiceSoapType country = service.getPort(CountryInfoServiceSoapType.class);

		TCountryInfo tCountryInfo = country.fullCountryInfo("AU");
		String nome = tCountryInfo.getSName();
		String capital = tCountryInfo.getSCapitalCity();
		String codigo = tCountryInfo.getSISOCode();
		String bandeira = tCountryInfo.getSCountryFlag();
		String phoneCode = tCountryInfo.getSPhoneCode();
		tCountryInfo.getLanguages();
		
		System.out.println(" País: " + nome + "\n Capital: " + capital + "\n Sigla: " + codigo
				+ "\n Banderia: " + bandeira + "\n Código Telefone: " + phoneCode + " Continente: " + tCountryInfo.getSContinentCode());

		System.out.println(tCountryInfo.getLanguages());
	}
}
