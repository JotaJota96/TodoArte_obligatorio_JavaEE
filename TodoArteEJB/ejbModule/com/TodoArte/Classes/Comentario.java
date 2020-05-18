package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "texto")
	private String texto;
	
	@Basic
	@Column(name = "fechaYHora")
	private Date fechaYHora;

	@ManyToOne()
	@JoinColumn(name = "id_Fan")
	private Fan miFan;

	public Comentario() {
	}
	
	public Comentario(int id, String texto, Date fechaYHora, Fan miFan) {
		if(texto.equals("")){
    		throw new RuntimeException(MensajesExcepciones.texto);
    	}
		if(fechaYHora.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		if(miFan == null){
    		throw new RuntimeException(MensajesExcepciones.miFan);
    	}
		this.id = id;
		this.texto = texto;
		this.fechaYHora = fechaYHora;
		this.miFan = miFan;
	}
	
	//****************************************************************************
	public static String codificar(Comentario comentario) {
			JsonObject json = Json.createObjectBuilder()
		        .add("id", comentario.getId())
		        .add("texto", comentario.getTexto())
		        .add("fechaYHora", comentario.getFechaString())
	           .build();
			
			StringWriter strWriter = new StringWriter();
			try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
			return strWriter.toString();
		}
		
		public static Comentario decodificar(String strJson) {
			StringReader reader = new StringReader(strJson);
			
			Comentario comentario = new Comentario();
			
	        try (JsonReader jsonReader = Json.createReader(reader)) {
	            JsonObject json = jsonReader.readObject();
	            comentario.setId(json.getInt("id"));
	            comentario.setTexto(json.getString("texto"));
	            comentario.setFechaString(json.getString("fechaYHora"));
	        }catch (Exception e) {
	        	return null;
			}
			return comentario;
		}
	//****************************************************************************

	public int getId() {
		return this.id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		if(texto.equals("")){
    		throw new RuntimeException(MensajesExcepciones.texto);
    	}
		
		this.texto = texto;
	}

	public Date getFechaYHora() {
		return this.fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora) {
		if(fechaYHora.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
		this.fechaYHora = fechaYHora;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		if(miFan.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.miFan);
    	}
		this.miFan = miFan;
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