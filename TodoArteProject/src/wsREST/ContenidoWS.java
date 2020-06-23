package wsREST;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.NotificacionArtista;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

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
			if (contenido == null) {
				return Response
						.status(Response.Status.NOT_FOUND)
						.build();
			}
			
			Funciones.limpiarVisibilidades(contenido);
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
	@Path("/obtener/todos/{idartista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenidos(@PathParam("idartista") String idArtista) {
		try {
			return obtenerContenidos(idArtista, "");
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener los contenidos: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	@GET
	@Path("/obtener/todos/{idartista}/{idfan}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenidos(@PathParam("idartista") String idArtista, @PathParam("idfan") String idFan) {
		try {
			ArrayList<Contenido> listaContenidos = fo.obtenerContenido(idArtista, idFan);
			Funciones.limpiarVisibilidadesLista(listaContenidos);
			return Response
					.status(Response.Status.OK)
					.entity(listaContenidos.toArray())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener los contenidos: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
	

	/**
	 * Función para obtener todos los contenidos premium de un artista
	 * @return
	 */
	@GET
	@Path("/obtener/premium/{idartista}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenidosPremium(@PathParam("idartista") String idArtista) {
		try {
			return obtenerContenidosPremium(idArtista, "");
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener los contenidos: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	@GET
	@Path("/obtener/premium/{idartista}/{idfan}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerContenidosPremium(@PathParam("idartista") String idArtista, @PathParam("idfan") String idFan) {
		try {
			ArrayList<Contenido> listaContenidos = fo.obtenerContenidoPremium(idArtista, idFan);
			Funciones.limpiarVisibilidadesLista(listaContenidos);
			return Response
					.status(Response.Status.OK)
					.entity(listaContenidos.toArray())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error al obtener los contenidos: " + e.getMessage();
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
	@Path("/agregar-modificar/{idartista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarModificarContenido(@PathParam("idartista") String idArtista, Contenido Contenido) {
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
	@Path("/eliminar/{idartista}/{idContenido}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarContenido(@PathParam("idartista") String idArtista, @PathParam("idContenido") Integer idContenido) {
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
	@Path("/calificar/{idFan}/{idContenido}/{idArtista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response calificarContenido(
			Valoracion val, 
			@PathParam("idFan") String idFan,
			@PathParam("idContenido") Integer idContenido, 
			@PathParam("idArtista") String idArtista) {
		try {
			Fan f = (Fan) fo.obtenerDatosUsuario(idFan);
			if (f == null) {
				throw new RuntimeException("No existe un fan con ese ID");
			}
			val = new Valoracion(0, val.getVal(), f);
			
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
	@Path("/comentar/{idFan}/{idContenido}/{idArtista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comentarContenido(
			Comentario comentario,
			@PathParam("idFan") String idFan,
			@PathParam("idContenido") Integer idContenido, 
			@PathParam("idArtista") String idArtista) {
		try {
			Fan f = (Fan) fo.obtenerDatosUsuario(idFan);
			if (f == null) {
				throw new RuntimeException("No existe un fan con ese ID");
			}
			comentario = new Comentario(0, comentario.getTexto(), FuncionesComunes.fechaActual(), f);
			
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
	@Path("/reportar/{idFan}/{idContenido}/{idArtista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportarContenido(
			Reporte reporte,
			@PathParam("idFan") String idFan,
			@PathParam("idContenido") Integer idContenido, 
			@PathParam("idArtista") String idArtista) {
		try {
			Fan f = (Fan) fo.obtenerDatosUsuario(idFan);
			if (f == null) {
				throw new RuntimeException("No existe un fan con ese ID");
			}
			reporte = new Reporte(0, reporte.getReporte(), f);
			
			fo.reportarContenido(reporte, idFan, idContenido, idArtista);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			String message = "Error al reportar un contenido. Error: " + e.getMessage();
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
	@Path("/comprar/{idFan}/{idContenido}/{idArtista}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comprarContenido(
			@PathParam("idFan") String idFan,
			@PathParam("idContenido") Integer idContenido, 
			@PathParam("idArtista") String idArtista) {
		try {
			fo.comprarContenido(idFan, idContenido, 0);
			return Response
					.status(Response.Status.OK)
					.build();
		} catch (Exception e) {
			String message = "Error al comprar un contenido. Error: " + e.getMessage();
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(message)
					.build();
		}
	}
	
}
