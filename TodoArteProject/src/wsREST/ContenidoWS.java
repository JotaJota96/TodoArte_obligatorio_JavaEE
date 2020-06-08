package wsREST;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Path("/contenido")
@Named
@SessionScoped
public class ContenidoWS implements Serializable {
	
	FrontOfficeInterface fo = new FrontOfficeController();
	

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
