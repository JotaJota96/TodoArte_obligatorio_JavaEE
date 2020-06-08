package beans.MiSitio;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import beans.FuncionesComunes;

@Named
@RequestScoped
public class HeaderCompletoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean mostrarAdministrar = false;

	//*********************************************************************************************************
	
	
	
	//*********************************************************************************************************
	public HeaderCompletoBean() {
		mostrarAdministrar = FuncionesComunes.rolActual("artista");
	}
	public boolean isMostrarAdministrar() {
		return mostrarAdministrar;
	}
	public void setMostrarAdministrar(boolean mostrarAdministrar) {
		this.mostrarAdministrar = mostrarAdministrar;
	}
}
