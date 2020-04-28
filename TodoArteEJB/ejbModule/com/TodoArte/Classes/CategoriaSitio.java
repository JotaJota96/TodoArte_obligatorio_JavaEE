package com.TodoArte.Classes;

public class CategoriaSitio {
	private int id;
    private String nombreCat;

    public CategoriaSitio() {
    }
    
    public CategoriaSitio(int id, String nombreCat) {
		this.id = id;
		this.nombreCat = nombreCat;
	}

	public String getNombreCat() {
        return this.nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}