package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

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
@SessionScoped
public class SaldoBean implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	//private BackOfficeInterface bo = new BackOfficeController();

	private String idArtista;
	private float saldoDelArtista;

	private float monto;
	
	//-----------funciones-------------------------------

	public void recargarSaldo() {
		fo.recargarSaldo(idArtista, monto);
	}
	
	//----------setters getters constructors---------
	
	public SaldoBean() {
		
		// IMPORTANTE ---- LOGEATE CON ergo 1234 para probar http://localhost:8080/TodoArteProject/sitio-administrar.jsf
		
		idArtista = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickname");
		
		Artista art = (Artista)fo.obtenerDatosUsuario(idArtista);
		saldoDelArtista = art.getSaldo();
	}
	
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}

	public float getSaldoDelArtista() {
		return saldoDelArtista;
	}

	public void setSaldoDelArtista(float saldoDelArtista) {
		this.saldoDelArtista = saldoDelArtista;
	}
	
	
}
 
