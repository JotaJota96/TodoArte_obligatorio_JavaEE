package com.TodoArte.InternalControllers;

import java.util.ArrayList;
import java.util.Map;

import javax.validation.constraints.Null;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Privacidad;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.ContenidoInterface;
import com.TodoArte.JPAControllerClasses.CategoriaContenidoJpaController;
import com.TodoArte.JPAControllerClasses.ContenidoJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;

public class ContenidoController implements ContenidoInterface{

	public ContenidoController() {}

	@Override
	public void bloquearDesbloquearContenido(int idContenido) {
		// obtener el contenido por su ID
		// actualizar el estado de bloqueado
		ContenidoJpaController cJpa = new ContenidoJpaController();
		Contenido contenido = cJpa.findContenido(idContenido);
		
		if(contenido == null){
			throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
		}
		
		if(contenido.getBloqueado() == true){
			contenido.setBloqueado(false);
		}
		else {
			contenido.setBloqueado(true);
		}
		
		try {
			cJpa.edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public ArrayList<Reporte> obtenerReportes(int idContenido) {
		// obtener el contenido por su ID
		// obtener sus reportes y convertirlos en ArrayList para devolverlos
		Contenido contenido = new ContenidoJpaController().findContenido(idContenido);
		
		if(contenido == null){
			throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
		}
				
		ArrayList<Reporte> ret = new ArrayList<Reporte>();
		
		for (Map.Entry<Integer, Reporte> entry : contenido.getMisReporte().entrySet()) {
			ret.add(entry.getValue());
		}
		
		return ret;
	}

	@Override
	public void calificarContenido(Valoracion val, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido la valoracion y el fan
		// update contenido
		
		Fan fan = new FanController().obtenerDatosUsuario(idFan);
		Contenido contenido = new ContenidoJpaController().findContenido(idContenido);
		
		if(val == null){
			throw new RuntimeException(MensajesExcepciones.valoracion);
		}
		if(fan == null){
			throw new RuntimeException(MensajesExcepciones.fanNoExiste);
		}
		if(contenido == null) {
			throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
		}
		
		contenido.crearValoracion(val, fan);
		
		try {
			new ContenidoJpaController().edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido el comentario y el fan
		// update contenido
		
		Fan fan = new FanController().obtenerDatosUsuario(idFan);
		Contenido contenido = new ContenidoJpaController().findContenido(idContenido);
		
		if(comentario == null){
			throw new RuntimeException(MensajesExcepciones.comentario);
		}
		if(fan == null){
			throw new RuntimeException(MensajesExcepciones.fanNoExiste);
		}
		if(contenido == null) {
			throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
		}
		
		contenido.crearComentario(comentario, fan);
		
		try {
			new ContenidoJpaController().edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}		
	}

	@Override
	public void reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido el reporte y el fan
		// update contenido
		
		Fan fan = new FanController().obtenerDatosUsuario(idFan);
		Contenido contenido = new ContenidoJpaController().findContenido(idContenido);
		
		contenido.crearReporte(reporte, fan);
		try {
			new ContenidoJpaController().edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void comprarContenido(String idFan, int idContenido, int idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// si el fan no tiene suficiente saldo: error
		// decirle al controlador de fan que le descuente del saldo el costo del contenido
		// pasarle al contenido el fan
		// update contenido
		
		Fan fan = new FanController().obtenerDatosUsuario(idFan);
		Contenido contenido = new ContenidoJpaController().findContenido(idContenido);
		
		if(contenido.getPrecio() > fan.getSaldo()) {
			throw new RuntimeException(MensajesExcepciones.saldoInsuficiente);
		}
		new FanController().descontarSaldo(idFan, contenido.getPrecio());
		
		contenido.crearVenta(fan);
		try {
			new ContenidoJpaController().edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Contenido agregarModificarContenido(String idArtista, Contenido contenido) {
		// si el contenido existe, modificarlo
		// si no existe, decirle al controlador de artista que agregue e contenido para ese artista
		boolean agregar = (contenido.getId() == 0);
		
		if(new CategoriaContenidoJpaController().findCategoriaContenido(contenido.getMiCategoria().getId()) == null){
			throw new RuntimeException(MensajesExcepciones.contenidoCategoria);
		}
		
		if (agregar) {
			contenido = new ArtistaController().agregarContenido(idArtista, contenido);
		}else {
			ContenidoJpaController cJpa = new ContenidoJpaController();
			Contenido c = cJpa.findContenido(contenido.getId());
			if (c == null) {
				throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
			}
			try {
				cJpa.edit(contenido);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return contenido;
	}

	@Override
	public void eliminarContenido(String idArtista, int idContenido) {
		// decirle al controlador de artista que elimine el contenido
		new ArtistaController().eliminarContenido(idArtista, idContenido);		
	}

	@Override
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan) {
		// obtener cntenido por su id
		// decirle al controlador de artista que nos de el artista
		// obtener el sitio del artista
		/*
		¿es fan?	¿lock?	prem..	privacidad	devolver
		no es fan					publico		contenido
									privado		error
									premium		error
		es fan		si				publico		error
									privado		error
									premium		error
					no		si		publico		contenido
									privado		contenido
									premium		contenido
							no		publico		contenido
									privado		contenido
									premium		error
		*/
		

		ContenidoJpaController Cjpa = new ContenidoJpaController();
		Contenido contenido = Cjpa.findContenido(idContenido);
		Sitio sitioArtista = new ArtistaController().obtenerDatosUsuario(idArtista).getMiSitio();
		
		if(contenido == null){
			throw new RuntimeException(MensajesExcepciones.contenidoNoExiste);
		}
		
		if(!(sitioArtista.getMisContenidos().containsKey(idContenido))) {
			throw new RuntimeException(MensajesExcepciones.ContenidoNoPertenece);
		}
			
		if (idFan.equals(idArtista)) {
			return contenido;
		}
		
		if(sitioArtista.esFan(idFan) == false) {
			if(contenido.getPrivacidad() == Privacidad.Publico){
				return contenido;				
			}
			else {
				throw new RuntimeException(MensajesExcepciones.contenidoNoEsPublico);
			}
		}
		else{
			if(sitioArtista.fanBloqueado(idFan) == true){
				throw new RuntimeException(MensajesExcepciones.fanBloqueado);
			}
			else{
				if(sitioArtista.fanEsPremium(idFan) == true){
					return contenido;
				}
				else{
					if(contenido.getPrivacidad() == Privacidad.Premium){
						throw new RuntimeException(MensajesExcepciones.FanNoPremium);
					}
					else {
						return contenido;
					}
				}
			}	
		}
	}
}
