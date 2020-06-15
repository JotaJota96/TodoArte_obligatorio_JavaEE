package wsREST;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class Servicio  extends Application{

    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(ContenidoWS.class);
        s.add(UsuarioWS.class);
        s.add(FanWS.class);
        s.add(ArtistaWS.class);
        return s;
    }
}


/*
	// Clase ContenidoWS
	/contenido
	 * 
	GET /obtener/uno
	Contenido obtenerContenido(String, int, String); 	hecho

	GET /obtener/todos
	ArrayList<Contenido> obtenerContenido(String, String); hecho
	
	POST /agregar
	Contenido agregarModificarContenido(String, Contenido); hecho
	
	PUT /modificar
	Contenido agregarModificarContenido(String, Contenido); hecho

	DELETE /eliminar
	void eliminarContenido(String, int); 					hecho
	
	POST /calificar
	void calificarContenido(Valoracion, String, int, String);	hecho
	
	POST /comentar
	void comentarContenido(Comentario, String, int, String);	hecho
	
	POST /reportar
	void reportarContenido(Reporte, String, int, String); 		hecho
	
	POST /comprar
	void comprarContenido(String, int, int);				hecho

	// Clase UsuarioWS  http://localhost:8080/TodoArteProject/api/usuario/login
	/usuario
	POST /login
	Usuario iniciarSesion(String, String); hecho
	
	GET /obtener
	Usuario obtenerDatosUsuario(String);	hecho   http://localhost:8080/TodoArteProject/api/usuario/obtener
	
	PUT /recargar
	void recargarSaldo(String, float);		hecho

// Clase FanWS
	/fan
	POST /registrar
	Fan registrarUsuarioFan(Fan);
	GET /notificaciones
	ArrayList<NotificacionFan> listarNotificacionesFan(String);
	POST /bloquear
	void bloquearDesbloquearUsuarioDeSitio(String, String);

	// Clase ArtistaWS
	/artista
	POST /registrar										
	Artista registrarUsuarioArtista(Artista, Sitio);     hecho
	
	GET /notificaciones
	ArrayList<NotificacionArtista> listarNotificacionesArtista(String);    hecho
	
	POST /suscribirse
	void suscribirseFanArtista(String, String);				hecho
	
	GET /fans
	ArrayList<Fan> obtenerFansDeSitio(String);				hecho
	
	POST /programar
	QyAProgramado programarQyA(String, QyAProgramado);		Hecho en la clase Artista

	POST /comprar-premium  				                  hecho en la clase contenido
	void comprarPremium(String, String);

 */







