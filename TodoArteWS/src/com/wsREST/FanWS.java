package com.wsREST;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.TodoArte.Classes.Fan;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Path("/fan")
public class FanWS {
	@EJB
	FrontOfficeInterface fo;
	
	/**
	 * Esta funcion es para hacer pruebas...
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces("text/plain")
	public String test() {
		return "Has llamado correctamente a la funcion test";
	}
}
