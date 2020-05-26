package com.TodoArte.Classes;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.JPAControllerClasses.NotificacionArtistaJpaController;
import com.TodoArte.JPAControllerClasses.NotificacionFanJpaController;

@Entity
@Table(name = "fan")
public class Fan extends Usuario implements Serializable {
	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "apellido")
    private String apellido;
	
	@Basic
	@Column(name = "fecha_nacimiento")
    private Date fechaNac;
	
	@Column(name = "ubicacion")
    private String ubicacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo")
    private Sexo sexo;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "id_fan")
	@MapKey(name = "id")
    private Map<Integer, NotificacionFan> notificaciones;

	@OneToMany(mappedBy = "miFan")
	private Map<Integer, FanSigueSitio> misSitiosSeguidos;

    public Fan() {
        super();
        this.misSitiosSeguidos = new TreeMap<Integer, FanSigueSitio>();
		this.notificaciones = new TreeMap<Integer, NotificacionFan>();
    }

	public Fan(String nikname, String contrasenia, String correo, float saldo, byte[] imagen, String nombre, String apellido, Date fechaNac, String ubicacion, Sexo sexo) {
		super(nikname, contrasenia, correo, saldo, imagen);
		if(nombre.equals("") || nombre.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		if(apellido.equals("") || apellido.equals(null)){
    		throw new RuntimeException(MensajesExcepciones.apellido);
    	}
		if(fechaNac == null){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
		Date fechaActual = new Date(System.currentTimeMillis());
		
		java.util.Date fechaNacUtil = new java.util.Date(fechaNac.getTime());
		java.util.Date fechaActualUtil = new java.util.Date(fechaActual.getTime());
		
		if(fechaNacUtil.compareTo(fechaActualUtil) <= 0 ){
    		throw new RuntimeException(MensajesExcepciones.fechaPosterior);
		}
		
		if(sexo == null){
    		throw new RuntimeException(MensajesExcepciones.sexo);
    	}

		
		fechaNac.setYear(fechaNac.getYear()-1900);
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.ubicacion = ubicacion;
		this.sexo = sexo;
        this.misSitiosSeguidos = new TreeMap<Integer, FanSigueSitio>();
		this.notificaciones = new TreeMap<Integer, NotificacionFan>();
	}
	//***************************************************************************

	public static String codificar(Fan fan) {
		JsonObject json = Json.createObjectBuilder()
	        .add("nombre", fan.getNombre())
	        .add("apellido", fan.getApellido())
	        .add("ubicacion", fan.getUbicacion())
	        .add("fechaNac", fan.getFechaString())
	        .add("nikname", fan.getNikname())
	        .add("contrasenia", fan.getContrasenia())
	        .add("correo", fan.getCorreo())
	        .add("saldo", fan.getSaldo())
	        .add("sexo", fan.getSexo().toString())
	      //.add("imagen", msj.getImagen())
           .build();
		
		StringWriter strWriter = new StringWriter();
		try (JsonWriter jsonWriter = Json.createWriter(strWriter)) {jsonWriter.write(json);}
		return strWriter.toString();
	}
	
	public static Fan decodificar(String strJson) {
		StringReader reader = new StringReader(strJson);
		
		Fan fan = new Fan();
		
        try (JsonReader jsonReader = Json.createReader(reader)) {
            JsonObject json = jsonReader.readObject();
            fan.setNombre(json.getString("nombre"));
            fan.setApellido(json.getString("apellido"));
            fan.setUbicacion(json.getString("ubicacion"));
            fan.setFechaString(json.getString("fechaNac"));
            fan.setNikname(json.getString("nikname"));
            fan.setContrasenia(json.getString("contrasenia"));
            fan.setCorreo(json.getString("correo"));
            fan.setSaldo(json.getInt("saldo"));
          //fan.setImagen(json.getInt("imagen"));
          //fan.setSexo(json.getString("sexo"));
        }catch (Exception e) {
        	return null;
		}
		return fan;
	}
	
	public void agregarNotificacion(NotificacionFan notificacion) {
		notificacion.setId(0);
		new NotificacionFanJpaController().create(notificacion);
		this.notificaciones.put(notificacion.getId(), notificacion);
    }
    
	//***************************************************************************
	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
    	if(nombre.equals("")){
    		throw new RuntimeException(MensajesExcepciones.nombre);
    	}
		
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
    	if(apellido.equals("")){
    		throw new RuntimeException(MensajesExcepciones.apellido);
    	}
		
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return this.fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
    	if(fechaNac == null){
    		throw new RuntimeException(MensajesExcepciones.fechaYHora);
    	}
		
        this.fechaNac = fechaNac;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {	
        this.ubicacion = ubicacion;
    }

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		if(sexo == null){
    		throw new RuntimeException(MensajesExcepciones.sexo);
    	}
		
		this.sexo = sexo;
	}

	public Map<Integer, NotificacionFan> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Map<Integer, NotificacionFan> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Map<Integer, FanSigueSitio> getMisSitiosSeguidos() {
		return misSitiosSeguidos;
	}

	public void setMisSitiosSeguidos(Map<Integer, FanSigueSitio> misSitiosSeguidos) {
		this.misSitiosSeguidos = misSitiosSeguidos;
	}
	
	
	public String getFechaString() {
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		String fechaString = df.format(getFechaNac());
		return fechaString;
	}
	
	public void setFechaString(String fecha) {
		java.sql.Date fecFormatoDate = null;
		try {
		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		      fecFormatoDate = new java.sql.Date(sdf.parse(fecha).getTime());
		      setFechaNac(fecFormatoDate);
		} catch (Exception ex) {
		}
	}

	@Override
	public String toString() {
		return "Fan [nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", ubicacion="
				+ ubicacion + ", sexo=" + sexo + ", notificaciones=" + notificaciones + ", misSitiosSeguidos="
				+ misSitiosSeguidos + ", nikname=" + nikname + ", contrasenia=" + contrasenia + ", correo=" + correo
				+ ", saldo=" + saldo + ", imagen=" + Arrays.toString(imagen) + ", bloqueado=" + bloqueado + "]";
	}
	
}