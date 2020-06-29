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
import com.TodoArte.Classes.QyAProgramado;
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
	 * Test OK
	 */
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarUsuarioArtista(Artista artista) {
		try {
//			Artista artista2 = new Artista();
//			Sitio sitio = new Sitio();
//			artista2.setMiSitio(sitio);			
			artista.getMiSitio().setMiArtista(artista);
			//Sitio sitio = artista.getMiSitio();	
			//Funciones.limpiarVisibilidades(sitio);
			Artista art = fo.registrarUsuarioArtista(artista, artista.getMiSitio());
			Funciones.limpiarVisibilidades(art);
			return Response
					.status(Response.Status.CREATED)
					.entity(art)
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
	 * Test OK
	 */
	@GET
	@Path("/notificaciones/{idartista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarNotificacionesArtista(@PathParam("idartista") String idArtista) {
		try {
			ArrayList<NotificacionArtista> listaNotificaciones = fo.listarNotificacionesArtista(idArtista);
			Funciones.limpiarVisibilidadesLista(listaNotificaciones);
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
	 * Test OK
	 */
	@POST
	@Path("/suscribirse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response suscribirseFanArtista(DtSuscribirse dtSuscribirse) {
		try {
			
			fo.suscribirseFanArtista(dtSuscribirse.getIdFan(), dtSuscribirse.getIdArtista());
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al subscribir el fan " + dtSuscribirse.getIdFan() + " al artista " + dtSuscribirse.getIdArtista()+ ". Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para obtener todos los fans que siguien al sitio del artista.
	 * @return
	 * Test OK
	 */
	@GET
	@Path("/fans/{idartista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerFansDeSitio(@PathParam("idartista") String idArtista) {
		try {
			ArrayList<Fan> listaFans = fo.obtenerFansDeSitio(idArtista);
			Funciones.limpiarVisibilidadesLista(listaFans);
			return Response
					.status(Response.Status.OK)
					.entity(listaFans)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener los fans. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función que progrma un QyA para un artista
	 * @return
	 * Sin terminar porque no se va a implementar
	 */
	@POST
	@Path("/programar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response programarQyA(DtProgramarQA dtProgramarQA) {
		try {
			throw new RuntimeException("Funcion aun no implementada");
			/*
			QyAProgramado qyAProgramado =  fo.programarQyA(dtProgramarQA.getIdArtista(), dtProgramarQA.getqAProgramado());
			Funciones.limpiarVisibilidades(qyAProgramado);
			return Response
					.status(Response.Status.CREATED)
					.entity(qyAProgramado)
					.build();
			*/
		} catch (Exception e) {
			
			String message = "Error al crear un qyaProgramado. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para comprar premium
	 * @return
	 */
	@POST
	@Path("/comprar-suscripcion-premium/{idFan}/{idArtista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarPremium(
			@PathParam("idFan") String idFan,
			@PathParam("idArtista") String idArtista) {
		try {
			fo.comprarPremium(idFan, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			String message = "Error al comprar suscripcion premium. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
}
