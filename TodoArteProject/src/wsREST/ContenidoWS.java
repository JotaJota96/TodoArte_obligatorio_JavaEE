package wsREST;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Comentario;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Valoracion;
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
	
	
	/**
	 * Función para obtener un contenido especifico
	 * @return
	 */
	@GET
	@Path("/obtener/uno/{idcontenido}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenido(@PathParam("idcontenido") Integer idContenido) {
		try {
			Contenido contenido = fo.obtenerUnContenido(idContenido);
			return Response
					.status(Response.Status.OK)
					.entity(contenido)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener un contenido: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para obtener todos los contenidos
	 * @return
	 */
	@GET
	@Path("/obtener/todos/{idartista}/{idfan}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenidos(@PathParam("idartista") String idArtista, @PathParam("idfan") String idFan) {
		try {
			ArrayList<Contenido> listaContenidos = fo.obtenerContenido(idArtista, idFan);
			return Response
					.status(Response.Status.OK)
					.entity(listaContenidos.toArray())
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
	 * Función para crear o actualizar un contenido
	 * @return
	 */
	@PUT
	@Path("/agregar/{idartista}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarModificarContenido(@PathParam("idartista") String idArtista,Contenido Contenido) {
		try {
			Contenido contenido = fo.agregarModificarContenido(idArtista, Contenido);
			return Response
					.status(Response.Status.CREATED)
					.entity(contenido) //Retorna el contenido creado
					.build();
		} catch (Exception e) {
			
			String message = "Error al crear un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para eliminar un contenido especifico de un artista especifico
	 * @return
	 */
	@DELETE
	@Path("/eliminar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarContenido(String idArtista,Integer idContenido) {
		try {
			fo.eliminarContenido(idArtista, idContenido);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al eliminar un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
	/**
	 * Función para calificar un contenido especifico
	 * @return
	 */
	@POST
	@Path("/calificar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response calificarContenido(Valoracion val, String idFan,Integer idContenido, String idArtista) {
		try {
			fo.calificarContenido(val, idFan, idContenido, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al calificar un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para comentar un contenido especifico
	 * @return
	 */
	@POST
	@Path("/comentar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response comentarContenido(Comentario comentario, String idFan, int idContenido, String idArtista) {
		try {
			fo.comentarContenido(comentario, idFan, idContenido, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al comentar un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para reportar un contenido especifico
	 * @return
	 */
	@POST
	@Path("/reportar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportarContenido(Reporte reporte, String idFan, int idContenido, String idArtista) {
		try {
			fo.reportarContenido(reporte, idFan, idContenido, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al comentar un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	/**
	 * Función para comprar un contenido especifico
	 * @return
	 */
	@POST
	@Path("/comprar")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarContenido(String idFan, int idContenido, int idArtista) {
		try {
			fo.comprarContenido(idFan, idContenido, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al comentar un contenido. Error: " + e.getMessage();
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
	@Path("/comprar-premium")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarPremium(String idFan, String idArtista) {
		try {
			fo.comprarPremium(idFan, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			
			String message = "Error al comprar premium. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	
	
}
