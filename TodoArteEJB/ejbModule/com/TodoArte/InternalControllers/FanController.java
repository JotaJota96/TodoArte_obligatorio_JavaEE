package com.TodoArte.InternalControllers;

import java.util.ArrayList;
import java.util.Map;

import org.jboss.resteasy.spi.ReaderException;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.ArtistaInterface;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
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
		Fan f = (Fan) this.obtenerDatosUsuario(idFan);
		if (f == null) {
			throw new RuntimeException(MensajesExcepciones.fanNoExiste);
		}
		ArrayList<NotificacionFan> ret = new ArrayList<NotificacionFan>();
		for (Map.Entry<Integer, NotificacionFan> entry : f.getNotificaciones().entrySet()) {
			ret.add(entry.getValue());
		}
		return ret;
	}

	@Override
	public Usuario iniciarSesion(String idUsuario, String contrasenia) {
		// verifica los datos de inicio de sesion del fan
		// el id puede ser nickname o email
		
		FanJpaController fjpa = new FanJpaController();
		Fan fan = fjpa.findFan(idUsuario);
		if(fan != null){
			if(fan.getContrasenia().equals(contrasenia)){
				return fan;
			}
		}
		return null;
	}

	@Override
	public Fan obtenerDatosUsuario(String idUsuario) {
		// obtener el fan por su ID y devolverlo (nill si no se encuentra)
		return new FanJpaController().findFan(idUsuario);
	}

	@Override
	public void recargarSaldo(String idUsuario, float monto) {
		// obtener el fan por id e incrementar su saldo
		

		Fan fan = new FanJpaController().findFan(idUsuario);
		fan.setSaldo(fan.getSaldo() + monto);
		try {
			new FanJpaController().edit(fan);
		} catch (Exception e) {
			throw new ReaderException(e.getMessage());
		}
		
	}

	@Override
	public void descontarSaldo(String idUsuario, float monto) {
		// obtener el fan por id
		// descuenta el monto del saldo del artista
		
		Fan fan = new FanJpaController().findFan(idUsuario);
		
		if(fan.getSaldo() < monto){
			throw new ReaderException(MensajesExcepciones.saldoInsuficiente);
		}
		
		fan.setSaldo(fan.getSaldo() - monto);
		
		try {
			new FanJpaController().edit(fan);
		} catch (Exception e) {
			throw new ReaderException(e.getMessage());
		}
	}

	@Override
	public void vincularFanASitio(FanSigueSitio fanSigueSitio, String idFan) {
		// obtiene el fan
		// vincular mutuamente al fan con el fanSigueSitio
		// recordar persistir lo que haga falta
		
		Fan fan = new FanJpaController().findFan(idFan);
		fan.getMisSitiosSeguidos().put(fanSigueSitio.getId(), fanSigueSitio);
		
		try {
			new FanJpaController().edit(fan);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public void notificarFan(String idFan, NotificacionFan notificacion) {
		// obtener el fan por id
		// decirle que registre la notificacion
		FanJpaController fJpa = new FanJpaController();
		Fan f = fJpa.findFan(idFan);
		if (f == null) {
			throw new RuntimeException(MensajesExcepciones.fanNoExiste);
		}
		f.agregarNotificacion(notificacion);
		try {
			fJpa.edit(f);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	
}
