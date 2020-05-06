package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "artista")
public class Artista extends Usuarios implements Serializable {
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "biografia")
    private String biografia;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "id_artista")
	@MapKey(name = "id")
    private Map<Integer, NotificacionArtista> notificacion;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "id_artista")
	@MapKey(name = "id")
    private Map<Integer, PagoAPlataforma> pagos;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sitio")
    private Sitio miSitio;

    public Artista() {
        super();
        this.notificacion = new TreeMap<Integer, NotificacionArtista>();
        this.pagos = new TreeMap<Integer, PagoAPlataforma>();
    }

    public Artista(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, boolean bloqueado, String nombre, String biografia, Sitio sitio) {
		super(nikname, contrasenia, correo, saldo, imagen, bloqueado);
		if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		if(biografia.equals("")){
    		throw new RuntimeException(MensajesExcepciones.biografia);
    	}
		if(sitio.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.sitio);
    	}
		
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
		if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		
		this.nombre = nombre;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		if(biografia.equals("")){
    		throw new RuntimeException(MensajesExcepciones.biografia);
    	}
		
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
		if(miSitio.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.sitio);
    	}
		
		this.miSitio = miSitio;
	}
    
    

}