package com.TodoArte.Classes;

public class Reporte {
	private int Id;
	private String reporte;
	private Fan miFan;

	public Reporte() {
	}

	public Reporte(int id, String reporte, Fan miFan) {
		Id = id;
		this.setReporte(reporte);
		this.miFan = miFan;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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