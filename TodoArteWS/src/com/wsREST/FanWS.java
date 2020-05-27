 
package com.wsREST;

import java.io.Serializable;
import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
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
		Date fecha = new Date(2019,06,05);
		Fan nuevoFan2 = new Fan("pedro33", "contrasenia", "correo", 0, null,"nombre", "apellido", fecha, "ubicacion", Sexo.Femenino);
		
		return Response
				.status(Response.Status.CREATED)
				.entity(nuevoFan2)
				.build();
	}
	
	
	@POST
	@Path("/categoria")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFan(CategoriaContenido cc) {		
		 System.out.println(cc.getNombreCategoria());
	}
	
	/**
	 * Función para registrar un usuario via rest
	 * @return
	 */
	@POST
	@Path("/addfan")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFan(Fan fan) {
		try {
			System.out.println("--------------" + fo);
			fan = fo.registrarUsuarioFan(fan);
			return Response
					.status(Response.Status.CREATED)
					.entity(fan)
					.build();
		} catch (Exception e) {
			// TODO: handle exception
			String message = "Puta madre, hay un error en el JSON que enviaste. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
}
