package multimediaResources;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

/**
 * Esta clase fue hecha para poder obtener los diferentes tipos de archivos que se guardan en la base de datos
 * Rutas disponibles:
 * '/recurso/multimedia/test' devuelve un json con un mensaje de prueba
 * '/recurso/multimedia/usuario/imagen/{idUsuario}' devuelve la imagen de perfil del usuario (Fan o Artista)
 * '/recurso/multimedia/sitio/imagen/{idArtista}' devuelve la imagen de portada del sitio del artista
 * '/recurso/multimedia/contenido/{idArtista}/{idContenido}' devuelve el archivo del contenido (sea del tipo que sea)
 * @author juan
 *
 */
@Path("/multimedia")
@Named
@SessionScoped
public class Manager implements Serializable {
	@EJB
	FrontOfficeInterface fo;
	
	public Manager() {
	}

	//---------------------------------------------------------------------------------------------------
	/**
	 * '/recurso/multimedia/test' devuelve un json con un mensaje de prueba
	 * @return
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		// {"mensaje" : "Has llamado ala funcion test"}
		try {
			String s = "{\"mensaje\" : \"Has llamado ala funcion /recurso/multimedia/test\"}";
			return Response
					.status(Response.Status.CREATED)
					.entity(s)
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(message)
					.build();
		}
	}
	
	//---------------------------------------------------------------------------------------------------
	/**
	 * '/recurso/multimedia/usuario/imagen/{idUsuario}' devuelve la imagen de perfil del usuario (Fan o Artista)
	 * @param idUsuario ID del usuario del que se quiere obtener la imagen de perfil
	 * @return
	 */
	@GET
	@Path("/usuario/imagen/{idUsuario}")
	@Produces("image/png")
	public Response obtenerImagenUsuario(@PathParam("idUsuario") String idUsuario) {
		try {
			Usuario u = fo.obtenerDatosUsuario(idUsuario);
			return Response
					.status(Response.Status.OK)
					.entity(u.getImagen())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(message)
					.build();
		}
	}
	

	//---------------------------------------------------------------------------------------------------
	/**
     * '/recurso/multimedia/sitio/imagen/{idArtista}' devuelve la imagen de portada del sitio del artista
	 * @param idArtista ID del usuario del que se quiere obtener la imagen de perfil
	 * @return
	 */
	@GET
	@Path("/sitio/imagen/{idArtista}")
	@Produces("image/png")
	public Response obtenerImagenPortadaSitio(@PathParam("idArtista") String idArtista) {
		try {
			Usuario u = fo.obtenerDatosUsuario(idArtista);
			if ( ! (u instanceof Artista)) {
				throw new RuntimeException("El ID " + idArtista + " no es de un artista");
			}
			Sitio s = ((Artista) u).getMiSitio();
			return Response
					.status(Response.Status.OK)
					.entity(s.getImagenPortada())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(message)
					.build();
		}
	}
	
	

	//---------------------------------------------------------------------------------------------------
	/**
	 * '/recurso/multimedia/contenido/{idArtista}/{idContenido}' devuelve el archivo del contenido (sea del tipo que sea)
	 * @param idArtista ID del usuario del que se quiere obtener la imagen de perfil
	 * @return
	 */
	@GET
	@Path("/contenido/{idArtista}/{idContenido}")
	public Response obtenerContenido(@PathParam("idArtista") String idArtista, 
									@PathParam("idContenido") int idContenido) {
		try {
			Contenido c = fo.obtenerContenido(idArtista, idContenido, idArtista);
			if ( c == null) {
				throw new RuntimeException("No se pudo obtener el contenido con ID " + idContenido + " del artista " + idArtista);
			}
			
			String mediaType  = "";
			switch (c.getTipo()) {
			case Audio:
				mediaType  = "audio/mpeg";
				break;
			case Imagen:
				mediaType  = "image/png";
				break;
			case Video:
				mediaType  = "video/mp4";
				break;
			case PDF:
				mediaType  = "application/pdf";
				break;
			case Otros:
				mediaType  = "application/octet-stream";
				break;
			default:
				mediaType  = "application/octet-stream";
				break;
			}
			return Response
					.status(Response.Status.OK)
					.type(mediaType)
					.entity(c.getArchivo())
					.build();
		} catch (Exception e) {
			String message = "Ha ocurrido un error: " + e.getMessage();
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(message)
					.build();
		}
	}
	
	
}











