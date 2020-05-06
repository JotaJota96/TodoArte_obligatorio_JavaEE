
package com.TodoArte.Classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuarios implements Serializable{
	@Id
	@Column(name = "id")
    protected String nikname;
	
	@Column(name = "contrasenia")
    protected String contrasenia;
	
	@Column(name = "correo", unique = true)
    protected String correo;
	
	@Column(name = "saldo")
    protected float saldo;
	
	@Lob
	@Column(name = "imagen")
    protected byte[] imagen;
    
	@Column(name = "bloqueado")
    protected boolean bloqueado;

	
    public Usuarios(){
    }

    public Usuarios(String nikname, String contrasenia, String correo, float saldo, byte[] imagen) {
    	if(nikname.equals("")){
            throw new RuntimeException(MensajesExcepciones.nickname);
		}
    	if(contrasenia.equals("")){
            throw new RuntimeException(MensajesExcepciones.contrasenia);
		}
    	if(correo.equals("")){
            throw new RuntimeException(MensajesExcepciones.correo);
		}
    	if(saldo <= 0){
            throw new RuntimeException(MensajesExcepciones.saldo);
		}
    	this.nikname = nikname;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.saldo = saldo;
        this.imagen = imagen;
    }

    public String getNikname() {
        return this.nikname;
    }

    public void setNikname(String nikname) {
    	if(nikname.equals("")){
            throw new RuntimeException(MensajesExcepciones.nickname);
		}
        this.nikname = nikname;
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

    public float getSaldo() {
        return this.saldo;
    }

    public void setSaldo(float saldo) {
    	if(saldo <= 0){
            throw new RuntimeException(MensajesExcepciones.saldo);
		}
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