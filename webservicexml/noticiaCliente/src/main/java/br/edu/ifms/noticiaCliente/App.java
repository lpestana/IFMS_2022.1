package br.edu.ifms.noticiaCliente;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import br.edu.ifms.noticiaCliente.cliente.Noticia;
import br.edu.ifms.noticiaCliente.cliente.SEINoticia;

import jakarta.xml.ws.Service;

public class App {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		URL url = new URL("http://localhost:8089/noticias?wsdl");

		QName qname = new QName("http://servico.aula04.ifms.edu.br/", "SIBNoticiaService");

		Service service = Service.create(url, qname);

		while (true) {
			System.out.print("|-------------------------------|\n");
			System.out.print("| Opção 1 - Listar Noticias     |\n");
			System.out.print("| Opção 2 - Excluir Noticias	  |\n");
			System.out.print("| Opção 3 - Editar Noticias     |\n");
			System.out.print("| Opção 4 - Adicionar Noticias  |\n");
			System.out.print("| Opção 5 - Sair                |\n");
			System.out.print("|-------------------------------|\n");
			System.out.print("Digite uma opção: ");

			int opcao = scanner.nextInt();

			if (opcao == 5) {
				System.out.print("\nAté logo!");
				scanner.close();
			}

			switch (opcao) {

			case 1:
				SEINoticia servicoNoticia = service.getPort(SEINoticia.class);
				List<Noticia> lista = servicoNoticia.listar();
				for (Noticia not : lista) {

					System.out.println(not.getId());
					System.out.println(not.getTexto());
					System.out.println(not.getTitulo());
					System.out.println(not.getData());
					System.out.println("--------------------------------");
				}

				break;

			case 2:
				System.out.println("Qual noticia deseja apagar \n");
				long valor = scanner.nextInt();
				SEINoticia excluirNoticia = service.getPort(SEINoticia.class);
				boolean exlcurir = excluirNoticia.apagar(valor);
				System.out.println(exlcurir);
				break;
			case 3:
				SEINoticia editarNoticia = service.getPort(SEINoticia.class);
				Noticia noticiaal = new Noticia();
				System.out.println("Qual noticia deseja alterar \n");
				long numeroNoticia = scanner.nextInt();
				noticiaal.setId(numeroNoticia);
				noticiaal.setData(gerarData());
				System.out.println("Novo Titulo da notica: \n");
				String titulo = scanner.nextLine();
				noticiaal.setTitulo(titulo);
				System.out.println("Novo Texto da notica: \n");
				String texto = scanner.nextLine();
				noticiaal.setTexto(texto);
				boolean altera = editarNoticia.alterar(noticiaal);
				System.out.println(altera);
				break;

			case 4:
				SEINoticia adicionarNoticia = service.getPort(SEINoticia.class);
				Noticia noticia = new Noticia();
				noticia.setData(gerarData());
				noticia.setTitulo("eliminatórias da copa do mundo");
				noticia.setTexto("Cristiano Ronaldo celebra vaga de Portugal na Copa...");
				boolean retorno = adicionarNoticia.adicionar(noticia);
				System.out.println(retorno);
				break;

			default:
				System.out.print("\nOpção Inválida!");
				break;

			}

		}

	}

	/*
	 * private static void criar() { SEINoticia adicionarNoticia =
	 * service.getPort(SEINoticia.class); Noticia noticia = new Noticia();
	 * noticia.setData(gerarData());
	 * noticia.setTitulo("eliminatórias da copa do mundo");
	 * noticia.setTexto("Cristiano Ronaldo celebra vaga de Portugal na Copa...");
	 * boolean retorno = adicionarNoticia.adicionar(noticia);
	 * System.out.println(retorno); break; }
	 * 
	 * private static void ler() { SEINoticia servicoNoticia =
	 * service.getPort(SEINoticia.class); List<Noticia> lista =
	 * servicoNoticia.listar(); for (Noticia not : lista) {
	 * 
	 * System.out.println(not.getId()); System.out.println(not.getTexto());
	 * System.out.println(not.getTitulo()); System.out.println(not.getData());
	 * System.out.println("--------------------------------"); } }
	 * 
	 * private static void alterar() { SEINoticia editarNoticia =
	 * service.getPort(SEINoticia.class); Noticia noticiaal = new Noticia();
	 * System.out.println("Qual noticia deseja alterar \n"); long numeroNoticia =
	 * scanner.nextInt(); noticiaal.setId(numeroNoticia);
	 * noticiaal.setData(gerarData());
	 * System.out.println("Novo Titulo da notica: \n"); String titulo =
	 * scanner.nextLine(); noticiaal.setTitulo(titulo);
	 * System.out.println("Novo Texto da notica: \n"); String texto =
	 * scanner.nextLine(); noticiaal.setTexto(texto); boolean altera =
	 * editarNoticia.alterar(noticiaal); System.out.println(altera); }
	 * 
	 * private static void excluir() {
	 * System.out.println("Qual noticia deseja apagar \n"); long valor =
	 * scanner.nextInt(); SEINoticia excluirNoticia =
	 * service.getPort(SEINoticia.class); boolean exlcurir =
	 * excluirNoticia.apagar(valor); System.out.println(exlcurir); }
	 */
	
	private static XMLGregorianCalendar gerarData() {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
