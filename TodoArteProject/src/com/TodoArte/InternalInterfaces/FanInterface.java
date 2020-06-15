package com.TodoArte.InternalInterfaces;

import java.util.ArrayList;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Usuario;

public interface FanInterface {
	
	/**
	 * Registra un nuevo fan en el sistema
	 * @param Fan Fan a registrar
	 * @return devuelve el fan creado
	 */
	public Fan registrarUsuarioFan(Fan fan);
	
	/**
	 * Obtiene las notificacion de un fan especifico
	 * @param idFan nickname del usuario del que se decea listar las notificaciones
	 * @return debuelve las notificaciones del usaurio en un ArrayList
	 */
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan);

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
	 * @return Si el usuario existe, devuelve sus datos, de lo contrario NULL
	 */
	public Fan obtenerDatosUsuario(String idUsuario);
	
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
	 * Vincula a un fan con un sitio
	 * @param fanSigueSitio Instancia con la que se debe vincular
	 * @param idFan ID del Fan que se debe vincular
	 */
	public void vincularFanASitio(FanSigueSitio fanSigueSitio, String idFan);

	/**
	 * Envia una notificacion a un fan
	 * @param idFan ID del fan a notificar
	 * @param notificacion Notificacion a enviar
	 */
	public void notificarFan(String idFan, NotificacionFan notificacion);

	
}
