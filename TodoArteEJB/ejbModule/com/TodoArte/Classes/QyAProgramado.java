package com.TodoArte.Classes;

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

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

@Entity
@Table(name = "qyaProgramado")
public class QyAProgramado implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Basic
	@Column(name = "fechaYHoraInicio")
    private Date fechaYHoraInicio;
    
	@Basic
	@Column(name = "fechaYHoraFin")
    private Date fechaYHoraFin;

    public QyAProgramado() {
    }

    public QyAProgramado(int id, Date fechaYHoraInicio, Date fechaYHoraFin) {
    	if(fechaYHoraInicio == null){
            throw new RuntimeException(MensajesExcepciones.fechaYHora);
		}
        this.id = id;
        this.fechaYHoraInicio = fechaYHoraInicio;
        this.fechaYHoraFin = fechaYHoraFin;
    }
    
  //****************************************************************************
    public static String codificar(QyAProgramado qya) {
    		JsonObject json = Json.createObjectBuilder()
    	        .add("id", qya.getId())
    	        .add("fechaYHoraInicio", qya.getFechaInicioString())
    	        .add("fechaYHoraFin", qya.getFechaFinString())
               .build();
    		
    		StringWriter strWriter = new StringWriter();
    		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
    		return strWriter.toString();
    	}
    	
    	public static QyAProgramado decodificar(String strJson) {
    		StringReader reader = new StringReader(strJson);
    		
    		QyAProgramado qya = new QyAProgramado();
    		
            try (JsonReader jsonReader = Json.createReader(reader)) {
                JsonObject json = jsonReader.readObject();
                qya.setId(json.getInt("id"));
                qya.setFechaInicioString(json.getString("fechaYHoraInicio"));
                qya.setFechaFinString(json.getString("fechaYHoraFin"));
            }catch (Exception e) {
            	return null;
    		}
    		return qya;
    	}
    //****************************************************************************

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaYHoraInicio() {
        return this.fechaYHoraInicio;
    }

    public void setFechaYHoraInicio(Date fechaYHoraInicio) {
    	if(fechaYHoraInicio == null){
            throw new RuntimeException(MensajesExcepciones.fechaYHora);
		}
        this.fechaYHoraInicio = fechaYHoraInicio;
    }

    public Date getFechaYHoraFin() {
        return this.fechaYHoraFin;
    }

    public void setFechaYHoraFin(Date fechaYHoraFin) {
        this.fechaYHoraFin = fechaYHoraFin;
    }
    
    public String getFechaInicioString() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String fechaString = df.format(getFechaYHoraInicio());
		return fechaString;
	}
	
	public void setFechaInicioString(String fecha) {
		java.sql.Date fecFormatoDate = null;
		try {
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecha).getTime());
		      setFechaYHoraInicio(fecFormatoDate);
		} catch (Exception ex) {
		}
	}
	
	public String getFechaFinString() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String fechaString = df.format(getFechaYHoraFin());
		return fechaString;
	}
	
	public void setFechaFinString(String fecha) {
		java.sql.Date fecFormatoDate = null;
		try {
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecha).getTime());
		      setFechaYHoraFin(fecFormatoDate);
		} catch (Exception ex) {
		}
	}
}