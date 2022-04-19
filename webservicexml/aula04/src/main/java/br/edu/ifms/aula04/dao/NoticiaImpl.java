package br.edu.ifms.aula04.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.edu.ifms.aula04.modelo.Noticia;

public class NoticiaImpl implements NoticiaDao {
	private Connection connection;

	public NoticiaImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean adicionar(Noticia noticia) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into noticia (titulo, texto, data_publicacao) values (?,?,?)";
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, noticia.getTitulo());
			stmt.setString(2, noticia.getTexto());
			stmt.setDate(3, new Date(noticia.getData().getTime()));
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return false;
	}

	@Override
	public boolean alterar(Noticia noticia) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "update noticia set titulo = ? , texto = ? , data_publicacao = ? " + "where id = ? ;";
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, noticia.getTitulo());
			stmt.setString(2, noticia.getTexto());
			stmt.setDate(3, new Date(noticia.getData().getTime()));
			stmt.setLong(4, noticia.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return false;
	}

	@Override
	public boolean apagar(long id) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "delete from noticia where id = ? ;";
			stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
		return false;
	}

	@Override
	public List<Noticia> listar() throws SQLException {
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		Statement stmt = null;

		try {
			String sql = "select * from noticia";
			stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Noticia not = new Noticia();
				not.setId(rs.getInt("id"));
				not.setTitulo(rs.getString("titulo"));
				not.setTexto(rs.getString("texto"));
				not.setData(new java.util.Date(rs.getDate("data_publicacao").getTime()));
				noticias.add(not);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();			
		}
		return noticias;

	}

}
