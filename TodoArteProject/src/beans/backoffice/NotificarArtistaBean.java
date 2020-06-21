package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class NotificarArtistaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private BackOfficeInterface bo = new BackOfficeController();
	private FrontOfficeInterface fo = new FrontOfficeController();
	
	private List<Artista> lstArtistas = new ArrayList<Artista>();
	private String idArtista;
	private NotificacionArtista notificacion = new NotificacionArtista();
	
	//-- Funciones --------------------------------------------------------------------------------------
	public void enviarNotificacion() {
		// creo una nueva notificacion a partir de la que fue rellenada
		NotificacionArtista na = new NotificacionArtista(0, notificacion.getTitulo(), notificacion.getDescripcion(), null);;
		try {
			bo.notificarArtista(idArtista, na);
			this.notificacion = new NotificacionArtista();
		} catch (Exception e) {
		}
	}
	
	//-- Constructor, getters y setters -----------------------------------------------------------------
	public NotificarArtistaBean() {
	}
	
	@PostConstruct
	public void init() {
		this.lstArtistas = fo.listarArtistas();
	}
	
	public List<Artista> getLstArtistas() {
		return lstArtistas;
	}
	public void setLstArtistas(List<Artista> lstArtistas) {
		this.lstArtistas = lstArtistas;
	}
	public NotificacionArtista getNotificacion() {
		return notificacion;
	}
	public void setNotificacion(NotificacionArtista notificacion) {
		this.notificacion = notificacion;
	}

	public String getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}

}