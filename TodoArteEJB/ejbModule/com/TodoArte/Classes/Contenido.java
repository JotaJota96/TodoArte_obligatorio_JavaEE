package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.TodoArte.Enums.Privacidad;
import com.TodoArte.Enums.TipoContenido;

@Entity
@Table(name = "contenido")
public class Contenido implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tipoContenido")
	@Enumerated(EnumType.STRING)
	private TipoContenido tipo;
	
	@Column(name = "privacidad")
	@Enumerated(EnumType.STRING)
	private Privacidad privacidad;
	
	@Column(name = "precio")
	private float precio;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "archivo")
	@Lob
	private byte[] archivo;
	
	@Basic
	@Column(name = "fechaPublicado")
	private Date fechaPublicado;
	
	@Column(name = "bloqueado")
	private boolean bloqueado;
	
	@Column(name = "eliminado")
	private boolean eliminado;
	
	
	@ManyToOne()
    @JoinColumn(name = "id_categoria")
	private CategoriaContenido miCategoria;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_contenido")
	@MapKey(name = "id")
	private Map<Integer, Venta> MisVentas;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_contenido")
	@MapKey(name = "id")
	private Map<Integer, Reporte> MisReporte;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_contenido")
	@MapKey(name = "id")
	private Map<Integer, Comentario> MisComentario;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_contenido")
	@MapKey(name = "id")
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