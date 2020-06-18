package beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HeaderSimpleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<String[]> opcionesIzquierda = new ArrayList<String[]>();
	private ArrayList<String[]> opcionesDerecha= new ArrayList<String[]>();
	private boolean fanLogueado = false;
	private boolean adminLogueado = false;
	private String linkLogo = "";
	private String nickUsuario = null;

	//*********************************************************************************************************
	public void definirOpcionesIzquierda() {
		// si es un visitante o un fan
		if (FuncionesComunes.usuarioActual() == null || FuncionesComunes.rolActual("fan")) {
			opcionesIzquierda.add(new String[]{"Buscar artista", Redirector.redirect("buscar.jsf")});
			linkLogo = Redirector.redirect("home.jsf");
		}
		// si es un fan
		if (FuncionesComunes.rolActual("fan")) {
			opcionesIzquierda.add(new String[]{"Mi saldo", Redirector.redirect("saldo-fan.jsf")});
		}
		// si es un admin
		if (FuncionesComunes.rolActual("admin")) {
			opcionesIzquierda.add(new String[]{"Backoffice", Redirector.redirect("backoffice.jsf")});
			linkLogo = Redirector.redirect("backoffice.jsf");
		}
	}
	
	public void definirOpcionesDerecha() {
		// si es un visitante
		if (FuncionesComunes.usuarioActual() == null ) {
			opcionesDerecha.add(new String[]{"Iniciar sesión", Redirector.redirect("login.jsf")});
			opcionesDerecha.add(new String[]{"Registrarse", Redirector.redirect("registro.jsf")});
		}
		// si es un fan
		if (FuncionesComunes.rolActual("fan")) {
			fanLogueado = true;
		}
		
	}
	
	
	//*********************************************************************************************************
	public HeaderSimpleBean() {
		try {
			// establezco variables
			nickUsuario = FuncionesComunes.usuarioActual();
			if (FuncionesComunes.rolActual("fan"))   fanLogueado = true;
			if (FuncionesComunes.rolActual("admin")) adminLogueado = true;
			
			// Cargo las opciones que deben aparecer en el menu
			definirOpcionesIzquierda();
			definirOpcionesDerecha();
		}catch (Exception e) {
		}
	}

	public ArrayList<String[]> getOpcionesIzquierda() {
		return opcionesIzquierda;
	}
	public void setOpcionesIzquierda(ArrayList<String[]> opcionesIzquierda) {
		this.opcionesIzquierda = opcionesIzquierda;
	}
	public ArrayList<String[]> getOpcionesDerecha() {
		return opcionesDerecha;
	}
	public void setOpcionesDerecha(ArrayList<String[]> opcionesDerecha) {
		this.opcionesDerecha = opcionesDerecha;
	}
	public boolean isFanLogueado() {
		return fanLogueado;
	}
	public void setFanLogueado(boolean fanLogueado) {
		this.fanLogueado = fanLogueado;
	}
	public boolean isAdminLogueado() {
		return adminLogueado;
	}
	public void setAdminLogueado(boolean adminLogueado) {
		this.adminLogueado = adminLogueado;
	}
	public String getLinkLogo() {
		return linkLogo;
	}
	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}
	public String getNickUsuario() {
		return nickUsuario;
	}
	public void setNickUsuario(String nickUsuario) {
		this.nickUsuario = nickUsuario;
	}
	

}
