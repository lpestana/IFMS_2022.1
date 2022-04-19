package br.edu.ifms.aula04.dao;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifms.aula04.modelo.Noticia;

public interface NoticiaDao {
	public boolean adicionar(Noticia noticia) throws SQLException;

	public boolean alterar(Noticia noticia) throws SQLException;
	
	public boolean apagar(long id) throws SQLException;
	
	public List<Noticia> listar() throws SQLException;
}
