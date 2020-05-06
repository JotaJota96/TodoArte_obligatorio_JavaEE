package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.JPAControllerClasses.FanJpaController;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fan f = new Fan("fan_1", "fan_1", "fan_1", 0, null, false, "fan_1", "fan_1", new Date(1998, 11, 15), "fan_1", Sexo.Masculino);
		
		new FanJpaController().create(f);
		
		System.out.println("Fin");
	}

}
