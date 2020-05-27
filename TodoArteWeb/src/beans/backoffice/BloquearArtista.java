package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.Redirector;

@Named
@SessionScoped
public class BloquearArtista implements Serializable {
	
	@EJB
	BackOfficeInterface bo;
	FrontOfficeInterface fo;
	//private ArrayList<Artista> artistas =  fo.;
	
	
	
}
