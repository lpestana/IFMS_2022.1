package br.edu.ifms.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.modelo.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Servlet implementation class Album
 */
@WebServlet("/Album")
public class ServicoAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServicoAlbum() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Album> albuns = new ArrayList<Album>();
		albuns = addAlbuns(2); // somente dois albuns ser�o gerados
		
		// cria a lista de albuns
		AlbumList albumList = new AlbumList();
		albumList.setAlbuns(albuns);
		
		JAXBContext context;

		try {
			context = JAXBContext.newInstance(AlbumList.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshaller.marshal(albumList, baos);

			response.setContentType("text/xml;charset=UTF-8");

			try {
				PrintWriter out = response.getWriter();
				out.print(baos);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	
	/* Os m�todos abaixo simulam um banco de dados */
	
	private List<Album> addAlbuns(int quantidade){
		
		Album album = null;
		List<Album> albuns = new ArrayList<Album>();
		List<Musica> musicas = null;
		
		for (int i = 0; i < quantidade; i++) {
			album = new Album();
			album.setId(i+1);
			album.setNome("Album " + (i + 1));
			
			musicas = new ArrayList<Musica>();
			for (int j = 0; j < 4; j++) {
				musicas.add(addMusica()); // tr�s m�sicas por album
			}			
			album.setMusicas(musicas);
			albuns.add(album);
			
		}
		
		return albuns;
	}
	
	private Musica addMusica(){
		
		Musica musica = new Musica();
		int id = 0;
		musica.setId(++id);
		musica.setDuracao(id * 1.5);
		musica.setTitulo("Musica " + id);
		
		List<Compositor> compositores = new ArrayList<Compositor>();
		
		for (int i = 0; i < 2; i++) { // dois compositores por m�sica
			compositores.add(addCompositor(i));
		}
		
		musica.setCompositores(compositores);
		
		return musica;
	}
	
	private Compositor addCompositor(int id){
		
		Compositor compositor = new Compositor();
		compositor.setId(id + 1);
		compositor.setNome("Compositor " + id);
		
		return compositor;
	}


}
