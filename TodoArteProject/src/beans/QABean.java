package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import websocket.WebSocketServerEndpoint;

import java.io.Serializable;

@Named
@SessionScoped
public class QABean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idSala;
	private String nickname;
	private String WebSocketURL = WebSocketServerEndpoint.getWebSocketURL();
	
	public String ingresarAChat() {
		String outcome = "participarQA";
		
		if (WebSocketServerEndpoint.existeSala(this.idSala)) {
			// aca se puede hacer lguna verificacion de nickname...
		}else {
			WebSocketServerEndpoint.crearSala(this.idSala);
		}
		return outcome;
	}
	
	//-------------------------------------------------------------
	public QABean() {
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
