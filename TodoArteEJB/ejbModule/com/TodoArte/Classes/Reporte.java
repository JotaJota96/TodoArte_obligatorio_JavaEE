package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "reporte")
public class Reporte implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "reporte")
	private String reporte;
	
	@ManyToOne()
	@JoinColumn(name = "id_Fan")
	private Fan miFan;

	public Reporte() {
	}

	public Reporte(int id, String reporte, Fan miFan) {
		this.id = id;
		this.setReporte(reporte);
		this.miFan = miFan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		this.miFan = miFan;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	
	
}