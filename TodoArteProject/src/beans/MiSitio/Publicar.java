package beans.MiSitio;

import java.io.Serializable;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.TodoArte.Enums.Privacidad;

import com.TodoArte.Enums.TipoContenido;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import beans.FuncionesComunes;
import beans.Redirector;

@Named
@SessionScoped
public class Publicar implements Serializable {

	//---atrivutos--------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	private List<CategoriaContenido> listaDeCategoria = new ArrayList<CategoriaContenido>();
	
	private Contenido cont = new Contenido();
	private Part file;
	private int idCategoria;
	private String idArtista;

	//----funciones-------------------

	public String getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}

	public String publicarContenido(){		
		
		CategoriaContenido catCon = fo.obtenerUnaCategoriasContenido(idCategoria);
		cont.setMiCategoria(catCon);
		
		
		
		cont.setArchivo(FuncionesComunes.partToBytes(file));
		
		String nombreArchivo = Paths.get(file.getSubmittedFileName()).getFileName().toString();
		String extencionArchivo = FuncionesComunes.obtenerExtecion(nombreArchivo);
		
		cont.setTipo(FuncionesComunes.obtenerTipoContenido(extencionArchivo));
		
		Contenido nuevoCont =  copiarContenido(cont);
		
		nuevoCont.setArchivo(FuncionesComunes.partToBytes(file));
		
		try {
			fo.agregarModificarContenido(idArtista, nuevoCont);
			cont = new Contenido();
		} catch (Exception e) {
			System.out.println("Error----"+e);
		}
		
		switch (nuevoCont.getTipo()) {
		
		case Imagen:
			return Redirector.redirect("sitio-imagenes.jsf", "id="+idArtista);

		case Video:
			return Redirector.redirect("sitio-videos.jsf", "id="+idArtista);

		case Audio:
			return Redirector.redirect("sitio-musica.jsf", "id="+idArtista);

		case Otros:
			return Redirector.redirect("sitio.jsf", "id="+idArtista);
			
		case PDF:
			return Redirector.redirect("sitio.jsf", "id="+idArtista);
			
		default:
			return Redirector.redirect("sitio.jsf", "id="+idArtista);
		}
		
	}
	
	private Contenido copiarContenido(Contenido c) {
		Contenido cont = new Contenido(0, c.getTipo(), c.getPrivacidad(), (int)c.getPrecio(), c.getDescripcion(), c.getTitulo(), c.getArchivo(), null, false, false, c.getMiCategoria());
		return cont;
	}

	//----seters getters contructor----
	
	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	public Privacidad[] getPrivacidad() {
		return Privacidad.values();
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public Contenido getCont() {
		return cont;
	}

	public void setCont(Contenido cont) {
		this.cont = cont;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public List<CategoriaContenido> getListaDeCategoria() {
		return listaDeCategoria;
	}

	public void setListaDeCategoria(List<CategoriaContenido> listaDeCategoria) {
		this.listaDeCategoria = listaDeCategoria;
	}
	public Publicar() {
		listaDeCategoria = fo.obtenerCategoriasContenido();
		idArtista = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickname");
	}
}
