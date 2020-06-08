package com.TodoArte.FachadeControllers;

import java.util.ArrayList;

import javax.ejb.Stateless;

import org.jboss.resteasy.spi.ReaderException;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;
import com.TodoArte.InternalControllers.ArtistaController;
import com.TodoArte.InternalControllers.ContenidoController;
import com.TodoArte.InternalControllers.FanController;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaContenidoJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaSitioJpaController;
import com.TodoArte.JPAControllerClasses.ContenidoJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;
import com.TodoArte.JPAControllerClasses.FuenteJpaController;

@Stateless
public class FrontOfficeController implements FrontOfficeInterface{
	
	public FrontOfficeController() {}

	@Override
	public void calificarContenido(Valoracion val, String idFan, int idContenido, String idArtista) {
			new ContenidoController().calificarContenido(val, idFan, idContenido, idArtista);
	}

	@Override
	public void comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista) {
		new ContenidoController().comentarContenido(comentario, idFan, idContenido, idArtista);
	}

	@Override
	public void reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista) {
		new ContenidoController().reportarContenido(reporte, idFan, idContenido, idArtista);		
	}

	@Override
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan) {
		return new FanController().listarNotificacionesFan(idFan);
	}

	@Override
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista) {
		return new ArtistaController().listarNotificacionesArtista(idArtista);
	}

	@Override
	public void comprarContenido(String idFan, int idContenido, int idArtista) {
		new ContenidoController().comprarContenido(idFan, idContenido, idArtista); 
	}

	@Override
	public void comprarPremium(String idFan, String idArtista) {
		new ArtistaController().comprarPremium(idFan, idArtista);
	}

	@Override
	public void suscribirseFanArtista(String idFan, String idArtista) {
		new ArtistaController().suscribirseFanArtista(idFan, idArtista);
	}

	@Override
	public Fan registrarUsuarioFan(Fan fan) {
		return new FanController().registrarUsuarioFan(fan);
	}

	@Override
	public Artista registrarUsuarioArtista(Artista artista, Sitio sitio) {
		return new ArtistaController().registrarUsuarioArtista(artista, sitio);
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		Usuario usu = new FanController().iniciarSesion(idUsuario, contrasenia);
		if(usu != null) {
			return usu;
		}
		usu = new ArtistaController().iniciarSesion(idUsuario, contrasenia);
		if(usu != null) {
			return usu;
		}		
		return null;
	}

	@Override
	public Contenido agregarModificarContenido(String idArtista, Contenido contenido) {
		return new ContenidoController().agregarModificarContenido(idArtista, contenido);
	}

	@Override
	public void eliminarContenido(String idArtista, int idContenido) {
		new ContenidoController().eliminarContenido(idArtista, idContenido);
	}

	@Override
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado) {
		return new ArtistaController().programarQyA(idArtista, qyaProgramado);
	}

	@Override
	public Usuario obtenerDatosUsuario(String idUsuario) {
		Usuario u = new FanController().obtenerDatosUsuario(idUsuario);
		if (u == null) {
			u = new ArtistaController().obtenerDatosUsuario(idUsuario);
		}
		return u;
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		Artista artista = new ArtistaJpaController().findArtista(idUsuario);
		Fan fan = new FanJpaController().findFan(idUsuario);
		
		if(fan == null && artista == null){
			throw new ReaderException(MensajesExcepciones.usuarioNoExiste);
		}
		
		if(artista != null) {
			new ArtistaController().recargarSaldo(idUsuario, monto);
		}
		
		if(fan != null){
			new FanController().recargarSaldo(idUsuario, monto);
		}
	}

	@Override
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan) {
		return new ContenidoController().obtenerContenido(idArtista, idContenido, idFan);
	}

	@Override
	public ArrayList<Contenido> obtenerContenido(String idArtista, String idFan) {
		return new ArtistaController().obtenerContenido(idArtista, idFan);
	}

	@Override
	public void bloquearDesbloquearUsuarioDeSitio(String idArtista, String idFan) {
		new ArtistaController().bloquearDesbloquearUsuarioDeSitio(idArtista, idFan);
		
	}

	@Override
	public ArrayList<Fan> obtenerFansDeSitio(String idArtista) {
		return new ArtistaController().obtenerFansDeSitio(idArtista);
	}

	@Override
	public ArrayList<CategoriaSitio> obtenerCategoriasSitios() {
		ArrayList<CategoriaSitio> ret = new ArrayList<CategoriaSitio>(new CategoriaSitioJpaController().findCategoriaSitioEntities());
		return ret;
	}

	@Override
	public ArrayList<Fuente> obtenerFuentes() {
		ArrayList<Fuente> ret = new ArrayList<Fuente>(new FuenteJpaController().findFuenteEntities());
		return ret;
	}

	@Override
	public ArrayList<CategoriaContenido> obtenerCategoriasContenido() {
		ArrayList<CategoriaContenido> ret = new ArrayList<CategoriaContenido>(new CategoriaContenidoJpaController().findCategoriaContenidoEntities());
		return ret;
	}

	@Override
	public CategoriaSitio obtenerUnaCategoriasSitios(int idCategoriaSitio) {
		return new CategoriaSitioJpaController().findCategoriaSitio(idCategoriaSitio);
	}

	@Override
	public Fuente obtenerUnaFuentes(int idFuente) {
		return new FuenteJpaController().findFuente(idFuente);
	}

	@Override
	public CategoriaContenido obtenerUnaCategoriasContenido(int idCategoriaContenido) {
		return new CategoriaContenidoJpaController().findCategoriaContenido(idCategoriaContenido);
	}

	@Override
	public ArrayList<Artista> listarArtistas() {
		ArrayList<Artista> ret = new ArrayList<Artista>(new ArtistaJpaController().findArtistaEntities());
		return ret;
	}

	@Override
	public ArrayList<Contenido> listarContenido() {
		ArrayList<Contenido> ret = new ArrayList<Contenido>(new ContenidoJpaController().findContenidoEntities());
		return ret;
	}

	@Override
	public Contenido obtenerUnContenido(int idContenido) {
		return new ContenidoJpaController().findContenido(idContenido);
	}

}
