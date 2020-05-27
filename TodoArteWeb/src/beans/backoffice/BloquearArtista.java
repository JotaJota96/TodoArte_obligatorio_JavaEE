package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.Redirector;

@Named
@SessionScoped
public class BloquearArtista implements Serializable {
	
	@EJB
	BackOfficeInterface bo;
	FrontOfficeInterface fo;
	
	private ArrayList<Artista> artistas = fo.listarArtistas();

	public ArrayList<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(ArrayList<Artista> artistas) {
		this.artistas = artistas;
	}
	
	public BloquearArtista() {
		
	}
	
}
