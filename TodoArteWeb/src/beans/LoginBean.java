package beans;

import java.io.Serializable;
import java.sql.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	
	@EJB
	FrontOfficeInterface fo;
	
	private String nickname;
	private String contrasenia;
	
	
	public String iniciarSesion() {
		String outcome = "login";
		Usuario u = fo.iniciarSesion(nickname, contrasenia);

		if (u == null) {
			System.out.println("Login fallido");
		} else if (u instanceof Fan) {
			outcome = "inicioFan";
		}else if (u instanceof Artista) {
			outcome = "inicioArtista";
		}
		return outcome;
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
