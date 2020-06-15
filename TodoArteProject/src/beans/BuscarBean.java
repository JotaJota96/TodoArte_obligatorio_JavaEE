package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class BuscarBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private List<Artista> lstArtistas = new ArrayList<Artista>();
	
	private String busqueda;

	//-- Funciones --------------------------------------------------------------------------------------
	public void buscarArtista(AjaxBehaviorEvent event) {
		List<Artista> resultado = new ArrayList<Artista>();
		lstArtistas = null;
		
		for (Artista a : fo.listarArtistas()) {

			if(a.getNikname().contains(busqueda) || a.getNombre().contains(busqueda)) {
				resultado.add(a);
			}
		}
		lstArtistas = resultado;
		busqueda = "";
	}
	
	//-- Constructor, getters y setters -----------------------------------------------------------------
	
	@PostConstruct
	public void init() {	
	}
	
	public BuscarBean() {
	}
	
	public List<Artista> getLstArtistas() {
		return lstArtistas;
	}

	public void setLstArtistas(List<Artista> lstArtistas) {
		this.lstArtistas = lstArtistas;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	
}
