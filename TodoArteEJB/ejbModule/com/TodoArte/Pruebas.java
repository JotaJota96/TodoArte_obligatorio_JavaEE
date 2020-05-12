package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;
import com.TodoArte.InternalControllers.ArtistaController;
import com.TodoArte.InternalControllers.FanController;
import com.TodoArte.JPAControllerClasses.ArtistaJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaSitioJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;
import com.TodoArte.JPAControllerClasses.FuenteJpaController;

public class Pruebas {

	public static void main(String[] args) {
		FrontOfficeInterface fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		System.out.println("-- fin --");
	}

}
