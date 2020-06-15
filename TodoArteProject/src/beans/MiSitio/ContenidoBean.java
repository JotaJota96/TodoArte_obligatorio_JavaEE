package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

@Named
@RequestScoped
public class ContenidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	FrontOfficeInterface fo = new FrontOfficeController();
	
	private String idArtista = "";
	private String idFan = "";
	private Artista artista = new Artista();
	private ArrayList<Contenido> contenidosAudio = new ArrayList<Contenido>();
	private ArrayList<Contenido> contenidosImagen = new ArrayList<Contenido>();
	private ArrayList<Contenido> contenidosVideo = new ArrayList<Contenido>();
	private ArrayList<Contenido> contenidosPDF = new ArrayList<Contenido>();
	private ArrayList<Contenido> contenidosOtros = new ArrayList<Contenido>();
	
	//**************************************************************************************************
	private void clasificarContenido() {
		for (Contenido c : fo.obtenerContenido(idArtista, idFan)) {
			switch (c.getTipo()) {
			case Audio:
				contenidosAudio.add(c);
				break;
			case Imagen:
				contenidosImagen.add(c);
				break;
			case Video:
				contenidosVideo.add(c);
				break;
			case PDF:
				contenidosPDF.add(c);
				break;
			case Otros:
				contenidosOtros.add(c);
				break;
			}
		}
	}
	
	/*
	public ArrayList<Comentario> obtenerComentarios(Contenido c){
		ArrayList<Comentario> ret = new ArrayList<Comentario>();
		
		for (Map.Entry<Integer, Comentario> entry : c.getMisComentario().entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}
	*/
	
	//**************************************************************************************************
	public ContenidoBean() {
		idArtista = FuncionesComunes.getParametro("id");
		if (FuncionesComunes.rolActual("artista") || FuncionesComunes.rolActual("artista")) {
			idFan = FuncionesComunes.usuarioActual();
		}
		artista = (Artista) fo.obtenerDatosUsuario(idArtista);
		clasificarContenido();
	}

	public String getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}

	public String getIdFan() {
		return idFan;
	}

	public void setIdFan(String idFan) {
		this.idFan = idFan;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public ArrayList<Contenido> getContenidosAudio() {
		return contenidosAudio;
	}

	public void setContenidosAudio(ArrayList<Contenido> contenidosAudio) {
		this.contenidosAudio = contenidosAudio;
	}

	public ArrayList<Contenido> getContenidosImagen() {
		return contenidosImagen;
	}

	public void setContenidosImagen(ArrayList<Contenido> contenidosImagen) {
		this.contenidosImagen = contenidosImagen;
	}

	public ArrayList<Contenido> getContenidosVideo() {
		return contenidosVideo;
	}

	public void setContenidosVideo(ArrayList<Contenido> contenidosVideo) {
		this.contenidosVideo = contenidosVideo;
	}

	public ArrayList<Contenido> getContenidosPDF() {
		return contenidosPDF;
	}

	public void setContenidosPDF(ArrayList<Contenido> contenidosPDF) {
		this.contenidosPDF = contenidosPDF;
	}

	public ArrayList<Contenido> getContenidosOtros() {
		return contenidosOtros;
	}

	public void setContenidosOtros(ArrayList<Contenido> contenidosOtros) {
		this.contenidosOtros = contenidosOtros;
	}
	
}