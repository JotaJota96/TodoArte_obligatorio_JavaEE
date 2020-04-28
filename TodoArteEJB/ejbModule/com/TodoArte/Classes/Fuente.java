package com.TodoArte.Classes;

public class Fuente {
	private int id;
    private String nombre;

    public Fuente() {
    }
    
    public Fuente(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
    
	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}