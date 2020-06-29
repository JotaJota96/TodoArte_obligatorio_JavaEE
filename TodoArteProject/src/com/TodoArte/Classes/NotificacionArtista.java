package com.TodoArte.Classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;

@Entity
@Table(name = "notificacionArtista")
public class NotificacionArtista implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic
    @Column(name = "fechaYhora")
    private Date fechaYHora;

    public NotificacionArtista() {
    }

    public NotificacionArtista(int id, String titulo, String descripcion, Date fechaYHora) {
    	if(titulo.equals("")){
            throw new RuntimeException(MensajesExcepciones.titulo);
		}
    	if(descripcion.equals("")){
            throw new RuntimeException(MensajesExcepciones.descripcion);
		}
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaYHora = new Date(System.currentTimeMillis());
    }

    //**********************************************************************************************
	/**
	 * Ordena los elementos de la coleccion
	 * @param lst Coleccion de elementos a ordenar
	 * @param inverso TRUE si se desea invertir el ordenamiento
	 * @return Coleccion de elementos ordenados
	 */
    public static ArrayList<NotificacionArtista> ordenar(ArrayList<NotificacionArtista> lst, boolean inverso){
		Map<Integer, NotificacionArtista> mapa = new TreeMap<Integer, NotificacionArtista>();
		for (NotificacionArtista n : lst) {
			mapa.put(n.getId(), n);
		}
		return ordenar(mapa, inverso);
	}
    /**
	 * Ordena los elementos de la coleccion
     * @param mapa Coleccion de elementos a ordenar
     * @param inverso TRUE si se desea invertir el ordenamiento
     * @return Coleccion de elementos ordenados
     */
	public static ArrayList<NotificacionArtista> ordenar(Map<Integer, NotificacionArtista> mapa, boolean inverso){
		ArrayList<NotificacionArtista> lst = new ArrayList<NotificacionArtista>();
		for (Map.Entry<Integer, NotificacionArtista> entry : mapa.entrySet()) {
			if (inverso) {
				lst.add(0, entry.getValue());
			}else {
				lst.add(entry.getValue());
			}
		}
		return lst;
	}
	
    //**********************************************************************************************
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