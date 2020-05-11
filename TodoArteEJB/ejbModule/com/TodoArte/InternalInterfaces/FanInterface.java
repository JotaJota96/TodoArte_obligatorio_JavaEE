package com.TodoArte.InternalInterfaces;

import com.TodoArte.Classes.Fan;

public interface FanInterface {
	
	/**
	 * Registra un nuevo fan en el sistema
	 * @param Fan Fan a registrar
	 * @return devuelve el fan creado
	 */
	public Fan registrarUsuarioFan(Fan fan);
}
