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
@Table(name = "notificacionFan")
public class NotificacionFan implements Serializable {
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


    public NotificacionFan() {
    }

    public NotificacionFan(int id, String titulo, String descripcion, Date fechaYHora) {
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
    public static ArrayList<NotificacionFan> ordenar(ArrayList<NotificacionFan> lst, boolean inverso){
		Map<Integer, NotificacionFan> mapa = new TreeMap<Integer, NotificacionFan>();
		for (NotificacionFan n : lst) {
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
	public static ArrayList<NotificacionFan> ordenar(Map<Integer, NotificacionFan> mapa, boolean inverso){
		ArrayList<NotificacionFan> lst = new ArrayList<NotificacionFan>();
		for (Map.Entry<Integer, NotificacionFan> entry : mapa.entrySet()) {
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