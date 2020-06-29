package com.TodoArte.InternalInterfaces;

import java.util.ArrayList;

import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Valoracion;

public interface ContenidoInterface {
	/**
	 * Bloquea o desbloquea un contenido. Si el contenido se encuentra bloqueado, lo desbloquea. Si el contenido se encuentra desbloqueado, lo bloquea
	 * @param idContenido ID del contenido a bloquear o desbloquear
	 */
	public void bloquearDesbloquearContenido(int idContenido);

	/**
	 * Obtiene todos los reportes de un contenido
	 * @param idContenido ID del contenido
	 * @return Lista de los reportes del contenido
	 */
	public ArrayList<Reporte> obtenerReportes(int idContenido);

	/**
	 * Calificar un contenido especifico
	 * @param val la Valoracion realizado
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 * @param idArtista el id del artista propietario del comentario
	 */
	public void calificarContenido(Valoracion val, String idFan, int idContenido, String idArtista);
	
	/**
	 * Comenta un contenido especifico
	 * @param comentario realizado
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 * @param idArtista el id del artista propietario del comentario
	 */
	public void comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista);
	
	/**
	 * Reporta un contenido especifico
	 * @param reporte realizado por el usuario
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 * @param idArtista el id del artista propietario del comentario
	 */
	public void reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista);
	
	/**
	 * Agrega un comentario a un contenido de un artista
	 * @param idFan nickname del usuario que compro el contenido
	 * @param idContenido es el id del contenido comprado
	 * @param idArtista nickname del artista duenio del contenido
	 */
	public void comprarContenido(String idFan, int idContenido, int idArtista);
	
	/**
	 * Agrega en el sistema, un contenido a un artista. Si el artista ya tiene un contenido con el ID recibido, lo actualiza
	 * @param idArtista ID del artista propietario del contenido
	 * @param contenido Contenido a agregar o modificar
	 * @return retorna el contenido agregado o modificado
	 */
	public Contenido agregarModificarContenido(String idArtista, Contenido contenido);
	
	/**
	 * Elimina un contenido especifico de un artista especifico
	 * @param idArtista ID del artista propietario del contenido
	 * @param idContenido ID del contenido a eliminar
	 */
	public void eliminarContenido(String idArtista, int idContenido);
	/**
	 * Obtiene un contenido especifico de un artista especifico
	 * @param idArtista ID del artista propietario del contenido que se desea obtener
	 * @param idContenido ID del contenido que se desea obtener
	 * @param idFan ID del fan que quiere obtener la informacion (idFan = "" si el usuario es invitado)
	 * @return Si el artista posee un contenido con ese ID, lo devuelve, de lo contrario retorna NULL
	 */
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan);

}
