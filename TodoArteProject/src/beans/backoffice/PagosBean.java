package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class PagosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private List<Artista> lstArtistas = new ArrayList<Artista>();
	private List<PagoAPlataforma> lstPagos = new ArrayList<PagoAPlataforma>();
	private String idArtista;
	//-- Funciones --------------------------------------------------------------------------------------
	public void actualizarTabla(AjaxBehaviorEvent  event) {
		try {
			lstPagos = bo.obtenerPagos(idArtista);
		} catch (Exception e) {
			lstPagos = new ArrayList<PagoAPlataforma>();
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			lstArtistas = fo.listarArtistas();
		} catch (Exception e) {
			lstArtistas = new ArrayList<Artista>();
		}
	}
	
	//-- Constructor, getters y setters -----------------------------------------------------------------
	public PagosBean() {
	}
	public List<Artista> getLstArtistas() {
		return lstArtistas;
	}
	public void setLstArtistas(List<Artista> lstArtistas) {
		this.lstArtistas = lstArtistas;
	}
	public List<PagoAPlataforma> getLstPagos() {
		return lstPagos;
	}
	public void setLstPagos(List<PagoAPlataforma> lstPagos) {
		this.lstPagos = lstPagos;
	}
	public String getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	
	
	
}
