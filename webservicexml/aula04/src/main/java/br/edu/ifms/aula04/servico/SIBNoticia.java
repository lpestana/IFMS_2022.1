package br.edu.ifms.aula04.servico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.aula04.dao.NoticiaDao;
import br.edu.ifms.aula04.dao.NoticiaImpl;
import br.edu.ifms.aula04.modelo.Noticia;
import br.edu.ifms.aula04.util.Conexao;
import jakarta.jws.WebService;

@WebService(endpointInterface = "br.edu.ifms.aula04.servico.SEINoticia")
public class SIBNoticia implements SEINoticia {

	@Override
	public boolean adicionar(Noticia noticia) {
		Connection con = Conexao.getConnection();
		NoticiaDao dao = new NoticiaImpl(con);
		try {
			return dao.adicionar(noticia);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alterar(Noticia noticia) {
		Connection con = Conexao.getConnection();
		NoticiaDao dao = new NoticiaImpl(con);
		try {
			return dao.alterar(noticia);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean apagar(long id) {
		Connection con = Conexao.getConnection();
		NoticiaDao dao = new NoticiaImpl(con);
		try {
			return dao.apagar(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Noticia> Listar() {
		Connection con = Conexao.getConnection();
		NoticiaDao dao = new NoticiaImpl(con);
		try {
			return dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Noticia>();
		}
	}

}
