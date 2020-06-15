package wsREST;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.TodoArte.Classes.QyAProgramado;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Path("/usuario")
@Named
@SessionScoped
public class UsuarioWS implements Serializable {
	
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
	
	/**
	 * Función para iniciar sesión
	 * @return
	 */
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response iniciarSesion(String idUsuario, String contrasenia) {
		try {
			Usuario usuario = fo.iniciarSesion(idUsuario, contrasenia);
			return Response
					.status(Response.Status.OK)
					.entity(usuario)
					.build();
		} catch (Exception e) {
			
			String message = "Error al iniciar sesión. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para obtener los datos de un usuario
	 * @return
	 */
	@POST
	@Path("/obtener")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerDatosUsuario(String idUsuario) {
		try {
			Usuario usuario = fo.obtenerDatosUsuario(idUsuario);
			return Response
					.status(Response.Status.OK)
					.entity(usuario)
					.build();
		} catch (Exception e) {
			
			String message = "Error al obtener los datos del usuario. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función que recarga el saldo de un usuario, sea fan o artista
	 * @return
	 */
	@PUT
	@Path("/recargar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response recargarSaldo(String idUsuario, float monto) {
		try {
			fo.recargarSaldo(idUsuario, monto);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al recargar saldo. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
	
	
}
