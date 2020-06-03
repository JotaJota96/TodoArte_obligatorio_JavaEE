package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class PagosBean implements Serializable {

	@EJB
	FrontOfficeInterface fo;
	@EJB
	BackOfficeInterface bo;
	
	private List<Artista> lstArtistas = new ArrayList<Artista>();
	private List<PagoAPlataforma> lstPagos = new ArrayList<PagoAPlataforma>();
	private String idArtista;
	//-- Funciones --------------------------------------------------------------------------------------
	public void actualizarTabla() {
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
