package com.TodoArte.Classes;

import java.util.Map;
import java.util.TreeMap;

public class Artista extends Usuarios {
    private String nombre;
    private String biografia;
    private Map<Integer, NotificacionArtista> notificacion;
    private Map<Integer, PagoAPlataforma> pagos;
    private Sitio miSitio;

    public Artista() {
        super();
        this.notificacion = new TreeMap<Integer, NotificacionArtista>();
        this.pagos = new TreeMap<Integer, PagoAPlataforma>();
    }

    public Artista(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, boolean bloqueado, String nombre, String biografia, Sitio sitio) {
		super(nikname, contrasenia, correo, saldo, imagen, bloqueado);
        this.nombre = nombre;
        this.biografia = biografia;
        this.notificacion = new TreeMap<Integer, NotificacionArtista>();
        this.pagos = new TreeMap<Integer, PagoAPlataforma>();
        this.miSitio = sitio;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Map<Integer, NotificacionArtista> getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Map<Integer, NotificacionArtista> notificacion) {
		this.notificacion = notificacion;
	}

	public Map<Integer, PagoAPlataforma> getPagos() {
		return pagos;
	}

	public void setPagos(Map<Integer, PagoAPlataforma> pagos) {
		this.pagos = pagos;
	}

	public Sitio getMiSitio() {
		return miSitio;
	}

	public void setMiSitio(Sitio miSitio) {
		this.miSitio = miSitio;
	}
    
    

}