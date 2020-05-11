package com.TodoArte.FachadeControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

public class FrontOfficeController implements FrontOfficeInterface{

	@Override
	public void calificarContenido(Valoracion val, String idFan, int idContenido, String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NotificacionArtista> listarNotificacionesArtista(String idArtista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comprarContenido(String idFan, int idContenido, int idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comprarPremium(String idFan, String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suscribirseFanArtista(String idFan, String idArtista) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fan registrarUsuarioFan(Fan fan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artista registrarUsuarioArtista(Artista artista, Sitio sitio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contenido agregarModificarContenido(String idArtista, Contenido contenido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarContenido(String idArtista, int idContenido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QyAProgramado programarQyA(String idArtista, QyAProgramado qyaProgramado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtenerDatosUsuario(String idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Contenido> obtenerContenido(String idArtista, String idFan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bloquearDesbloquearUsuarioDeSitio(String idArtista, String idFan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Fan> obtenerFansDeSitio(String idArtista) {
		// TODO Auto-generated method stub
		return null;
	}

}
