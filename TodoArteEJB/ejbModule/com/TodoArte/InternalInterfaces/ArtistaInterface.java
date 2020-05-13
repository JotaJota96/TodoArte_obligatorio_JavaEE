package com.TodoArte.InternalInterfaces;

import java.util.ArrayList;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;

public interface ArtistaInterface {
	
	/**
	 * Bloquea o desbloquea un artista. Si el artista se encuentra bloqueado, lo desbloquea. Si el artista se  encuentra desbloqueado, lo bloquea
	 * @param idArtista ID del artista a bloquear o desbloquear
	 */
	public void bloquearDesbloquearArtista(String idArtista);
	
	/**
	 * Envia una notificacion a un artista
	 * @param idArtista ID del artista a notificar
	 * @param notificacion Notificacion a enviar
	 */
	public void notificarArtista(String idArtista, NotificacionArtista notificacion);

	/**
	 * Obtiene el listado de pagos realizados por un artista especifico
	 * @param idArtista ID del artista
	 * @return Listado de pagos realizados por el artista
	 */
	public ArrayList<PagoAPlataforma> obtenerPagos(String idArtista);

	/**
	 * Obtiene las notificacion de un fan especifico
	 * @param idArtista nickname del usuario del que se decea listar las notificaciones
	 * @return debuelve las notificaciones del usaurio en un ArrayList
	 */
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista);
	
	/**
	 * Actualiza el tipo de suscripcion de un fan al sitio de un artista a Premium
	 * @param idFan nickname del fan que compra el premium
	 * @param idArtista nickname del artista
	 */
	public void comprarPremium(String idFan, String idArtista);
	
	/**
	 * Suscribe un fan a un artista
	 * @param idFan nickname del fan que se suscribio
	 * @param idArtista nickname del artista
	 */
	public void suscribirseFanArtista(String idFan, String idArtista);
	
	/**
	 * Registra un nuevo artista y su sitio en el sistema
	 * @param artista Artista a registrar
	 * @param sitio Sitio del artista a registrar
	 * @return devuelve el artista creado (no su sitio)
	 */
	public Artista registrarUsuarioArtista(Artista artista, Sitio sitio);

	/**
	 * Valida los datos de inicio de sesion de un usuario (sea fan o artista)
	 * Si el usuario se encuentra bloqueado, no se le permite iniciar sesion y se retorna FALSE
	 * @param idUsuario ID (nickname o email) del usuario que desea iniciar sesion
	 * @param contrasenia Contrasenia del usuario
	 * @return si los datos son correctos retorna el usuario que ha iniciado sesion, de lo contrario NULL
	 */
	public Usuario iniciarSesion(String idUsuario, String contrasenia);
	
	/**
	 * Devuelve los datos del usuario, sea Fan o Artista
	 * @param idUsuario ID del usuario que se desea obtener
	 * @return Si el artista existe, devuelve sus datos, de lo contrario NULL
	 */
	public Artista obtenerDatosUsuario(String idUsuario);

	/**
	 * Recarga el saldo de un usuario
	 * @param idUsuario ID del usuario al que se le quiere recargar su saldo
	 * @param monto Monto que se desea agregar al saldo actual
	 */
	public void recargarSaldo(String idUsuario, float monto);

	/**
	 * Descuenta el saldo de un usuario
	 * @param idUsuario ID del usuario al que se le quiere recargar su saldo
	 * @param monto Monto que se desea agregar al saldo actual
	 */
	public void descontarSaldo(String idUsuario, float monto);

	/**
	 * Devuelve todo los contenidos de un artista especifico
	 * @param idArtista ID del artista propietario del contenido que se desea obtener
	 * @param idFan ID del fan que quiere obtener la informacion (idFan = "" si el usuario es invitado)
	 * @return Si el ID es correcto, devuelve los contenidos del artista, de lo contrario retorna NULL
	 */
	public ArrayList<Contenido> obtenerContenido(String idArtista, String idFan);
	
	/**
	 * Bloquea o desbloque a un fan del sitio de un artista. Si el fan no se encuentra bloqueado aun, lo bloquea, si ya se encuentra bloqueado, lo desbloquea.
	 * @param idArtista ID del artista del sitio afectado
	 * @param idFan ID del fan a bloquear del sitio
	 */
	public void bloquearDesbloquearUsuarioDeSitio(String idArtista, String idFan);
	
	/**
	 * Devuelve todos los fans que siguen al sitio del artista especificado
	 * @param idArtista ID del artista del que se desea conocer sus fans seguidores
	 * @return Si el ID es correcto, devuelve los fans del sitio, de lo contrario retorna NULL
	 */
	public ArrayList<Fan> obtenerFansDeSitio(String idArtista);

	/**
	 * Programa un QyA para un artista
	 * @param idArtista ID del artista que realizará el QyA
	 * @param qyaProgramado QyA a programar
	 * @return retorna el QyA programado creado
	 */
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado);
	
	/**
	 * Agrega el contenido al artista especificado
	 * @param idArtista ID del artista al que se le desea agregar contenido
	 * @param contenido Contenido que se desea agregar
	 * @return
	 */
	public Contenido agregarContenido(String idArtista, Contenido contenido);
	
	/**
	 * Elimina un contenido especifico de un artista
	 * @param idArtista ID del artista propietario del contenido a eliminar
	 * @param idContenido ID del contenido a eliminar
	 * @return
	 */
	public Contenido eliminarContenido(String idArtista, int idContenido);

}
