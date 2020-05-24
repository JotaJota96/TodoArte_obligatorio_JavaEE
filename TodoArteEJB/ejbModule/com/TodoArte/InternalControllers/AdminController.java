package com.TodoArte.InternalControllers;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.Fuente;
import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.InternalInterfaces.AdminInterface;
import com.TodoArte.JPAControllerClasses.AdministradorJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaContenidoJpaController;
import com.TodoArte.JPAControllerClasses.CategoriaSitioJpaController;
import com.TodoArte.JPAControllerClasses.FanJpaController;
import com.TodoArte.JPAControllerClasses.FuenteJpaController;

public class AdminController implements AdminInterface{

	public AdminController() {}

	@Override
	public CategoriaSitio agregarCategoriaDeSitio(CategoriaSitio categoriaSitio) {
		// persiste la categoria en la base de datos
		 new CategoriaSitioJpaController().create(categoriaSitio);
		 return categoriaSitio;
	}

	@Override
	public CategoriaContenido agregarCategoriaDeContenido(CategoriaContenido categoriaContenido) {
		// persiste la categoria en la base de datos
		 new CategoriaContenidoJpaController().create(categoriaContenido);
		 return categoriaContenido;
	}
	
	@Override
	public Fuente agregarFuente(Fuente fuente) {
		 new FuenteJpaController().create(fuente);
		 return fuente;
	}

	@Override
	public Administrador iniciarSesion(String id, String contrasenia) {
		// obtener administrador desde la BDD y verificar que coincida la contraseña
		
		AdministradorJpaController Ajpa = new AdministradorJpaController();
		Administrador adm = Ajpa.findAdministrador(id);
		if(adm != null){
			if(adm.getContrasenia().equals(contrasenia)){
				return adm;
			}
		}		
		return null;
	}

	@Override
	public Administrador agregarAdministrador(Administrador administrador) {
		// persistir el administrador en la BDD
		
		AdministradorJpaController Ajpa = new AdministradorJpaController();
		
		if (Ajpa.findAdministrador(administrador.getNickname()) != null){
			throw new RuntimeException(MensajesExcepciones.admExiste);
		}
		
		Ajpa.create(administrador);
		
		return administrador;
	}
}
