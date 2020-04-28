package com.TodoArte.Classes;

import java.util.Date;

public class NotificacionArtista {
    private int id;
    private String titulo;
    private String descripcion;
    private Date fechaYHora;

    public NotificacionArtista() {
    }

    public NotificacionArtista(int id, String titulo, String descripcion, Date fechaYHora) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaYHora = fechaYHora;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaYHora() {
        return this.fechaYHora;
    }

    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}