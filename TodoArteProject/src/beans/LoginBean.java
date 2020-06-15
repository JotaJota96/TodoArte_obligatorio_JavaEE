package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	
	private String nickname;
	private String contrasenia;
	
	
	public String iniciarSesion() {
		Usuario u = fo.iniciarSesion(nickname, contrasenia);

		if (u == null) {
			return Redirector.redirect("login.jsf");
		}else if (u instanceof Fan) {
			return Redirector.redirect("home.jsf");
		}else if (u instanceof Artista) {
			return Redirector.redirect("sitio.jsf", "id=" + u.getNikname());
		}else {
			return Redirector.redirect("login.jsf");
		}
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
