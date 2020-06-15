package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class BloquearArtista  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private ArrayList<Artista> artistas = new ArrayList<Artista>();
	
	public ArrayList<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(ArrayList<Artista> artistas) {
		this.artistas = artistas;
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

    public void bloquear(String nickname) {
    	bo.bloquearDesbloquearArtista(nickname);
    }
	
	public BloquearArtista() {
		artistas = fo.listarArtistas();
	}
	
}
 
