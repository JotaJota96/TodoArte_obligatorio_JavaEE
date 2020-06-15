package websocket;

import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class MensajeChat {

	private int idSala;
	private String nickname;
	private String mensaje;

	//-------------------------------------------------------------------------
	
	public static String codificar(MensajeChat msj) {
		JsonObject json = Json.createObjectBuilder()
	        .add("idSesion", msj.getIdSala())
	        .add("nickname", msj.getNickname())
	        .add("mensaje", msj.getMensaje())
           .build();
		
		StringWriter strWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
		return strWriter.toString();
	}
	
	public static MensajeChat decodificar(String strJson) {
		StringReader reader = new StringReader(strJson);
		
		MensajeChat msj = new MensajeChat();
		
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            msj.setIdSala(json.getInt("idSala"));
            msj.setNickname(json.getString("nickname"));
            msj.setMensaje(json.getString("mensaje"));
        }catch (Exception e) {
        	return null;
		}
		return msj;
	}
	
	
	//-------------------------------------------------------------------------
	public MensajeChat() {
	}
	
	public MensajeChat(int idSala, String nickname, String mensaje) {
		this.idSala = idSala;
		this.nickname = nickname;
		this.mensaje = mensaje;
	}
	
	//-------------------------------------------------------------------------
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
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
