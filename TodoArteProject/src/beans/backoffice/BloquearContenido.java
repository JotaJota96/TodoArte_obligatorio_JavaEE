package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.Redirector;

@Named
@SessionScoped
public class BloquearContenido  implements Serializable {

	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
	
	public ArrayList<Contenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(ArrayList<Contenido> contenidos) {
		this.contenidos = contenidos;
	}
	
	public String estaBloqueadoValue(int idContenido) {
		try {
			Contenido cont = fo.obtenerUnContenido(idContenido);
			if(cont.getBloqueado()) {
				return "Desbloquear";
			}
			else {
				return "Bloquear";
			}
		} catch (Exception e) {
			System.out.println("error---------------------");
			return "Rebento ";
		}
	}
	
	public String estaBloqueadoClass(int idContenido) {
		try {
			Contenido cont = fo.obtenerUnContenido(idContenido);
			if(cont.getBloqueado()) {
				return "btn  btn-success";
			}
			else {
				return "btn  btn-danger";
			}	
		} catch (Exception e) {
			System.out.println("error---------------------");
			return "btn ";
		}
	}
	 
    public String bloquear(int idContenido) {
    	try {
    		bo.bloquearDesbloquearContenido(idContenido);
			return Redirector.redirect("backoffice.jsf", "tab=2");
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
    }
	
	public BloquearContenido() {
	}
	
	@PostConstruct
	public void init() {
		try {
			contenidos = fo.listarContenido();
		} catch (Exception e) {
			System.out.println("Rebento------------------------------------------------"+e);
		}
	}
}
 
