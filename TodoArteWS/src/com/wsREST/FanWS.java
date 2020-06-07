 
package com.wsREST;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Path("/fan")
@Named
@SessionScoped
public class FanWS implements Serializable{
	@EJB
	FrontOfficeInterface fo;
	
	public FanWS() {
		// TODO Apéndice de constructor generado automáticamente
	}
	
	/**
	 * Esta funcion es para hacer pruebas...
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		try {
			String s = "Has llamado ala funcion test";
			return Response
					.status(Response.Status.CREATED)
					.entity(s)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	@GET
	@Path("/testarudo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test2() {
		try {
			Contenido c = fo.obtenerContenido("ergo", 1, "JotaJota96");
			Funciones.limpiarVisibilidades(c);
			return Response
					.status(Response.Status.CREATED)
					.entity(c)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
	@POST
	@Path("/categoria")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFan(CategoriaContenido cc) {		
		 System.out.println(cc.getNombreCategoria());
	}
	

	/**
	 * Función para obtener las notificaciones de un usuario fan via rest
	 * @return
	 */
	@GET
	@Path("/notificaciones/{idfan}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNotificacionesFan(@PathParam("idfan") String idFan) {
		try {
			ArrayList<NotificacionFan> listaNotificaciones = fo.listarNotificacionesFan(idFan);
			return Response
					.status(Response.Status.OK)
					.entity(listaNotificaciones.toArray())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
	/**
	 * Función para registrar un usuario via rest
	 * @return
	 */
	@POST
	@Path("/registrar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarUsuarioFan(Fan fan) {
		try {
			fan = fo.registrarUsuarioFan(fan);
			return Response
					.status(Response.Status.CREATED)
					.entity(fan)
					.build();
		} catch (Exception e) {
			// TODO: handle exception
			String message = "Error en el JSON que enviaste. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
}
