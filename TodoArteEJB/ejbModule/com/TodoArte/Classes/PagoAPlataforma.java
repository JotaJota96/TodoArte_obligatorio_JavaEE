package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

public class PagoAPlataforma implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "monto")
    private float monto;
	
	@Basic
	@Column(name = "fechaYHora")
    private Date fechaYHora;

    public PagoAPlataforma() {
    }

    public PagoAPlataforma(int id, float monto, Date fechaYHora) {
        this.id = id;
        this.monto = monto;
        this.fechaYHora = fechaYHora;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return this.monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaYHora() {
        return this.fechaYHora;
    }

    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}