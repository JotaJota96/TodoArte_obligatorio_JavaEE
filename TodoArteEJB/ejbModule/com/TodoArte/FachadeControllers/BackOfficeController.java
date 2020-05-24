package com.TodoArte.FachadeControllers;

import java.util.ArrayList;

import javax.ejb.Stateless;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;
import com.TodoArte.InternalControllers.AdminController;
import com.TodoArte.InternalControllers.ArtistaController;
import com.TodoArte.InternalControllers.ContenidoController;

@Stateless
public class BackOfficeController implements BackOfficeInterface {

	public BackOfficeController() {}
	
	@Override
	public void bloquearDesbloquearArtista(String idArtista) {
		new ArtistaController().bloquearDesbloquearArtista(idArtista);
	}

	@Override
	public void bloquearDesbloquearContenido(int idContenido) {
		new ContenidoController().bloquearDesbloquearContenido(idContenido);
	}

	@Override
	public ArrayList<Reporte> obtenerReportes(int idContenido) {
		return new ContenidoController().obtenerReportes(idContenido);
	}

	@Override
	public void notificarArtista(String idArtista, NotificacionArtista notificacion) {
		new ArtistaController().notificarArtista(idArtista, notificacion);
	}

	@Override
	public CategoriaSitio agregarCategoriaDeSitio(CategoriaSitio categoriaSitio) {
		return new AdminController().agregarCategoriaDeSitio(categoriaSitio);
	}

	@Override
	public CategoriaContenido agregarCategoriaDeContenido(CategoriaContenido categoriaContenido) {
		return new AdminController().agregarCategoriaDeContenido(categoriaContenido);
	}

	@Override
	public Fuente agregarFuente(Fuente fuente) {
		return new AdminController().agregarFuente(fuente);
	}
	
	@Override
	public Administrador iniciarSesion(String id, String contrasenia) {
		return new AdminController().iniciarSesion(id, contrasenia);
	}

	@Override
	public ArrayList<PagoAPlataforma> obtenerPagos(String idArtista) {
		return new ArtistaController().obtenerPagos(idArtista);
	}

	@Override
	public Administrador agregarAdministrador(Administrador administrador) {
		return new AdminController().agregarAdministrador(administrador);
	}

}
