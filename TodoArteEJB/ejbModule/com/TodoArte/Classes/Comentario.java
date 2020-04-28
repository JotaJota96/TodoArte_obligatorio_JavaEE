package com.TodoArte.Classes;

import java.util.Date;

public class Comentario {
	private int Id;
	private String texto;
	private Date fechaYHora;
	private Fan miFan;

	public Comentario() {
	}
	
	public Comentario(int id, String texto, Date fechaYHora, Fan miFan) {
		Id = id;
		this.texto = texto;
		this.fechaYHora = fechaYHora;
		this.miFan = miFan;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaYHora() {
		return this.fechaYHora;
	}

	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		this.miFan = miFan;
	}
}