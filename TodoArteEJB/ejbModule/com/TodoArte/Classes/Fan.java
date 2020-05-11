package com.TodoArte.Classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Sexo;

@Entity
@Table(name = "fan")
public class Fan extends Usuario implements Serializable {
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "apellido")
    private String apellido;
	
	@Basic
	@Column(name = "fecha_nacimiento")
    private Date fechaNac;
	
	@Column(name = "ubicacion")
    private String ubicacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
    private Sexo sexo;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "id_fan")
	@MapKey(name = "id")
    private Map<Integer, NotificacionFan> notificaciones;

	@OneToMany(mappedBy = "miFan")
	private Map<Integer, FanSigueSitio> misSitiosSeguidos;

    public Fan() {
        super();
        this.misSitiosSeguidos = new TreeMap<Integer, FanSigueSitio>();
		this.notificaciones = new TreeMap<Integer, NotificacionFan>();
    }

	public Fan(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, String nombre, String apellido, Date fechaNac, String ubicacion, Sexo sexo) {
		super(nikname, contrasenia, correo, saldo, imagen);
		if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		if(apellido.equals("")){
    		throw new RuntimeException(MensajesExcepciones.apellido);
    	}
		if(fechaNac.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		if(ubicacion.equals("")){
    		throw new RuntimeException(MensajesExcepciones.ubicacion);
    	}
		if(sexo.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.sexo);
    	}
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.ubicacion = ubicacion;
		this.sexo = sexo;
        this.misSitiosSeguidos = new TreeMap<Integer, FanSigueSitio>();
		this.notificaciones = new TreeMap<Integer, NotificacionFan>();
	}

	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
    	if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
    	if(apellido.equals("")){
    		throw new RuntimeException(MensajesExcepciones.apellido);
    	}
		
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return this.fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
    	if(fechaNac.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
        this.fechaNac = fechaNac;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
    	if(ubicacion.equals("")){
    		throw new RuntimeException(MensajesExcepciones.ubicacion);
    	}
		
        this.ubicacion = ubicacion;
    }

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		if(sexo.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.sexo);
    	}
		
		this.sexo = sexo;
	}

	public Map<Integer, NotificacionFan> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Map<Integer, NotificacionFan> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Map<Integer, FanSigueSitio> getMisSitiosSeguidos() {
		return misSitiosSeguidos;
	}

	public void setMisSitiosSeguidos(Map<Integer, FanSigueSitio> misSitiosSeguidos) {
		this.misSitiosSeguidos = misSitiosSeguidos;
	}
}