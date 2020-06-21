package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class MisFans implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	//private BackOfficeInterface bo = new BackOfficeController();
	
	
	private ArrayList<Fan> listaFan = new ArrayList<Fan>();

	private String idArtista;
	
	//-----------funciones-------------------------------

	public void bloquearDesbloquearFan(String idFan) {
		fo.bloquearDesbloquearUsuarioDeSitio(idArtista, idFan);
	}
	
	public String gustosDelFan(Fan miFan) {
		
		ArrayList<String> listaCat = new ArrayList<String>();
		
		Map<Integer, FanSigueSitio> sitios = miFan.getMisSitiosSeguidos();

		for (Map.Entry<Integer, FanSigueSitio> entry : sitios.entrySet()) {
			String nicknameArtista = entry.getValue().getNickArtista();
			Artista art =  (Artista)fo.obtenerDatosUsuario(nicknameArtista);
			
			String cat = art.getMiSitio().getMiCategoria().getNombreCat();
			
			if(!listaCat.contains(cat)) {
				listaCat.add(cat);
			}
		}
		
		if(listaCat.isEmpty()) {
			return "sin gustos.";
		}
		
		String ret="";
		for(String s:listaCat) {
			ret+=s;
			ret+=", ";
		}
		return ret.substring(0, ret.length()-2);
	}
	
	public boolean isFanBloqueado(String nikname) {
		//nikname del fan del cual quiero saber si yo (artista) lo tengo bloquado.
		Artista art = (Artista)fo.obtenerDatosUsuario(idArtista);
		
		for (Map.Entry<Integer, FanSigueSitio> entry : art.getMiSitio().getMisFans().entrySet()) {
			if(entry.getValue().getMiFan().getNikname().equals(nikname)) {
				return entry.getValue().getBloqueado();
			}
		}
		return false;
	}
	
	
	public String estaBloqueadoValue(String nikname) {
		//nikname del fan que artista quiere bloquear
		if(isFanBloqueado(nikname)) {
			return "Desbloquear";
		}
		else {
			return "Bloquear";
		}
	}
	
	public String estaBloqueadoClass(String nikname) {
		//nikname del fan que artista quiere bloquear 
		if(isFanBloqueado(nikname)) {
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

	public String getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	
	public MisFans() {
		
		// IMPORTANTE ---- LOGEATE CON ergo 1234 para probar http://localhost:8080/TodoArteProject/sitio-administrar.jsf
		
		
		//fo.suscribirseFanArtista("alfajor", "ergo"); //esto altero la base de datos
		idArtista = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickname");
		listaFan = fo.obtenerFansDeSitio(idArtista);
	}
	
	
}
 
