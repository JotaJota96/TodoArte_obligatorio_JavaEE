package beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RegistrarArtistaSitio implements Serializable{
	@EJB
	FrontOfficeInterface fo;
	
	private Artista artista = new Artista();
	private Sitio sitio = new Sitio();
	private String contrasenia2;
	//---------------------------------------------------------------------
	
	public void registrar() {
		System.out.println("------------");
		System.out.println(artista);
		System.out.println(sitio);
		System.out.println("------------");
	}
	
	//---------------------------------------------------------------------
	public RegistrarArtistaSitio() {
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
	public String getContrasenia2() {
		return contrasenia2;
	}
	public void setContrasenia2(String contrasenia2) {
		this.contrasenia2 = contrasenia2;
	}
	
	
}
