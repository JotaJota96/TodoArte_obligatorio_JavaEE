package beans.MiSitio;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class MisFans implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private ArrayList<Fan> listaFan = new ArrayList<Fan>();
	
	
	//-----------funciones-------------------------------
	
	public void bloquearDesbloquearFan(String idFan) {
		String idArtista = "ergo"; //idArtista obtenido por la variable secion
		
		fo.bloquearDesbloquearUsuarioDeSitio(idArtista, idFan);
	}
	
	public String estaBloqueadoValue(String nikname) {
		Usuario user = fo.obtenerDatosUsuario(nikname);
		if(user.getBloqueado()) {
			return "Desbloquear";
		}
		else {
			return "Bloquear";
		}
	}
	
	public String estaBloqueadoClass(String nikname) {
		Usuario user = fo.obtenerDatosUsuario(nikname);
		if(user.getBloqueado()) {
			return "btn  btn-success";
		}
		else {
			return "btn  btn-danger";
		}	
	}
	
	//----------setters getters constructors---------
	public ArrayList<Fan> getListaFan() {
		return listaFan;
	}

	public void setListaFan(ArrayList<Fan> listaFan) {
		this.listaFan = listaFan;
	}

	public MisFans() {
	}
	
	@PostConstruct
	public void init() {
		
		String idArtista = "ergo"; //idArtista obtenido por la variable secion
		
		//fo.suscribirseFanArtista("alfajor", "ergo"); //esto altero la base de datos
				
		listaFan = fo.obtenerFansDeSitio(idArtista);
		
		System.out.println("---------------------listaFan--------------" + listaFan.size());
	}
	
}
 
