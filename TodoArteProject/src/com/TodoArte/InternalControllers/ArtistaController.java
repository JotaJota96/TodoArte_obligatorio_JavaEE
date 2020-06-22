package com.TodoArte.InternalControllers;

import java.time.LocalDate;
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
import com.TodoArte.Classes.Venta;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.Enums.Privacidad;
import com.TodoArte.Enums.TipoContenido;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.SitioJpaController;

import beans.FuncionesComunes;

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
				if(entry.getValue().getEliminado() == false) {
					if(idArtista.equals(idFan)){
						ret.add(cjpa.obtenerContenido(idArtista, entry.getValue().getId(), idFan));
					}else {
						if(entry.getValue().getBloqueado() == false){
							ret.add(cjpa.obtenerContenido(idArtista, entry.getValue().getId(), idFan));
						}
					}					
				}
			} catch (Exception e) {
			}
		}
		
		return ret;
	}


	public ArrayList<Contenido> obtenerContenidoPremium(String idArtista, String idFan) {
		// obtener el artista por id
		// obtener el sitio del artista
		// extraer todos los contenidos premium del sitio, convertirlo a ArrayList y devolver
		
		Sitio sitioArtista = new ArtistaJpaController().findArtista(idArtista).getMiSitio();
		if (sitioArtista == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		ArrayList<Contenido> ret = new ArrayList<Contenido>();
		
		if (sitioArtista.fanBloqueado(idFan)) {
			return ret;
		}
		
		for (Map.Entry<Integer, Contenido> entry : sitioArtista.getMisContenidos().entrySet()) {
			if(entry.getValue().getEliminado() == false) {
				if(idArtista.equals(idFan)) {
					if (entry.getValue().getPrivacidad() == Privacidad.Premium) {
						ret.add(entry.getValue());
					}
				}else {
					if(entry.getValue().getBloqueado() == false){
						if (entry.getValue().getPrivacidad() == Privacidad.Premium) {
							ret.add(entry.getValue());
						}
					}
				}
				
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

	public void pagarAPlataforma(String idArtista) {
		float montoBase = 100; // pesos
		float porcentajeSeguidoresPremium = 0.1F; // porciento
		float porcentajeContenidoVendido = 0.05F; // porciento
		float total = 0;
		
		Artista a = new ArtistaJpaController().findArtista(idArtista);
		if (a == null) {
			throw new RuntimeException(MensajesExcepciones.artistaNoExiste);
		}
		Sitio s = a.getMiSitio();

		// pago base
		total += montoBase;
		
		// pago por suscriptores premium
		float contador = 0;
		for (Map.Entry<Integer, FanSigueSitio> entry : s.getMisFans().entrySet()) {
			if (entry.getValue().getPremiun()) {
				contador++;
			}
		}
		total += (contador * s.getPrecioPremium()) * porcentajeSeguidoresPremium;
		
		// pago por contenido vendido
		float montoContenidoVendido = 0;
		for (Map.Entry<Integer, Contenido> entry : s.getMisContenidos().entrySet()) {
			for (Map.Entry<Integer, Venta> entrada : entry.getValue().getMisVentas().entrySet()) {
				if (entrada.getValue().getFechaYHora().compareTo(FuncionesComunes.haceUnMes()) >= 0) {
					montoContenidoVendido += entrada.getValue().getPrecio();
				}
			}
		}
		total += montoContenidoVendido * porcentajeContenidoVendido;
		
		PagoAPlataforma pap = new PagoAPlataforma(0, total, FuncionesComunes.fechaActual());
		
		a.pagarAPlataforma(pap);
		a.setSaldo(a.getSaldo() - pap.getMonto());
		try {
			new ArtistaJpaController().edit(a);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
    
}







