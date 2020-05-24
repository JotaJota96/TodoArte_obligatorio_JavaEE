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
@Table(name = "categoriaContenido")
public class CategoriaContenido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombreCategoria")
	private String NombreCategoria;

	public CategoriaContenido() {
	}

	public CategoriaContenido(int Id, String NombreCategoria) {
		if(NombreCategoria.equals("")){
    		throw new RuntimeException(MensajesExcepciones.NombreCategoriaContenido);
    	}
		this.id = Id;
		this.NombreCategoria = NombreCategoria;
	}
	
	//*************************************************************************************
	public static String codificar(CategoriaContenido catCont) {
		JsonObject json = Json.createObjectBuilder()
	        .add("id", catCont.getId())
	        .add("nombreCategoria", catCont.getNombreCategoria())
           .build();
		
		StringWriter strWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
		return strWriter.toString();
	}
	
	public static CategoriaContenido decodificar(String strJson) {
		StringReader reader = new StringReader(strJson);
		
		CategoriaContenido catCont = new CategoriaContenido();
		
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            catCont.setId(json.getInt("id"));
            catCont.setNombreCategoria(json.getString("nombreCategoria"));
        }catch (Exception e) {
        	return null;
		}
		return catCont;
	}
	//*************************************************************************************

	public int getId() {
		return this.id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public String getNombreCategoria() {
		return this.NombreCategoria;
	}

	public void setNombreCategoria(String NombreCategoria) {
		if(NombreCategoria.equals("")){
    		throw new RuntimeException(MensajesExcepciones.NombreCategoriaContenido);
    	}
		
		this.NombreCategoria = NombreCategoria;
	}
}