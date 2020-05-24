package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
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
@Table(name = "reporte")
public class Reporte implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "reporte")
	private String reporte;
	
	@ManyToOne()
	@JoinColumn(name = "id_Fan")
	private Fan miFan;

	public Reporte() {
	}

	public Reporte(int id, String reporte, Fan miFan) {
		if(reporte.equals("")){
            throw new RuntimeException(MensajesExcepciones.reporte);
		}
		if(miFan == null){
            throw new RuntimeException(MensajesExcepciones.miFan);
		}
		this.id = id;
		this.reporte = reporte;
		this.miFan = miFan;
	}
	
	//****************************************************************************
	public static String codificar(Reporte reporte) {
			JsonObject json = Json.createObjectBuilder()
		        .add("id", reporte.getId())
		        .add("reporte", reporte.getReporte())
	           .build();
			
			StringWriter strWriter = new StringWriter();
			try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
			return strWriter.toString();
		}
		
		public static Reporte decodificar(String strJson) {
			StringReader reader = new StringReader(strJson);
			
			Reporte reporte = new Reporte();
			
	        try (JsonReader jsonReader = Json.createReader(reader)) {
	            JsonObject json = jsonReader.readObject();
	            reporte.setId(json.getInt("id"));
	            reporte.setReporte(json.getString("reporte"));
	        }catch (Exception e) {
	        	return null;
			}
			return reporte;
		}
	//****************************************************************************

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		if(miFan == null){
            throw new RuntimeException(MensajesExcepciones.miFan);
		}
		this.miFan = miFan;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		if(reporte.equals("")){
            throw new RuntimeException(MensajesExcepciones.reporte);
		}
		this.reporte = reporte;
	}
	
	
}