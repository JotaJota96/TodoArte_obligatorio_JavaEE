package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "categoriaSitio")
public class CategoriaSitio implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombreCategoria")
    private String nombreCat;

    public CategoriaSitio() {
    }
    
    public CategoriaSitio(int id, String nombreCat) {
    	if(nombreCat.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombreCatSitio);
    	}
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