package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;
import com.TodoArte.JPAControllerClasses.CategoriaSitioJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;
import com.TodoArte.JPAControllerClasses.FuenteJpaController;

public class Pruebas {

	public static void main(String[] args) {
		FrontOfficeInterface fo = new FrontOfficeController();
		
		Fuente fuente = new Fuente(0, "fuente 1");
		new FuenteJpaController().create(fuente);
		System.out.println("-- 1 --");
		CategoriaSitio cat = new CategoriaSitio(0, "musica");
		new CategoriaSitioJpaController().create(cat);
		System.out.println("-- 2 --");
		
		Artista a = new Artista("ergo", "1234", "ergo@gmail.com", 0, null, "Ergo", "biog", null);
		Sitio s = new Sitio(0, null, 150, "", "", "", "", "", "", "", 2, cat, a, fuente);
		System.out.println("-- 3 --");
		
		fo.registrarUsuarioArtista(a, s);
		
		System.out.println("-- fin --");
	}

}
