package com.TodoArte.InternalControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;

public class ArtistaController implements ArtistaInterface{
	
	FanInterface fc = new FanController();
	
	public ArtistaController() {}

	@Override
	public void bloquearDesbloquearArtista(String idArtista) {
		// actualizar el artista bloqueado o desbloqueado
		
	}

	@Override
	public void notificarArtista(String idArtista, NotificacionArtista notificacion) {
		// obtener el artista por id
		// decirle que registre la notificacion
		
	}

	@Override
	public ArrayList<PagoAPlataforma> obtenerPagos(String idArtista) {
		// obtener los pagos del artista y convertirlos en ArrayList
		return null;
	}

	@Override
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista) {
		// obtener las notificaciones del artista y convertirlos en ArrayList
		return null;
	}

	@Override
	public void comprarPremium(String idFan, String idArtista) {
		// obtener artista
		// obtener su sitio
		// pedir al controlador fan que le descuente
	}

	@Override
	public void suscribirseFanArtista(String idFan, String idArtista) {
		// obtener artista
		// obtener su sitio
		// decirle al sitio que cree y devuelva el nuevo FanSigueASitio
		
	}

	@Override
	public Artista registrarUsuarioArtista(Artista artista, Sitio sitio) {
		// Crea un nuevo artista
		// un nuevo sitio
		// los vincula
		// los persiste, y retorna el artista
		// recordar validar
		return null;
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		// verifica los datos de inicio de sesion del artista
		// el id puede ser nickname o email
		
		ArtistaJpaController Ajpa = new ArtistaJpaController();
		Artista artista = Ajpa.findArtista(idUsuario);
		if(artista != null){
			if(artista.getContrasenia() == contrasenia){
				return artista;
			}
		}
		return null;
	}

	@Override
	public Usuario obtenerDatosUsuario(String idUsuario) {
		// obtener el artista por su ID y devolverlo (nill si no se encuentra)
		return null;
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		// obtener el artista por id e incrementar su saldo
		
	}

	@Override
	public ArrayList<Contenido> obtenerContenido(String idArtista, String idFan) {
		// obtener el artista por id
		// obtener el sitio del artista
		// extraer todos los contenidos del sitio, convertirlo a ArrayList y devolver
		return null;
	}

	@Override
	public void bloquearDesbloquearUsuarioDeSitio(String idArtista, String idFan) {
		// obtener el artista por id
		// obtener el sitio del artista
		// decirle al sitio que bloquee el fan por id
		
	}

	@Override
	public ArrayList<Fan> obtenerFansDeSitio(String idArtista) {
		// obtener el artista por id
		// obtener el sitio del artista
		// decirle al sitio que devuelva un listado con los nicknames de los fans que lo siguen
		// para cada id obtenido, decirle al controlador de fans que lo devuelva y agregarlo al array de retorno
		return null;
		
	}

	@Override
	public void descontarSaldo(String idUsuario, float monto) {
		// obtener el artista por id
		// descuenta el monto del saldo del artista
	}

	@Override
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado) {
		// obtener el artista por id
		// obtener su sitio
		// decirle al sitio que agregue el QyA, y devuelve lo obtenido
		return null;
	}

	@Override
	public Contenido agregarContenido(String idArtista, Contenido contenido) {
		// obtener el artista por id
		// obtener el sitio de ese artista
		// decirle al sitio del artista que agregue el contenido
		return null;
	}

	@Override
	public Contenido eliminarContenido(String idArtista, int idContenido) {
		// obtener el artista por id
		// obtener el sitio de ese artista
		// decirle al sitio del artista que elimine el contenido
		// update del sitio
		return null;
	}

	
	
}
