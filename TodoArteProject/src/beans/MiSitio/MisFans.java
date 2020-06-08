package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class MisFans implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private ArrayList<Fan> listaFan = new ArrayList<Fan>();
	
	
	//-----------funciones-------------------------------
	
	
	
	
	//----------setters getters constructors---------
	public ArrayList<Fan> getListaFan() {
		return listaFan;
	}

	public void setListaFan(ArrayList<Fan> listaFan) {
		this.listaFan = listaFan;
	}

	public MisFans() {
	}
	
}
 
