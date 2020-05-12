package com.TodoArte.InternalControllers;

import java.util.ArrayList;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.FanJpaController;

public class FanController implements FanInterface{
	
	public FanController() {}

	@Override
	public Fan registrarUsuarioFan(Fan fan) {
		if (fan == null) {
			throw new RuntimeException(MensajesExcepciones.miFan);
		}
		ArtistaInterface ac = new ArtistaController();
		
		FanJpaController fjpa = new FanJpaController();
		if (fjpa.findFan(fan.getNikname()) != null || ac.obtenerDatosUsuario(fan.getNikname()) != null || ac.obtenerDatosUsuario(fan.getCorreo()) != null){
			throw new RuntimeException(MensajesExcepciones.usuarioExiste);
		}
		fjpa.create(fan);
		return fan;
	}

	@Override
	public ArrayList<NotificacionFan> listarNotificacionesFan(String idFan) {
		// obtener el fan por id
		// obtener sus notificaciones, convertirlas a ArrayList y devolverlas
		return null;
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		// verifica los datos de inicio de sesion del fan
		// el id puede ser nickname o email
		return null;
	}

	@Override
	public Usuario obtenerDatosUsuario(String idUsuario) {
		// obtener el fan por su ID y devolverlo (nill si no se encuentra)
		return null;
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		// obtener el fan por id e incrementar su saldo
		
	}

	@Override
	public void descontarSaldo(String idUsuario, float monto) {
		// obtener el fan por id
		// descuenta el monto del saldo del artista
		
	}

	@Override
	public void vincularFanASitio(FanSigueSitio fanSigueSitio, String idFan) {
		// obtiene el fan
		// vincular mutuamente al fan con el fanSigueSitio
		// recordar persistir lo que haga falta
	}
	
	@Override
	public void notificarFan(String idFan, NotificacionFan notificacion) {
		// obtener el fan por id
		// decirle que registre la notificacion

	}

	
}
