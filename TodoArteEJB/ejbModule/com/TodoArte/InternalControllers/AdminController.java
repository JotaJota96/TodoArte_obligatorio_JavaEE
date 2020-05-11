package com.TodoArte.InternalControllers;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.InternalInterfaces.AdminInterface;

public class AdminController implements AdminInterface{

	public AdminController() {}

	@Override
	public void agregarCategoriaDeSitio(CategoriaSitio categoriaSitio) {
		// persiste la categoria en la base de datos
	}

	@Override
	public void agregarCategoriaDeContenido(CategoriaContenido categoriaContenido) {
		// persiste la categoria en la base de datos
	}

	@Override
	public Administrador iniciarSesion(String id, String contrasenia) {
		// obtener administrador desde la BDD y verificar que coincida la contraseña
		return null;
	}

	@Override
	public Administrador agregarAdministrador(Administrador administrador) {
		// persistir el administrador en la BDD
		return null;
	}
	
}
