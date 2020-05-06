package com.TodoArte.Classes;

import java.io.Serializable;
import java.sql.Date;

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
}