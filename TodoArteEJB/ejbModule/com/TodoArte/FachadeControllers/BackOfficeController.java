package com.TodoArte.FachadeControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;

public class BackOfficeController implements BackOfficeInterface {

	public BackOfficeController() {}
	
	@Override
	public void bloquearDesbloquearArtista(String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bloquearDesbloquearContenido(int idContenido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Reporte> obtenerReportes(int idContenido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notificarArtista(String idArtista, NotificacionArtista notificacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarCategoriaDeSitio(CategoriaSitio categoriaSitio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarCategoriaDeContenido(CategoriaContenido categoriaContenido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Administrador iniciarSesion(String id, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PagoAPlataforma> obtenerPagos(String idArtista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrador agregarAdministrador(Administrador administrador) {
		// TODO Auto-generated method stub
		return null;
	}

}
