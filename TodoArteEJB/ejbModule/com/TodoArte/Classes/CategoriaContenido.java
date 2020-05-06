package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
		this.id = Id;
		this.NombreCategoria = NombreCategoria;
	}

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
		this.NombreCategoria = NombreCategoria;
	}
}