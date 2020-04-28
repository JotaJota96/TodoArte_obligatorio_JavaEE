package com.TodoArte.Classes;

public class Usuarios{
    protected String nikname;
    protected String contrasenia;
    protected String correo;
    protected float saldo;
    protected byte[] imagen;
    protected boolean bloqueado;

    public Usuarios() {
    }

    public Usuarios(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, boolean bloqueado) {
        this.nikname = nikname;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.saldo = saldo;
        this.imagen = imagen;
        this.bloqueado = bloqueado;
    }

    public String getNikname() {
        return this.nikname;
    }

    public void setNikname(String nikname) {
        this.nikname = nikname;
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

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public byte[] getImagen() {
        return this.imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public boolean getBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public String toString() {
        return "{" +
            " nikname='" + getNikname() + "'" +
            ", contrasenia='" + getContrasenia() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", saldo='" + getSaldo() + "'" +
            ", imagen='" + getImagen() + "'" +
            "}";
    }

}