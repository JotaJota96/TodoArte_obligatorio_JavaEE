package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "precio")
	private float precio;
	
	@Basic
	@Column(name = "fechaYHora")
	private Date fechaYHora;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "nombre_Fan")
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