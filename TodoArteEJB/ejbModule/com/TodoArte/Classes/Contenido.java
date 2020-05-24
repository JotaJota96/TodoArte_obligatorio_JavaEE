package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
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
import javax.validation.constraints.Null;

import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Privacidad;
import com.TodoArte.Enums.TipoContenido;
import com.TodoArte.JPAControllerClasses.ComentarioJpaController;
import com.TodoArte.JPAControllerClasses.ReporteJpaController;
import com.TodoArte.JPAControllerClasses.ValoracionJpaController;
import com.TodoArte.JPAControllerClasses.VentaJpaController;

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
		if(tipo == null){
    		throw new RuntimeException(MensajesExcepciones.tipoContenido);
    	}
		if(privacidad == null){
    		throw new RuntimeException(MensajesExcepciones.privacidadContenido);
    	}
		if(privacidad != privacidad.Premium){
			precio = 0;
		}
		if(privacidad == privacidad.Premium){
			if (precio <= 0) {
	    		throw new RuntimeException(MensajesExcepciones.precio);
			}
		}
		
		fechaPublicado.setYear(fechaPublicado.getDay()-1900);
		
		if(descripcion.equals("") || descripcion.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.descripcion);
    	}
		if(titulo.equals("") || titulo.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.titulo);
    	}
		if(fechaPublicado.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
		Date fechaActual = new Date(System.currentTimeMillis());
		
		java.util.Date fechaNacUtil = new java.util.Date(fechaPublicado.getTime());
		java.util.Date fechaActualUtil = new java.util.Date(fechaActual.getTime());
		
		if(fechaNacUtil.compareTo(fechaActualUtil) < 0 ){
    		throw new RuntimeException(MensajesExcepciones.fechaPosterior);
		}
		
		if(miCategoria == null){
    		throw new RuntimeException(MensajesExcepciones.categoria);
    	}
		
		
		this.id = id;
		this.tipo = tipo;
		this.privacidad = privacidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.titulo = titulo;
		this.archivo = archivo;
		this.fechaPublicado = fechaPublicado;
		this.bloqueado = false;
		this.eliminado = false;
		this.miCategoria = miCategoria;
		MisVentas = new TreeMap<Integer, Venta>();
		MisReporte = new TreeMap<Integer, Reporte>();
		MisComentario = new TreeMap<Integer, Comentario>();
		MisValoracion = new TreeMap<Integer, Valoracion>();
	}
	//***************************************************************************
	public static String codificar(Contenido contenido) {
			JsonObject json = Json.createObjectBuilder()
		        .add("id", contenido.getId())
		        .add("tipo", contenido.getTipo().toString())
		        .add("privacidad", contenido.getPrivacidad().toString())
		        .add("precio", contenido.getPrecio())
		        .add("descripcion", contenido.getDescripcion())
		        .add("titulo", contenido.getTitulo())
		        .add("fechaPublicado", contenido.getFechaString())
		        .add("bloqueado", contenido.getBloqueado())
		        .add("eliminado", contenido.getEliminado())
		        //.add("archivo", contenido.getArchivo())
	           .build();
			
			StringWriter strWriter = new StringWriter();
			try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
			return strWriter.toString();
		}
		
		public static Contenido decodificar(String strJson) {
			StringReader reader = new StringReader(strJson);
			
			Contenido contenido = new Contenido();
			
	        try (JsonReader jsonReader = Json.createReader(reader)) {
	            JsonObject json = jsonReader.readObject();
	            contenido.setId(json.getInt("id"));
	            contenido.setPrecio(json.getInt("precio"));
	            contenido.setDescripcion(json.getString("descripcion"));
	            contenido.setTitulo(json.getString("titulo"));
	            contenido.setFechaString(json.getString("fechaPublicado"));
	            contenido.setBloqueado(json.getBoolean("bloqueado"));
	            contenido.setEliminado(json.getBoolean("eliminado"));
	            

	            //contenido.setTipo(json.getString("tipo"));
	            //contenido.setPrivacidad(json.getString("privacidad"));
	            //contenido.setArchivo(json.getInt("archivo"));
	        }catch (Exception e) {
	        	return null;
			}
			return contenido;
		}
	
	public void crearValoracion(Valoracion val, Fan fan) {
		// vincular la valoracion con el fan
		// persistir la valoracion
		// agregarlo a la coleccion
		
		val.setMiFan(fan);
		new ValoracionJpaController().create(val);
		this.MisValoracion.put(val.getId(), val);
	}

	public void crearComentario(Comentario comentario, Fan fan) {
		// vincular el comentario con el fan
		// persistir el comentario
		// agregarlo a la coleccion
		
		comentario.setMiFan(fan);
		new ComentarioJpaController().create(comentario);
		this.MisComentario.put(comentario.getId(), comentario);
	}
	
	public void crearReporte(Reporte reporte, Fan fan) {
		// vincular el reporte con el fan
		// persistir el reporte
		// agregarlo a la coleccion
		
		reporte.setMiFan(fan);
		new ReporteJpaController().create(reporte);
		this.MisReporte.put(reporte.getId(), reporte);
	}

	public void crearVenta(Fan fan) {
		// crear una nueva venta
		// vincular la venta con el fan
		// persistir la venta
		// agregarlo a la coleccion
		
		Date fechaYHora = new Date(System.currentTimeMillis());
		Venta venta = new Venta(0, this.getPrecio(), fechaYHora, fan);
		new VentaJpaController().create(venta);
		this.MisVentas.put(venta.getId(), venta);
	}
	
	//***************************************************************************
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
		if(tipo == null){
    		throw new RuntimeException(MensajesExcepciones.tipoContenido);
    	}
		
		this.tipo = tipo;
	}

	public Privacidad getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(Privacidad privacidad) {
		if(privacidad == null){
    		throw new RuntimeException(MensajesExcepciones.privacidadContenido);
    	}
		
		this.privacidad = privacidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		if(privacidad != privacidad.Premium){
			if(precio < 0){
	    		throw new RuntimeException(MensajesExcepciones.precio);
	    	}
		}
		
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if(descripcion.equals("")){
    		throw new RuntimeException(MensajesExcepciones.descripcion);
    	}
		
		this.descripcion = descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo.equals("")){
    		throw new RuntimeException(MensajesExcepciones.titulo);
    	}
		
		this.titulo = titulo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		if(archivo.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.archivo);
    	}
		
		this.archivo = archivo;
	}

	public Date getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(Date fechaPublicado) {
		if(fechaPublicado.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
		this.fechaPublicado = fechaPublicado;
	}

	public boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public CategoriaContenido getMiCategoria() {
		return miCategoria;
	}

	public void setMiCategoria(CategoriaContenido miCategoria) {
		if(miCategoria == null){
    		throw new RuntimeException(MensajesExcepciones.categoria);
    	}
		
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
	
	public String getFechaString() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String fechaString = df.format(getFechaPublicado());
		return fechaString;
	}
	
	public void setFechaString(String fecha) {
		java.sql.Date fecFormatoDate = null;
		try {
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecha).getTime());
		      setFechaPublicado(fecFormatoDate);
		} catch (Exception ex) {
		}
	}
	
}