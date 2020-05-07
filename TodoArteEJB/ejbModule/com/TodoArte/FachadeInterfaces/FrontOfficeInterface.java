package com.TodoArte.FachadeInterfaces;

import java.util.ArrayList;

import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Valoracion;

public interface FrontOfficeInterface {
	/**
	 * 
	 * @param val la Valoracion realizado
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 */
	public void calificarContenido(Valoracion val, String idFan, int idContenido);
	
	/**
	 * 
	 * @param comentario realizado
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 */
	public void comentarContenido(Comentario comentario, String idFan, int idContenido);
	
	/**
	 * 
	 * @param reporte realizado por el usuario
	 * @param idFan nickname del usuario que realizo el comentario
	 * @param idContenido el id del contenido al cual se hizo el comentario
	 */
	public void reportarContenido(Reporte reporte, String idFan, int idContenido);
	
	/**
	 * 
	 * @param idFan nickname del usuario que se decea listar las notificaciones
	 * @return debuelve las notificaciones del usaurio en un ArrayList
	 */
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan);
	
	/**
	 * 
	 * @param idFan nickname del usuario que compro el contenido
	 * @param idContenido es el id del contenido comprado
	 * @param idArtista nickname del artista dueño del contenido
	 */
	public void comprarContenido(String idFan, int idContenido, int idArtista);
	
	/**
	 * 
	 * @param idFan nickname del fan que compra el premium
	 * @param idArtista nickname del artista
	 */
	public void comprarPremium(String idFan, String idArtista);
	
	/**
	 * 
	 * @param idFan nickname del fan que se suscribio
	 * @param idArtista nickname del artista
	 */
	public void suscribirseAArtista(String idFan, String idArtista);
}
