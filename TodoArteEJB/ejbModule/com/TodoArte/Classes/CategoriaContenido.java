package com.TodoArte.Classes;

public class CategoriaContenido {
	private int Id;
	private String NombreCategoria;

	public CategoriaContenido() {
	}

	public CategoriaContenido(int Id, String NombreCategoria) {
		this.Id = Id;
		this.NombreCategoria = NombreCategoria;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getNombreCategoria() {
		return this.NombreCategoria;
	}

	public void setNombreCategoria(String NombreCategoria) {
		this.NombreCategoria = NombreCategoria;
	}
}