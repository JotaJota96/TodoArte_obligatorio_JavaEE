package com.TodoArte.InternalControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.ContenidoInterface;
import com.TodoArte.JPAControllerClasses.ContenidoJpaController;

public class ContenidoController implements ContenidoInterface{

	public ContenidoController() {}

	@Override
	public void bloquearDesbloquearContenido(int idContenido) {
		// obtener el contenido por su ID
		// actualizar el estado de bloqueado
	}

	@Override
	public ArrayList<Reporte> obtenerReportes(int idContenido) {
		// obtener el contenido por su ID
		// obtener sus reportes y convertirlos en ArrayList para devolverlos
		return null;
	}

	@Override
	public void calificarContenido(Valoracion val, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido la valoracion y el fan
		// update contenido
	}

	@Override
	public void comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido el comentario y el fan
		// update contenido
		
	}

	@Override
	public void reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// pasarle al contenido el reporte y el fan
		// update contenido
		
	}

	@Override
	public void comprarContenido(String idFan, int idContenido, int idArtista) {
		// Pedirle el fan al Controlador de Fan
		// obtener el contenido por su ID
		// si el fan no tiene suficiente saldo: error
		// decirle al controlador de fan que le descuente del saldo el costo del contenido
		// pasarle al contenido el fan
		// update contenido
		
	}

	@Override
	public Contenido agregarModificarContenido(String idArtista, Contenido contenido) {
		// si el contenido existe, modificarlo
		// si no existe, decirle al controlador de artista que agregue e contenido para ese artista
		boolean agregar = (contenido.getId() == 0);
		
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
		return null;
	}
}
