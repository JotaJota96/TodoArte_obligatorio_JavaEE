package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RevisarContenido  implements Serializable {

	@EJB
	FrontOfficeInterface fo;
	@EJB
	BackOfficeInterface bo;
	
	private ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
	
	public ArrayList<Contenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(ArrayList<Contenido> contenidos) {
		this.contenidos = contenidos;
	}
	
	public void redirigir(int id) {
		System.out.println("te redirigio al contenido con id " + id);
	}
	
	
	public RevisarContenido() {
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
 
