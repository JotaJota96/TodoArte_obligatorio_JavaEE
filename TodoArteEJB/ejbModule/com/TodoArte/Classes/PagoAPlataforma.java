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
@Table(name = "pagoAPlataforma")
public class PagoAPlataforma implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "moto")
    private float monto;
	
	@Basic
	@Column(name = "fechaYHora")
    private Date fechaYHora;

    public PagoAPlataforma() {
    }

    public PagoAPlataforma(int id, float monto, Date fechaYHora) {
    	if(monto <= 0){
            throw new RuntimeException(MensajesExcepciones.monto);
		}
    	if(fechaYHora == null){
            throw new RuntimeException(MensajesExcepciones.fechaYHora);
		}
        this.id = id;
        this.monto = monto;
        this.fechaYHora = fechaYHora;
    }
    
  //****************************************************************************
    public static String codificar(PagoAPlataforma pap) {
    		JsonObject json = Json.createObjectBuilder()
    	        .add("id", pap.getId())
    	        .add("monto", pap.getMonto())
    	        .add("fechaYHora", pap.getFechaString())
               .build();
    		
    		StringWriter strWriter = new StringWriter();
    		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
    		return strWriter.toString();
    	}
    	
    	public static PagoAPlataforma decodificar(String strJson) {
    		StringReader reader = new StringReader(strJson);
    		
    		PagoAPlataforma pap = new PagoAPlataforma();
    		
            try (JsonReader jsonReader = Json.createReader(reader)) {
                JsonObject json = jsonReader.readObject();
                pap.setId(json.getInt("id"));
                pap.setMonto(json.getInt("monto"));
                pap.setFechaString(json.getString("fechaYHora"));
            }catch (Exception e) {
            	return null;
    		}
    		return pap;
    	}
    //****************************************************************************

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return this.monto;
    }

    public void setMonto(float monto) {
    	if(monto <= 0){
            throw new RuntimeException(MensajesExcepciones.monto);
		}
        this.monto = monto;
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