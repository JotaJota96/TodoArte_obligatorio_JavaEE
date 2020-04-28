package com.TodoArte.Classes;

import java.util.Date;

public class QyAProgramado {
    private int id;
    private Date fechaYHoraInicio;
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