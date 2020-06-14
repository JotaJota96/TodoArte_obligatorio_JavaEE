package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class ModificarBean implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	//private BackOfficeInterface bo = new BackOfficeController();
	
	private ArrayList<Contenido> listaContenido = new ArrayList<Contenido>();
	
	private String idArtista;
	
	//-----------funciones-------------------------------

	public void modificarContenido(int id) {
		
		try {
			//fo.agregarModificarContenido(idArtista, contenido);
			listaContenido = fo.obtenerContenido(idArtista, idArtista);
		} catch (Exception e) {
			System.out.println("Error---------"+e);
		}
	}
	
	//----------setters getters constructors---------

	public String getIdArtista() {
		return idArtista;
	}

	public ArrayList<Contenido> getListaContenido() {
		return listaContenido;
	}

	public void setListaContenido(ArrayList<Contenido> listaContenido) {
		this.listaContenido = listaContenido;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	
	public ModificarBean() {	
		// IMPORTANTE ---- LOGEATE CON ergo 1234 para probar http://localhost:8080/TodoArteProject/sitio-administrar.jsf
		idArtista = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickname");
		listaContenido = fo.obtenerContenido(idArtista, idArtista);
	}
	
	
}
 
