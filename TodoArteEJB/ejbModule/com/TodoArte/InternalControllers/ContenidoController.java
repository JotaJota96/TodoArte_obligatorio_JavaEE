package com.TodoArte.InternalControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.InternalInterfaces.ContenidoInterface;

public class ContenidoController implements ContenidoInterface{

	public ContenidoController() {}

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
	public void comprarContenido(String idFan, int idContenido, int idArtista) {
		// TODO Auto-generated method stub
		
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
	public Contenido obtenerContenido(String idArtista, int idContenido, String idFan) {
		// TODO Auto-generated method stub
		return null;
	}
}
