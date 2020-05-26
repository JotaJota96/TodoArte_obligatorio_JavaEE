package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
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
import com.TodoArte.JPAControllerClasses.NotificacionArtistaJpaController;

@Entity
@Table(name = "artista")
public class Artista extends Usuario implements Serializable {
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

    public Artista(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, String nombre, String biografia, Sitio sitio) {
		super(nikname, contrasenia, correo, saldo, imagen);
		if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		if(biografia.equals("")){
    		throw new RuntimeException(MensajesExcepciones.biografia);
    	}
		/*
		if(sitio == null){
    		throw new RuntimeException(MensajesExcepciones.sitio);
    	}
		*/
        this.nombre = nombre;
        this.biografia = biografia;
        this.notificacion = new TreeMap<Integer, NotificacionArtista>();
        this.pagos = new TreeMap<Integer, PagoAPlataforma>();
        this.miSitio = sitio;
        this.miSitio.setMiArtista(this);
    }
    //**********************************************************************

    public static String codificar(Artista artista) {
		JsonObject json = Json.createObjectBuilder()
	        .add("nombre", artista.getNombre())
	        .add("biografia", artista.getBiografia())
	        .add("nikname", artista.getNikname())
	        .add("contrasenia", artista.getContrasenia())
	        .add("correo", artista.getCorreo())
	        .add("saldo", artista.getSaldo())
           .build();
		
		StringWriter strWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
		return strWriter.toString();
	}
    
	public static Artista decodificar(String strJson) {
		StringReader reader = new StringReader(strJson);
		
		Artista artista = new Artista();
		
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            artista.setNombre(json.getString("nombre"));
            artista.setBiografia(json.getString("biografia"));
            artista.setNikname(json.getString("nikname"));
            artista.setContrasenia(json.getString("contrasenia"));
            artista.setCorreo(json.getString("correo"));
            artista.setSaldo(json.getInt("saldo"));
          //artista.setImagen(json.getInt("imagen"));
        }catch (Exception e) {
        	return null;
		}
		return artista;
	}

    
    public void agregarNotificacion(NotificacionArtista notificacion) {
    	notificacion.setId(0);
    	new NotificacionArtistaJpaController().create(notificacion);
    	this.notificacion.put(notificacion.getId(), notificacion);
    }
    
    //**********************************************************************
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

	@Override
	public String toString() {
		return "Artista [nombre=" + nombre + ", biografia=" + biografia + ", notificacion=" + notificacion + ", pagos="
				+ pagos + ", miSitio=" + miSitio + ", nikname=" + nikname + ", contrasenia=" + contrasenia + ", correo="
				+ correo + ", saldo=" + saldo + ", imagen=" + Arrays.toString(imagen) + ", bloqueado=" + bloqueado
				+ "]";
	}
    
	
    

}