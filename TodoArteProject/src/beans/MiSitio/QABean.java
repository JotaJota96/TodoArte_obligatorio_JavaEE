package beans.MiSitio;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;
import beans.Redirector;
import websocket.WebSocketServerEndpoint;

import java.io.Serializable;

@Named
@RequestScoped
public class QABean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	FrontOfficeInterface fo = new FrontOfficeController();
	
	private String idArtista = "";
	private String nickname = "";
	private int idSala = -1;
	private String WebSocketURL = WebSocketServerEndpoint.getWebSocketURL();

	//****************************************************************************
	
	public String ingresarAChat() {
		if  (idSala == -1) {
			return Redirector.redirect("404.jsf");
		}
		if  (nickname == null) {
			return Redirector.redirect("401.jsf");
		}
		
		// Si la sala no existe, se crea
		if ( ! WebSocketServerEndpoint.existeSala(this.idSala)) {
			WebSocketServerEndpoint.crearSala(this.idSala);
		}
		return Redirector.redirect("qya.jsf", "id="+idArtista);
	}

	//****************************************************************************
	public QABean() {
		try {
			idArtista = FuncionesComunes.getParametro("id");
			idSala = ((Artista) fo.obtenerDatosUsuario(idArtista)).getMiSitio().getId();
			
			if (FuncionesComunes.rolActual("fan") || FuncionesComunes.rolActual("artista")) {
				nickname = FuncionesComunes.usuarioActual();
			}else {
				nickname = null;
			}
			
		} catch (Exception e) {
		}
	}
	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
		public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getWebSocketURL() {
		return WebSocketURL;
	}
	public void setWebSocketURL(String webSocketURL) {
		WebSocketURL = webSocketURL;
	}
}
