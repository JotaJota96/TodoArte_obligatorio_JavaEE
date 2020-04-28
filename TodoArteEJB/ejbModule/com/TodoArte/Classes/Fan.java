package com.TodoArte.Classes;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.TodoArte.Enums.Sexo;

public class Fan extends Usuarios{
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private String ubicacion;
    private Sexo sexo;
    private Map<Integer, NotificacionFan> notificaciones;
    private Map<Integer, FanSigueSitio> misSitiosSeguidos;

    public Fan() {
        super();
        this.misSitiosSeguidos = new TreeMap<Integer, FanSigueSitio>();
		this.notificaciones = new TreeMap<Integer, NotificacionFan>();
    }

	public Fan(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, boolean bloqueado, String nombre, String apellido, Date fechaNac, String ubicacion, Sexo sexo) {
		super(nikname, contrasenia, correo, saldo, imagen, bloqueado);
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



}