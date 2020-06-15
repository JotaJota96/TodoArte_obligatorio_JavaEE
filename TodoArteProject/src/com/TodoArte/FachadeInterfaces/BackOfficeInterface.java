package com.TodoArte.FachadeInterfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Reporte;

@Local
public interface BackOfficeInterface {
	/**
	 * Bloquea o desbloquea un artista. Si el artista se encuentra bloqueado, lo desbloquea. Si el artista se  encuentra desbloqueado, lo bloquea
	 * @param idArtista ID del artista a bloquear o desbloquear
	 */
	public void bloquearDesbloquearArtista(String idArtista);

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
	 * Envia una notificacion a un artista
	 * @param idArtista ID del artista a notificar
	 * @param notificacion Notificacion a enviar
	 */
	public void notificarArtista(String idArtista, NotificacionArtista notificacion);
	
	/**
	 * Agrega al sistema una nueva categoria de sitios
	 * @param categoriaSitio Categoria a agregar
	 * @return categoria creada
	 */
	public CategoriaSitio agregarCategoriaDeSitio(CategoriaSitio categoriaSitio);

	/**
	 * Agrega al sistema una nueva categoria de contenido
	 * @param categoriaContenido Categoria a agregar
	 * @return categoria creada
	 */
	public CategoriaContenido agregarCategoriaDeContenido(CategoriaContenido categoriaContenido);
	
	/**
	 * Agrega al sistema una nueva fuente
	 * @param fuente Fuente a agregar
	 * @return
	 */
	public Fuente agregarFuente(Fuente fuente);
	
	/**
	 * Valida los datos de inicio de sesion de un administrador
	 * @param nickname ID (nickname o email) del administrador
	 * @param contrasenia Contrasenia del administrador
	 * @return si los datos son correctos retorna el administrador que ha iniciado sesion, de lo contrario NULL
	 */
	public Administrador iniciarSesion(String id, String contrasenia);
	
	/**
	 * Obtiene el listado de pagos realizados por un artista especifico
	 * @param idArtista ID del artista
	 * @return Listado de pagos realizados por el artista
	 */
	public ArrayList<PagoAPlataforma> obtenerPagos(String idArtista);
	
	/**
	 * Agrega un nuevo administrador al sistema
	 * @param administrador Nuevo administrador
	 * @return retorna el administrador creado
	 */
	public Administrador agregarAdministrador(Administrador administrador);
}









