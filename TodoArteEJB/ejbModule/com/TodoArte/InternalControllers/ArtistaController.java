package com.TodoArte.InternalControllers;

import java.util.ArrayList;
import java.util.Map;

import org.jboss.resteasy.spi.ReaderException;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.SitioJpaController;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaContenidoJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;
import com.TodoArte.JPAControllerClasses.FanSigueSitioJpaController;
import com.TodoArte.JPAControllerClasses.FuenteJpaController;


public class ArtistaController implements ArtistaInterface{
	
	public ArtistaController() {}

	@Override
	public void bloquearDesbloquearArtista(String idArtista) {
		// actualizar el artista bloqueado o desbloqueado
		ArtistaJpaController ajpa = new ArtistaJpaController();
		Artista art = ajpa.findArtista(idArtista);
		if(art == null){
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		
		if(art.getBloqueado() == true){
			art.setBloqueado(false);
		}
		else {
			art.setBloqueado(true);
		}
		
		try {
			ajpa.edit(art);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
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
		
		Artista artista = (Artista) this.obtenerDatosUsuario(idArtista);
		if (artista == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		
		ArrayList<PagoAPlataforma> ret = new ArrayList<PagoAPlataforma>();
		
		for (Map.Entry<Integer, PagoAPlataforma> entry : artista.getPagos().entrySet()) {
			ret.add(entry.getValue());
		}
		
		return ret;
	}

	@Override
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista) {
		// obtener las notificaciones del artista y convertirlos en ArrayList
		Artista f = (Artista) this.obtenerDatosUsuario(idArtista);
		if (f == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		ArrayList<NotificacionArtista> ret = new ArrayList<NotificacionArtista>();
		for (Map.Entry<Integer, NotificacionArtista> entry : f.getNotificacion().entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}

	@Override
	public void comprarPremium(String idFan, String idArtista) {
		// obtener artista
		// obtener su sitio
		// pedir al controlador fan que le descuente
		
		Artista artista = new ArtistaJpaController().findArtista(idArtista);
		Sitio sitio = artista.getMiSitio();
		
		if(sitio.getPrecioPremium() > new FanController().obtenerDatosUsuario(idFan).getSaldo()){
			throw new RuntimeException(MensajesExcepciones.saldoInsuficiente);
		}
		
		new FanController().descontarSaldo(idFan, sitio.getPrecioPremium());
		sitio.comprarPremium(idFan);
	}

	@Override
	public void suscribirseFanArtista(String idFan, String idArtista) {
		// obtener artista
		// obtener su sitio
		// decirle al sitio que cree y devuelva el nuevo FanSigueASitio
		Sitio sitio = new ArtistaJpaController().findArtista(idArtista).getMiSitio();
		Fan fan = new FanJpaController().findFan(idFan);
		if(sitio == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		if(fan == null) {
			throw new RuntimeException(MensajesExcepciones.fanNoExiste);
		}
		FanSigueSitio fss = sitio.agregarSeguidor(fan);
		new FanController().vincularFanASitio(fss, idFan);
		
		try {
			new SitioJpaController().edit(sitio);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Artista registrarUsuarioArtista(Artista artista, Sitio sitio) {
		if (artista == null) {
			throw new RuntimeException(MensajesExcepciones.artista);
		}
		
		if (sitio == null) {
			throw new RuntimeException(MensajesExcepciones.sitio);
		}
		
		if(new CategoriaContenidoJpaController().findCategoriaContenido(sitio.getMiCategoria().getId()) == null){
			throw new RuntimeException(MensajesExcepciones.categoriaSitio);
		}
		
		if(new FuenteJpaController().findFuente(sitio.getMiFuente().getId()) == null){
			throw new RuntimeException(MensajesExcepciones.fuenteSitio);
		}
		
		if(artista.getNikname().equals("") || artista.getNikname().equals(null)){
			throw new RuntimeException(MensajesExcepciones.nickname);
		}
		
		if(artista.getCorreo().equals("") || artista.getCorreo().equals(null)){
			throw new RuntimeException(MensajesExcepciones.correo);
		}
		
		if(new ArtistaJpaController().findArtista(artista.getNikname()) != null || new ArtistaJpaController().findArtista(artista.getCorreo()) != null){
			throw new RuntimeException(MensajesExcepciones.artistaExiste);
		}
		
		FanInterface fc = new FanController();
		ArtistaJpaController aJpa = new ArtistaJpaController();

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
		
		ArtistaJpaController Ajpa = new ArtistaJpaController();
		Artista artista = Ajpa.findArtista(idUsuario);
		if(artista != null){
			if(artista.getContrasenia().equals(contrasenia)){
				return artista;
			}
		}
		return null;
	}

	@Override
	public Artista obtenerDatosUsuario(String idUsuario) {
		// obtener el artista por su ID y devolverlo (nill si no se encuentra)
		return new ArtistaJpaController().findArtista(idUsuario);
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		// obtener el artista por id e incrementar su saldo
		
		Artista artista = new ArtistaJpaController().findArtista(idUsuario);
		artista.setSaldo(artista.getSaldo() + monto);
		try {
			new ArtistaJpaController().edit(artista);
		} catch (Exception e) {
			throw new ReaderException(e.getMessage());
		}
	}

	@Override
	public ArrayList<Contenido> obtenerContenido(String idArtista, String idFan) {
		// obtener el artista por id
		// obtener el sitio del artista
		// extraer todos los contenidos del sitio, convertirlo a ArrayList y devolver
		
		Sitio sitioArtista = new ArtistaJpaController().findArtista(idArtista).getMiSitio();
		if (sitioArtista == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		ArrayList<Contenido> ret = new ArrayList<Contenido>();
		ContenidoController cjpa = new ContenidoController();
		
		for (Map.Entry<Integer, Contenido> entry : sitioArtista.getMisContenidos().entrySet()) {
			try {
				ret.add(cjpa.obtenerContenido(idArtista, entry.getValue().getId(), idFan));
			} catch (Exception e) {
			}
		}
		
		return ret;
	}

	@Override
	public void bloquearDesbloquearUsuarioDeSitio(String idArtista, String idFan) {
		// obtener el artista por id
		// obtener el sitio del artista
		// decirle al sitio que bloquee el fan por id
		
		Sitio sitioArtista = new ArtistaJpaController().findArtista(idArtista).getMiSitio();
		sitioArtista.bloquearDesbloquearUsuarioDeSitio(idFan);
	}

	@Override
	public ArrayList<Fan> obtenerFansDeSitio(String idArtista) {
		// obtener el artista por id
		// obtener el sitio del artista
		// decirle al sitio que devuelva un listado con los nicknames de los fans que lo siguen
		// para cada id obtenido, decirle al controlador de fans que lo devuelva y agregarlo al array de retorno
		Sitio sitioArtista = new ArtistaJpaController().findArtista(idArtista).getMiSitio();
		
		if (sitioArtista == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		
		ArrayList<Fan> ret = new ArrayList<Fan>();
		ArrayList<String> idFans = sitioArtista.obtenerIdDeFans();
		
		for (String entry : idFans){
			ret.add(new FanController().obtenerDatosUsuario(entry));
		}
		
		return ret;
	}
		
	

	@Override
	public void descontarSaldo(String idUsuario, float monto) {
		// obtener el artista por id
		// descuenta el monto del saldo del artista
		

		Artista artista = new ArtistaJpaController().findArtista(idUsuario);
		
		if(artista.getSaldo() < monto){
			throw new ReaderException(MensajesExcepciones.saldoInsuficiente);
		}
		
		artista.setSaldo(artista.getSaldo() - monto);
		
		try {
			new ArtistaJpaController().edit(artista);
		} catch (Exception e) {
			throw new ReaderException(e.getMessage());
		}
	}

	@Override
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado) {
		// obtener el artista por id
		// obtener su sitio
		// decirle al sitio que agregue el QyA, y devuelve lo obtenido
		Sitio s = ((Artista) this.obtenerDatosUsuario(idArtista)).getMiSitio();
		QyAProgramado ret = s.programarQyA(qyaProgramado);
		try {
			new SitioJpaController().edit(s);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return ret;
	}

	@Override
	public Contenido agregarContenido(String idArtista, Contenido contenido) {
		// obtener el artista por id
		// obtener el sitio de ese artista
		// decirle al sitio del artista que agregue el contenido
		Artista a = new ArtistaJpaController().findArtista(idArtista);
		if (a == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		Sitio s = a.getMiSitio();
		contenido = s.agregarContenido(contenido);
		try {
			new SitioJpaController().edit(s);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return contenido;
	}

	@Override
	public void eliminarContenido(String idArtista, int idContenido) {
		// obtener el artista por id
		// obtener el sitio de ese artista
		// decirle al sitio del artista que elimine el contenido
		// update del sitio
		
		Artista artista = new ArtistaJpaController().findArtista(idArtista);
		Sitio sitio = artista.getMiSitio();
		sitio.eliminarContenido(idContenido);
		
		try {
			new SitioJpaController().edit(sitio);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
    
}
