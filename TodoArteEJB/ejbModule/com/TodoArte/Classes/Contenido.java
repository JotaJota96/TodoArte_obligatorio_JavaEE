package com.TodoArte.Classes;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.TodoArte.Enums.Privacidad;
import com.TodoArte.Enums.TipoContenido;

public class Contenido {
	private int id;
	private TipoContenido tipo;
	private Privacidad privacidad;
	private float precio;
	private String descripcion;
	private String titulo;
	private byte[] archivo;
	private Date fechaPublicado;
	private boolean bloqueado;
	private boolean eliminado;
	private CategoriaContenido miCategoria;
	private Map<Integer, Venta> MisVentas;
	private Map<Integer, Reporte> MisReporte;
	private Map<Integer, Comentario> MisComentario;
	private Map<Integer, Valoracion> MisValoracion;

	public Contenido() {
		MisVentas = new TreeMap<Integer, Venta>();
		MisReporte = new TreeMap<Integer, Reporte>();
		MisComentario = new TreeMap<Integer, Comentario>();
		MisValoracion = new TreeMap<Integer, Valoracion>();
	}
	
	public Contenido(int id, TipoContenido tipo, Privacidad privacidad, int precio, String descripcion, String titulo, byte[] archivo, Date fechaPublicado, boolean bloqueado, boolean eliminado, CategoriaContenido miCategoria) {
		this.id = id;
		this.tipo = tipo;
		this.privacidad = privacidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.titulo = titulo;
		this.archivo = archivo;
		this.fechaPublicado = fechaPublicado;
		this.bloqueado = bloqueado;
		this.eliminado = eliminado;
		this.miCategoria = miCategoria;
		MisVentas = new TreeMap<Integer, Venta>();
		MisReporte = new TreeMap<Integer, Reporte>();
		MisComentario = new TreeMap<Integer, Comentario>();
		MisValoracion = new TreeMap<Integer, Valoracion>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoContenido getTipo() {
		return tipo;
	}

	public void setTipo(TipoContenido tipo) {
		this.tipo = tipo;
	}

	public Privacidad getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(Privacidad privacidad) {
		this.privacidad = privacidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public Date getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(Date fechaPublicado) {
		this.fechaPublicado = fechaPublicado;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public CategoriaContenido getMiCategoria() {
		return miCategoria;
	}

	public void setMiCategoria(CategoriaContenido miCategoria) {
		this.miCategoria = miCategoria;
	}

	public Map<Integer, Venta> getMisVentas() {
		return MisVentas;
	}

	public void setMisVentas(Map<Integer, Venta> misVentas) {
		MisVentas = misVentas;
	}

	public Map<Integer, Reporte> getMisReporte() {
		return MisReporte;
	}

	public void setMisReporte(Map<Integer, Reporte> misReporte) {
		MisReporte = misReporte;
	}

	public Map<Integer, Comentario> getMisComentario() {
		return MisComentario;
	}

	public void setMisComentario(Map<Integer, Comentario> misComentario) {
		MisComentario = misComentario;
	}

	public Map<Integer, Valoracion> getMisValoracion() {
		return MisValoracion;
	}

	public void setMisValoracion(Map<Integer, Valoracion> misValoracion) {
		MisValoracion = misValoracion;
	}
	
	
}