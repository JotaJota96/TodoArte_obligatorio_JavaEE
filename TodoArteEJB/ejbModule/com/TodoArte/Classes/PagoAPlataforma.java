package com.TodoArte.Classes;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pagoAPlataforma")
public class PagoAPlataforma implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "moto")
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