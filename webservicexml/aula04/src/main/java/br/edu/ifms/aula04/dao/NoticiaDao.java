package br.edu.ifms.aula04.dao;

import java.sql.SQLException;

import br.edu.ifms.aula04.modelo.Noticia;

public interface NoticiaDao {
	public boolean adicionar(Noticia noticia) throws SQLException;

	public boolean alterar(Noticia noticia) throws SQLException;
}
