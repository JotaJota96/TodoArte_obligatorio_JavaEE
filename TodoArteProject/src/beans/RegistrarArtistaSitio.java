package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RegistrarArtistaSitio implements Serializable{
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	
	private Artista artista = new Artista();
	private Sitio sitio = new Sitio();
	private String contrasenia2 = "";
	private Part fotoPerfil;
	private Part fotoPortada;
	private int idCategoriaSitio;
	private int idFuente;
	
	//---------------------------------------------------------------------
	
	public String registrar() {
		try {
			Artista nuevoArtista = copiarArtista(artista);
			Sitio nuevoSitio = copiarSitio(sitio);
			
			nuevoArtista.setImagen(FuncionesComunes.partToBytes(fotoPerfil));
			nuevoSitio.setImagenPortada(FuncionesComunes.partToBytes(fotoPortada));
			nuevoArtista.setMiSitio(nuevoSitio);
			nuevoSitio.setMiArtista(nuevoArtista);
			
			Artista ret = null;
			
			CategoriaSitio cs = fo.obtenerUnaCategoriasSitios(idCategoriaSitio);
			Fuente f = fo.obtenerUnaFuentes(idFuente);

			nuevoSitio.setMiCategoria(cs);
			nuevoSitio.setMiFuente(f);
			
			ret = fo.registrarUsuarioArtista(nuevoArtista, nuevoSitio);

			if (ret == null) {
				return Redirector.redirect("500.jsf");
			}else {
				return Redirector.redirect("login.jsf");
			}
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	
	private Artista copiarArtista(Artista a) {
		Artista copia = new Artista();
		copia.setNikname(a.getNikname());
		copia.setContrasenia(a.getContrasenia());
		copia.setCorreo(a.getCorreo());
		copia.setImagen(a.getImagen());
		copia.setNombre(a.getNombre());
		copia.setBiografia(a.getBiografia());
		return copia;
	}
	
	private Sitio copiarSitio(Sitio s) {
		Sitio copia = new Sitio();
		copia.setPrecioPremium(s.getPrecioPremium());
		copia.setColorDeFondo(s.getColorDeFondo());
		copia.setColorDeMenu(s.getColorDeMenu());
		copia.setColorDeTexto(s.getColorDeTexto());
		copia.setImagenPortada(s.getImagenPortada());
		copia.setRrssTwitter(s.getRrssTwitter());
		copia.setRrssFacebook(s.getRrssFacebook());
		copia.setRrssInstagram(s.getRrssInstagram());
		copia.setRrssYouTube(s.getRrssYouTube());
		return copia;
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
	public Part getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(Part fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public Part getFotoPortada() {
		return fotoPortada;
	}
	public void setFotoPortada(Part fotoPortada) {
		this.fotoPortada = fotoPortada;
	}
	public int getIdCategoriaSitio() {
		return idCategoriaSitio;
	}
	public void setIdCategoriaSitio(int idCategoriaSitio) {
		this.idCategoriaSitio = idCategoriaSitio;
	}
	public int getIdFuente() {
		return idFuente;
	}
	public void setIdFuente(int idFuente) {
		this.idFuente = idFuente;
	}
	//-----------------------------------------------------------------------
	
	public ArrayList<CategoriaSitio> categorias() {
		ArrayList<CategoriaSitio> ret = new ArrayList<CategoriaSitio>();
		try {
			ret = fo.obtenerCategoriasSitios();
		} catch (Exception e) {
		}
		return ret;
	}

	public ArrayList<Fuente> fuentes() {
		ArrayList<Fuente> ret = new ArrayList<Fuente>();
		try {
			ret = fo.obtenerFuentes();
		} catch (Exception e) {
		}
		return ret;
	}
}
