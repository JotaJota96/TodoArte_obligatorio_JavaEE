package com.TodoArte.InternalInterfaces;

import com.TodoArte.Classes.Administrador;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.Classes.Fuente;

public interface AdminInterface {
	
	/**
	 * Agrega al sistema una nueva categoria de sitios
	 * @param categoriaSitio Categoria a agregar
	 * @return categoria creada
	 */
	public CategoriaSitio agregarCategoriaDeSitio(CategoriaSitio categoriaSitio);

	/**
	 * Agrega al sistema una nueva categoria de contenido
	 * @param categoriaContenido Categoria a agregar
	 * @return categoria creada
	 */
	public CategoriaContenido agregarCategoriaDeContenido(CategoriaContenido categoriaContenido);

	/**
	 * Agrega al sistema una nueva fuente
	 * @param fuente Fuente a agregar
	 * @return
	 */
	public Fuente agregarFuente(Fuente fuente);
	
	/**
	 * Valida los datos de inicio de sesion de un administrador
	 * @param nickname ID (nickname o email) del administrador
	 * @param contrasenia Contrasenia del administrador
	 * @return si los datos son correctos retorna el administrador que ha iniciado sesion, de lo contrario NULL
	 */
	public Administrador iniciarSesion(String id, String contrasenia);
	
	/**
	 * Agrega un nuevo administrador al sistema
	 * @param administrador Nuevo administrador
	 * @return retorna el administrador creado
	 */
	public Administrador agregarAdministrador(Administrador administrador);
	
}
