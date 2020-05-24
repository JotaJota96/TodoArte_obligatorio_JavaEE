package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "notificacionArtista")
public class NotificacionArtista implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic
    @Column(name = "fechaYhora")
    private Date fechaYHora;

    public NotificacionArtista() {
    }

    public NotificacionArtista(int id, String titulo, String descripcion, Date fechaYHora) {
    	if(titulo.equals("")){
            throw new RuntimeException(MensajesExcepciones.titulo);
		}
    	if(descripcion.equals("")){
            throw new RuntimeException(MensajesExcepciones.descripcion);
		}
    	if(fechaYHora == null){
            throw new RuntimeException(MensajesExcepciones.fechaYHora);
		}
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaYHora = fechaYHora;
    }

  //****************************************************************************
    public static String codificar(NotificacionArtista notiArtista) {
    		JsonObject json = Json.createObjectBuilder()
    	        .add("id", notiArtista.getId())
    	        .add("titulo", notiArtista.getTitulo())
    	        .add("descripcion", notiArtista.getDescripcion())
    	        .add("fechaYHora", notiArtista.getFechaString())
               .build();
    		
    		StringWriter strWriter = new StringWriter();
    		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
    		return strWriter.toString();
    	}
    	
    	public static NotificacionArtista decodificar(String strJson) {
    		StringReader reader = new StringReader(strJson);
    		
    		NotificacionArtista notiArtista = new NotificacionArtista();
    		
            try (JsonReader jsonReader = Json.createReader(reader)) {
                JsonObject json = jsonReader.readObject();
                notiArtista.setId(json.getInt("id"));
                notiArtista.setTitulo(json.getString("titulo"));
                notiArtista.setDescripcion(json.getString("descripcion"));
                notiArtista.setFechaString(json.getString("fechaYHora"));
            }catch (Exception e) {
            	return null;
    		}
    		return notiArtista;
    	}
    //****************************************************************************
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
    	if(titulo.equals("")){
            throw new RuntimeException(MensajesExcepciones.titulo);
		}
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
    	if(descripcion.equals("")){
            throw new RuntimeException(MensajesExcepciones.descripcion);
		}
        this.descripcion = descripcion;
    }

    public Date getFechaYHora() {
        return this.fechaYHora;
    }

    public void setFechaYHora(Date fechaYHora) {
    	if(fechaYHora == null){
            throw new RuntimeException(MensajesExcepciones.fechaYHora);
		}
        this.fechaYHora = fechaYHora;
    }
    
	public String getFechaString() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String fechaString = df.format(getFechaYHora());
		return fechaString;
	}
	
	public void setFechaString(String fecha) {
		java.sql.Date fecFormatoDate = null;
		try {
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecha).getTime());
		      setFechaYHora(fecFormatoDate);
		} catch (Exception ex) {
		}
	}
}