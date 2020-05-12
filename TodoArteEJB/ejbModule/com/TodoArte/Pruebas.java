package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

public class Pruebas {

	public static void main(String[] args) {
		FrontOfficeInterface fo = new FrontOfficeController();

		System.out.println(fo.iniciarSesion("prueba", "2"));
		System.out.println("exito");
	}

}
