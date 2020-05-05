package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id")
	private int id;
	
	@Column(name = "texto")
	private String texto;
	
	@Basic
	@Column(name = "fechaYHora")
	private Date fechaYHora;
	
	@OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true)
    @JoinColumn(name = "nombre_Fan")
	private Fan miFan;

	public Comentario() {
	}
	
	public Comentario(int id, String texto, Date fechaYHora, Fan miFan) {
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