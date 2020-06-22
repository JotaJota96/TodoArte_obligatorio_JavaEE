package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

@Named
@RequestScoped
public class NotificacionesArtistaBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();

	private ArrayList<NotificacionArtista> listaNotificaciones = new ArrayList<NotificacionArtista>();
	
	//***************************************************************************************************
	
	public NotificacionesArtistaBean() {
		listaNotificaciones = fo.listarNotificacionesArtista(FuncionesComunes.usuarioActual());
		//Collections.reverse(listaNotificaciones); para invertir la lista
	}

	public ArrayList<NotificacionArtista> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(ArrayList<NotificacionArtista> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}
	
	//http://localhost:8080/TodoArteProject/notificaciones-fan.jsf
}
