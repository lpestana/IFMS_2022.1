package br.edu.ifms.aula04.servico;

import java.util.List;

import br.edu.ifms.aula04.modelo.Noticia;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;


@WebService 
@SOAPBinding (style = Style.DOCUMENT)
public interface SEINoticia {
	@WebMethod
	boolean adicionar(Noticia noticia);
	
	@WebMethod
	public boolean alterar(Noticia notica);
	
	@WebMethod
	public boolean apagar(long id);
	
	@WebMethod
	public List<Noticia> Listar( );
	
	
	
}
