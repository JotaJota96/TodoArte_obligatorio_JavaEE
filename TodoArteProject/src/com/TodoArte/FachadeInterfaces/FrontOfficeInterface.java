package com.TodoArte.FachadeInterfaces;

import java.util.ArrayList;

import javax.ejb.Local;

import com.TodoArte.Classes.*;

@Local
public interface FrontOfficeInterface {
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
	 * Obtiene las notificacion de un fan especifico
	 * @param idFan nickname del usuario del que se decea listar las notificaciones
	 * @return debuelve las notificaciones del usaurio en un ArrayList
	 */
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan);

	/**
	 * Obtiene las notificacion de un fan especifico
	 * @param idArtista nickname del usuario del que se decea listar las notificaciones
	 * @return debuelve las notificaciones del usaurio en un ArrayList
	 */
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista);
	
	/**
	 * Agrega un comentario a un contenido de un artista
	 * @param idFan nickname del usuario que compro el contenido
	 * @param idContenido es el id del contenido comprado
	 * @param idArtista nickname del artista due�o del contenido
	 */
	public void comprarContenido(String idFan, int idContenido, int idArtista);
	
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
	 * Registra un nuevo fan en el sistema
	 * @param Fan Fan a registrar
	 * @return devuelve el fan creado
	 */
	public Fan registrarUsuarioFan(Fan fan);
	
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
	 * Programa un QyA para un artista
	 * @param idArtista ID del artista que realizará el QyA
	 * @param qyaProgramado QyA a programar
	 * @return retorna el QyA programado creado
	 */
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado);
	
	/**
	 * Devuelve los datos del usuario, sea Fan o Artista
	 * @param idUsuario ID del usuario que se desea obtener
	 * @return Si el usuario existe, devuelve sus datos, de lo contrario NULL
	 */
	public Usuario obtenerDatosUsuario(String idUsuario);
	
	/**
	 * Recarga el saldo de un usuario, sea fan o artista
	 * @param idUsuario ID del usuario al que se le quiere recargar su saldo
	 * @param monto Monto que se desea agregar al saldo actual
	 */
	public void recargarSaldo(String idUsuario, float monto);
	
	/**
	 * Obtiene un contenido especifico de un artista especifico
	 * @param idArtista ID del artista propietario del contenido que se desea obtener
	 * @param idContenido ID del contenido que se desea obtener
	 * @param idFan ID del fan que quiere obtener la informacion (idFan = "" si el usuario es invitado)
	 * @return Si el artista posee un contenido con ese ID, lo devuelve, de lo contrario retorna NULL
	 */
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan);
	
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
	 * Devuelve todos las categorias Sitios
	 * @return Debuelve un array de categorias Sitios
	 */
	public ArrayList<CategoriaSitio> obtenerCategoriasSitios();
	

	/**
	 * Devuelve todos las Fuente
	 * @return Debuelve un array de Fuente
	 */
	public ArrayList<Fuente> obtenerFuentes();
	

	/**
	 * Devuelve todos las Categoria Contenido
	 * @return Debuelve un array de Categoria Contenido
	 */
	public ArrayList<CategoriaContenido> obtenerCategoriasContenido();

	/**
	 * Devuelve una CategoriaSitio en espesifico
	 * @param idCategoriaSitio
	 * @return una CategoriaSitio si no existe devuelve null
	 */
	public CategoriaSitio obtenerUnaCategoriasSitios(int idCategoriaSitio);
	
	/**
	 * Devuelve una Fuente en espesifico
	 * @param idFuente
	 * @return una Fuente si no existe devuelve null
	 */
	public Fuente obtenerUnaFuentes(int idFuente);
	
	/**
	 * Devuelve una CategoriaContenido en espesifico
	 * @param idCategoriaContenido
	 * @return una CategoriaContenido si no existe devuelve null
	 */
	public CategoriaContenido obtenerUnaCategoriasContenido(int idCategoriaContenido);
	
	/**
	 * Devuelve todos los artistas
	 * @return Debuelve un array de Artista
	 */
	public ArrayList<Artista> listarArtistas();
	
	/**
	 * Devuelve todos los Contenido
	 * @return Debuelve un array de Contenido
	 */
	public ArrayList<Contenido> listarContenido();
	
	/**
	 * Devuelve un Contenido en especifico
	 *  @param idContenido id del contenido que se quiere obtener
	 * @return Debuelve Contenido
	 */
	public Contenido obtenerUnContenido(int idContenido);
	
	
}

