package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	private BackOfficeInterface bo = new BackOfficeController();
	
	private String nickname;
	private String contrasenia;
	
	
	public String iniciarSesion() {
		Usuario u = fo.iniciarSesion(nickname, contrasenia);

		if (u == null) {
			// puede ser admin...
			Administrador admin = bo.iniciarSesion(nickname, contrasenia);
			if (admin != null) {
				// login admin ok
				guardarDatosEnSesion(admin);
				return Redirector.redirect("backoffice.jsf");
			}
			return Redirector.redirect("login.jsf");
		}else if (u instanceof Fan) {
			guardarDatosEnSesion(u);
			return Redirector.redirect("home.jsf");
		}else if (u instanceof Artista) {
			guardarDatosEnSesion(u);
			return Redirector.redirect("sitio.jsf", "id=" + u.getNikname());
		}else {
			return Redirector.redirect("login.jsf");
		}
	}
	
	private void guardarDatosEnSesion(Object o) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String nick = null;
		String rol = null;
		
		if (o instanceof Administrador) {
			nick = ((Administrador) o).getNickname();
			rol = "admin";
		}else if (o instanceof Fan) {
			nick = ((Fan) o).getNikname();
			rol = "fan";
		}else if (o instanceof Artista) {
			nick = ((Artista) o).getNikname();
			rol = "artista";
		}		
		
		context.getExternalContext().getSessionMap().put("nickname", nick);
		context.getExternalContext().getSessionMap().put("rol", rol);
		
		// para obener los valores en la sesion
		//String nick = (String) context.getExternalContext().getSessionMap().get("user");
	}
	//---------------------------------------------------------
	public LoginBean() {}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
