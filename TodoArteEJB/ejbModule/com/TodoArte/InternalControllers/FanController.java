package com.TodoArte.InternalControllers;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.FanInterface;
import com.TodoArte.JPAControllerClasses.FanJpaController;

public class FanController implements FanInterface{

	public FanController() {}

	@Override
	public Fan registrarUsuarioFan(Fan fan) {
		FanJpaController fjpa = new FanJpaController();
		if (fjpa.findFan(fan.getNikname()) != null){
			throw new RuntimeException(MensajesExcepciones.fanExiste);
		}
		// Aca falta verificar el correo
		
		fjpa.create(fan);
		
		return fan;
	}
	
	
}
