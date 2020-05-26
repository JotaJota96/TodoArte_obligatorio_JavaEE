package com.TodoArte.Classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
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
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.JPAControllerClasses.NotificacionFanJpaController;

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
		if(fechaNac == null){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
		Date fechaActual = new Date(System.currentTimeMillis());
		
		java.util.Date fechaNacUtil = new java.util.Date(fechaNac.getTime());
		java.util.Date fechaActualUtil = new java.util.Date(fechaActual.getTime());
		
		if(fechaNacUtil.compareTo(fechaActualUtil) > 0 ){
    		throw new RuntimeException(MensajesExcepciones.fechaPosterior);
		}
		
		if(sexo == null){
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
	//***************************************************************************
	public void agregarNotificacion(NotificacionFan notificacion) {
		notificacion.setId(0);
		new NotificacionFanJpaController().create(notificacion);
		this.notificaciones.put(notificacion.getId(), notificacion);
    }
    
	//***************************************************************************
	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return this.fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {	
        this.ubicacion = ubicacion;
    }

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
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

	@Override
	public String toString() {
		return "Fan [nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", ubicacion="
				+ ubicacion + ", sexo=" + sexo + ", notificaciones=" + notificaciones + ", misSitiosSeguidos="
				+ misSitiosSeguidos + ", nikname=" + nikname + ", contrasenia=" + contrasenia + ", correo=" + correo
				+ ", saldo=" + saldo + ", imagen=" + Arrays.toString(imagen) + ", bloqueado=" + bloqueado + "]";
	}
	
}