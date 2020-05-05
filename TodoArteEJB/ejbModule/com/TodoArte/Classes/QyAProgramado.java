package com.TodoArte.Classes;

import java.util.Date;

import java.io.Serializable;
import javax.persistence.*;

public class QyAProgramado implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Basic
	@Column(name = "fechaYHoraInicio")
    private Date fechaYHoraInicio;
    
	@Basic
	@Column(name = "fechaYHoraFin")
    private Date fechaYHoraFin;

    public QyAProgramado() {
    }

    public QyAProgramado(int id, Date fechaYHoraInicio, Date fechaYHoraFin) {
        this.id = id;
        this.fechaYHoraInicio = fechaYHoraInicio;
        this.fechaYHoraFin = fechaYHoraFin;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaYHoraInicio() {
        return this.fechaYHoraInicio;
    }

    public void setFechaYHoraInicio(Date fechaYHoraInicio) {
        this.fechaYHoraInicio = fechaYHoraInicio;
    }

    public Date getFechaYHoraFin() {
        return this.fechaYHoraFin;
    }

    public void setFechaYHoraFin(Date fechaYHoraFin) {
        this.fechaYHoraFin = fechaYHoraFin;
    }
}