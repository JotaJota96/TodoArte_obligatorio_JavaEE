package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "administrador")
public class Administrador implements Serializable {
	@Id
    public String nickname;

	@Column(name = "contrasenia")
    public String contrasenia;
	
	@Column(name = "correo")
    public String correo;

    public Administrador() {
    }
    
    public Administrador(String nickname, String contrasenia, String correo) {
    	if(nickname.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nickname);
    	}
    	if(contrasenia.equals("")){
    		throw new RuntimeException(MensajesExcepciones.contrasenia);
    	}
    	if(correo.equals("")){
    		throw new RuntimeException(MensajesExcepciones.correo);
    	}
    	
        this.nickname = nickname;
        this.contrasenia = contrasenia;
        this.correo = correo;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
    	if(nickname.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nickname);
    	}
    	
        this.nickname = nickname;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
    	if(contrasenia.equals("")){
    		throw new RuntimeException(MensajesExcepciones.contrasenia);
    	}
    	
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
    	if(correo.equals("")){
    		throw new RuntimeException(MensajesExcepciones.correo);
    	}
    	
        this.correo = correo;
    }
}