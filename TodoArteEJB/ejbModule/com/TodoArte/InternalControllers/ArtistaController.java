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
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
import com.TodoArte.JPAControllerClasses.SitioJpaController;


public class ArtistaController implements ArtistaInterface{
	
	public ArtistaController() {}

	@Override
	public void bloquearDesbloquearArtista(String idArtista) {
		// actualizar el artista bloqueado o desbloqueado
		
	}

	@Override
	public void notificarArtista(String idArtista, NotificacionArtista notificacion) {
		ArtistaJpaController aJpa = new ArtistaJpaController();
		Artista a = aJpa.findArtista(idArtista);
		if (a == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		a.agregarNotificacion(notificacion);
		try {
			aJpa.edit(a);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
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
		if (artista == null) {
			throw new RuntimeException(MensajesExcepciones.artista);
		}
		if (sitio == null) {
			throw new RuntimeException(MensajesExcepciones.sitio);
		}
		FanInterface fc = new FanController();
		ArtistaJpaController aJpa = new ArtistaJpaController();
		SitioJpaController sJpa = new SitioJpaController();

		if (aJpa.findArtista(artista.getNikname()) != null || fc.obtenerDatosUsuario(artista.getNikname()) != null || fc.obtenerDatosUsuario(artista.getCorreo()) != null) {
			throw new RuntimeException(MensajesExcepciones.usuarioExiste);
		}
		artista.setMiSitio(sitio);
		sitio.setMiArtista(artista);

		aJpa.create(artista, sitio);
		
		return artista;
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		// verifica los datos de inicio de sesion del artista
		// el id puede ser nickname o email
		return null;
	}

	@Override
	public Usuario obtenerDatosUsuario(String idUsuario) {
		// obtener el artista por su ID y devolverlo (nill si no se encuentra)
		return new ArtistaJpaController().findArtista(idUsuario);
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
