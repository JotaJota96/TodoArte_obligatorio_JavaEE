package beans.MiSitio;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

@Named
@RequestScoped
public class SitioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	FrontOfficeInterface fo = new FrontOfficeController();
	
	private String idArtista;
	private Artista artista;
	private Sitio sitio;
	
	private boolean mostrarIconoFacebook = false;
	private boolean mostrarIconoTwitter = false;
	private boolean mostrarIconoInstagram= false;
	private boolean mostrarIconoYoutube= false;
	
	//****************************************************************************

	//****************************************************************************
	public SitioBean() {
		idArtista = FuncionesComunes.getParametro("id");
		artista = (Artista) fo.obtenerDatosUsuario(idArtista);
		sitio = artista.getMiSitio();

		mostrarIconoFacebook = sitio.getRrssFacebook() != null && !sitio.getRrssFacebook().equals("");
		mostrarIconoTwitter = sitio.getRrssTwitter() != null && !sitio.getRrssTwitter().equals("");
		mostrarIconoInstagram = sitio.getRrssInstagram() != null && !sitio.getRrssInstagram().equals("");
		setMostrarIconoYoutube(sitio.getRrssYouTube() != null && !sitio.getRrssYouTube().equals(""));
	}
	
	public String getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Sitio getSitio() {
		return sitio;
	}
	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}
	public boolean isMostrarIconoFacebook() {
		return mostrarIconoFacebook;
	}
	public void setMostrarIconoFacebook(boolean mostrarIconoFacebook) {
		this.mostrarIconoFacebook = mostrarIconoFacebook;
	}
	public boolean isMostrarIconoTwitter() {
		return mostrarIconoTwitter;
	}
	public void setMostrarIconoTwitter(boolean mostrarIconoTwitter) {
		this.mostrarIconoTwitter = mostrarIconoTwitter;
	}
	public boolean isMostrarIconoInstagram() {
		return mostrarIconoInstagram;
	}
	public void setMostrarIconoInstagram(boolean mostrarIconoInstagram) {
		this.mostrarIconoInstagram = mostrarIconoInstagram;
	}
	public boolean isMostrarIconoYoutube() {
		return mostrarIconoYoutube;
	}
	public void setMostrarIconoYoutube(boolean mostrarIconoYoutube) {
		this.mostrarIconoYoutube = mostrarIconoYoutube;
	}
	
}
