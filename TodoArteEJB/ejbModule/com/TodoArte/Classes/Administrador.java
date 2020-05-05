package com.TodoArte.Classes;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public String nickname;

	@Column(name = "contrasenia")
    public String contrasenia;
	
	@Column(name = "correo")
    public String correo;

    public Administrador() {
    }
    
    public Administrador(String nickname, String contrasenia, String correo) {
        this.nickname = nickname;
        this.contrasenia = contrasenia;
        this.correo = correo;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}