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
@Table(name = "categoriaSitio")
public class CategoriaSitio implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombreCategoria")
    private String nombreCat;

    public CategoriaSitio() {
    }
    
    public CategoriaSitio(int id, String nombreCat) {
    	if(nombreCat.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombreCatSitio);
    	}
		this.id = id;
		this.nombreCat = nombreCat;
	}
    //*************************************************************************
    
    public static String codificar(CategoriaSitio catSitio) {
		JsonObject json = Json.createObjectBuilder()
	        .add("id", catSitio.getId())
	        .add("nombreCat", catSitio.getNombreCat())
           .build();
		
		StringWriter strWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
		return strWriter.toString();
	}
	
	public static CategoriaSitio decodificar(String strJson) {
		StringReader reader = new StringReader(strJson);
		
		CategoriaSitio catSitio = new CategoriaSitio();
		
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            catSitio.setId(json.getInt("id"));
            catSitio.setNombreCat(json.getString("nombreCat"));
        }catch (Exception e) {
        	return null;
		}
		return catSitio;
	}
    
    //*************************************************************************

	public String getNombreCat() {
        return this.nombreCat;
    }

    public void setNombreCat(String nombreCat) {
    	if(nombreCat.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombreCatSitio);
    	}
    	
        this.nombreCat = nombreCat;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}