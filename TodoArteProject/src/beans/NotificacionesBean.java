package beans;

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
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class NotificacionesBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();

	private ArrayList<NotificacionFan> listaNotificaciones = new ArrayList<NotificacionFan>();
	
	//***************************************************************************************************
	public ArrayList<NotificacionFan> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(ArrayList<NotificacionFan> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}
	
	public NotificacionesBean() {
		listaNotificaciones = fo.listarNotificacionesFan(FuncionesComunes.usuarioActual());
		//Collections.reverse(listaNotificaciones); para invertir la lista
	}
	
	//http://localhost:8080/TodoArteProject/notificaciones-fan.jsf
}
