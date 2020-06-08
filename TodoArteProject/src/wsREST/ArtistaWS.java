package wsREST;

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

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.NotificacionFan;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Path("/artista")
@Named
@SessionScoped
public class ArtistaWS implements Serializable {
	
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
	 * Función para registrar un Artista via rest
	 * @return
	 */
	@POST
	@Path("/registrar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarUsuarioArtista(Artista artista, Sitio sitio) {
		try {
			artista = fo.registrarUsuarioArtista(artista, sitio);
			return Response
					.status(Response.Status.CREATED)
					.entity(artista)
					.build();
		} catch (Exception e) {
			
			String message = "Error en el JSON que enviaste. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para obtener las notificaciones de un Artista via rest
	 * @return
	 */
	@GET
	@Path("/notificaciones/{idartista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNotificacionesArtista(@PathParam("idartista") String idArtista) {
		try {
			ArrayList<NotificacionArtista> listaNotificaciones = fo.listarNotificacionesArtista(idArtista);
			return Response
					.status(Response.Status.OK)
					.entity(listaNotificaciones.toArray())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al listar las notificaciones del artista: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para subscribir un Fan a un Artista via rest
	 * @return
	 */
	@POST
	@Path("/suscribirse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response suscribirseFanArtista(String idFan, String idArtista) {
		try {
			fo.suscribirseFanArtista(idFan, idArtista);
			return Response
					.status(Response.Status.OK)
					.entity("")
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al subscribir el fan " + idFan + " al artista " + idArtista + ". Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
}
