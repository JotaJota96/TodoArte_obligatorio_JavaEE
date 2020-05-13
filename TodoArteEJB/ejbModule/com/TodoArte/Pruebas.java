package com.TodoArte;

import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;

public class Pruebas {

	public static void main(String[] args) {
		FrontOfficeController fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		System.out.println("-- fin --");
	}

}
