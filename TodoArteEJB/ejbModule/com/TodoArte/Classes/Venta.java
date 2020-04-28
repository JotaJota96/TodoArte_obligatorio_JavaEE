package com.TodoArte.Classes;

import java.util.Date;

public class Venta {
	private int id;
	private float precio;
	private Date fechaYHora;
	private Fan miFan;

	public Venta() {
	}

	public Venta(int id, float precio, Date fechaYHora, Fan miFan) {
		this.id = id;
		this.precio = precio;
		this.fechaYHora = fechaYHora;
		this.miFan = miFan;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
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