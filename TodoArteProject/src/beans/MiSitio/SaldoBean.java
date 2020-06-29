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

import beans.FuncionesComunes;
import beans.Redirector;

@Named
@RequestScoped
public class SaldoBean implements Serializable {
	
	//----------atrivutos------------------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	//private BackOfficeInterface bo = new BackOfficeController();

	private String idArtista; // tambien es el id del Fan, pero ya estaba hecho asi...
	private float saldoDelArtista;

	private float monto;
	
	//-----------funciones-------------------------------

	public String recargarSaldo() {
		try {
			fo.recargarSaldo(idArtista, monto);
			
			if (FuncionesComunes.rolActual("fan")) {
				return Redirector.redirect("saldo-fan.jsf");
			}else {
				return Redirector.redirect("sitio-administrar.jsf", "tab=3");
			}
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	
	//----------setters getters constructors---------
	
	public SaldoBean() {
		try {
			idArtista = FuncionesComunes.usuarioActual();
			Usuario art = fo.obtenerDatosUsuario(idArtista);
			saldoDelArtista = art.getSaldo();
		} catch (Exception e) {
		}
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
 
