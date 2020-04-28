package com.TodoArte.Classes;

public class Administrador{
    public String nickname;
    public String contrasenia;
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