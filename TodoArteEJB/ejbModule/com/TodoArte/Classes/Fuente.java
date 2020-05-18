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
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "fuente")
public class Fuente implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
    private String nombre;

    public Fuente() {
    }
    
    public Fuente(int id, String nombre) {
    	if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
    	
		this.id = id;
		this.nombre = nombre;
	}
    
	public String getNombre() {
        return this.nombre;
    }
	
	//****************************************************************************
	public static String codificar(Fuente fuente) {
			JsonObject json = Json.createObjectBuilder()
		        .add("id", fuente.getId())
		        .add("nombre", fuente.getNombre())
	           .build();
			
			StringWriter strWriter = new StringWriter();
			try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
			return strWriter.toString();
		}
		
		public static Fuente decodificar(String strJson) {
			StringReader reader = new StringReader(strJson);
			
			Fuente fuente = new Fuente();
			
	        try (JsonReader jsonReader = Json.createReader(reader)) {
	            JsonObject json = jsonReader.readObject();
	            fuente.setId(json.getInt("id"));
	            fuente.setNombre(json.getString("nombre"));
	        }catch (Exception e) {
	        	return null;
			}
			return fuente;
		}
	//****************************************************************************
		
    public void setNombre(String nombre) {
    	if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
    	
        this.nombre = nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}