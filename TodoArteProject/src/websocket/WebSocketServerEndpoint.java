package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/qya")
public class WebSocketServerEndpoint {
	/**
	 * coleccion para almacenar temporalmente nuevas sesiones. Estas sesiones no
	 * tienen una sala de chat asignada
	 */
	static Set<Session> sesionesNuevas = Collections.synchronizedSet(new HashSet<Session>());
	
	/**
	 * coleccion de colecciones de sesiones. Cada entrada es una sala de chat
	 * identificada por su ID y se asocia a la coleccion de sesiones de los usuarios
	 * que participan en ella
	 */
	static Map<Integer, Set<Session>> salas = new TreeMap<Integer, Set<Session>>();
	
	static String WebSocketURL = "ws://localhost:8080/TodoArteProject/qya";
	
	// constructor por defecto
	public WebSocketServerEndpoint() {}
	
	//--------------------------------------------------------------------
	/**
	 * Crea una sala de chat con el ID especificado
	 * @param id ID de la sala de chat a crear
	 */
	public static void crearSala(int id) {
		if (existeSala(id)) {
			throw new RuntimeException("Ya existe una sala con ese id");
		}
		salas.put(id, Collections.synchronizedSet(new HashSet<Session>()));
	}
	
	/**
	 * Verifica si existe una sala de chat con el ID especificado
	 * @param idSala ID de la sala que se desea saber si existe
	 * @return true si la sala existe, de lo contrario false
	 */
	public static boolean existeSala(int idSala) {
		return salas.containsKey(idSala);
	}

	public static String getWebSocketURL() {
		return WebSocketURL;
	}
	public static void setWebSocketURL(String webSocketURL) {
		WebSocketURL = webSocketURL;
	}

	//---------------------------------------------------------------
	/**
	 * Esta funcion se ejecuta cuando un cliente WebSocket establece conexion por
	 * primera vez
	 * @param userSession Sesion del cliente conectado
	 */
	@OnOpen
	public void handleOpen(Session userSession) {
		// agrega la nueva sesion a la coleccin de sesiones sin sala de chat asignada
		sesionesNuevas.add(userSession);
	}
	
	/**
	 * Esta funcion se ejecuta cada vez que un cliente WebSocket envia un mensaje
	 * @param message     Mensaje enviado por el cliente, en este caso, un JSON
	 * @param userSession Sesion del usuario que ha enviado el mensaje
	 * @throws IOException Tipo de excepcione que se puede generar
	 */
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// valido lo recibido, si algo esta mal, lo ignoro
		// si el mensaje no se puede convertir
		MensajeChat mc = null;
		try {
			mc = MensajeChat.decodificar(message);
		} catch (Exception e) {
			return;
		}
		if (mc == null) {
			return;
		}
		// si la sala no existe
		if ( ! existeSala(mc.getIdSala())) {
			return;
		}
		// fin de validaciones
		
		// si la sesion aun no tiene un nickname asociado, es que es el primer mensaje que envia
		boolean primerMensaje = userSession.getUserProperties().get("nickname") == null;
		if (primerMensaje) {
			// si es el primer mensaje...
			
			// quito la sesion de las sesiones sin sala asignada
			sesionesNuevas.remove(userSession);
			
			// guardo en la sesion del usuario el nickname del usuario y el id de la sala
			userSession.getUserProperties().put("nickname", mc.getNickname());
			userSession.getUserProperties().put("idSala", mc.getIdSala());
			
			// Envia un mensaje a todos los usuarios para avisar que alguien se ha unido
			MensajeChat mcNuevaConexion = new MensajeChat(mc.getIdSala(), "TodoArte", mc.getNickname() + " se ha unido al chat");
			enviarMensajeATodos(mc.getIdSala(), mcNuevaConexion);
			
			// agrego la sesion a la sala que le corresponde
			salas.get(mc.getIdSala()).add(userSession);
			
			// Mando un mensaje de bienvenida al nueco usuario
			MensajeChat bienvenida = new MensajeChat(mc.getIdSala(), "TodoArte", "Bienvenido al chat " + mc.getNickname());
			userSession.getBasicRemote().sendText(MensajeChat.codificar(bienvenida));
		} else {
			// si NO es el primer mensaje...

			// extraigo los datos de la sesion del usuario y no del MensajeChat porque me parece que es mas seguro
			int idSala = (int) userSession.getUserProperties().get("idSala");
			// por las dudas sobreescribo el nickname que viene en el mensaje por el que esta guardado en la sesion  (porque tambien me parece que es mas seguro)
			mc.setNickname((String) userSession.getUserProperties().get("nickname"));
			// envio el mensaje a todos los usuarios de la sala indicada
			enviarMensajeATodos(idSala, mc);
		}
	}

	/**
	 * Esta funcion se ejecuta cuando un cliente WebSocket finaliza su conexion
	 * @param userSession Sesion del cliente que se desconecta
	 */
	@OnClose
	public void handleClose(Session userSession) {
		// como el usuario cerro su conexion por websocket, hay que quitar su sesion de la coleccion donde se encuentra
		// si la sesion NO tiene seteada la variable 'idSala', es porque no tiene una sala de chat asignada
		if (userSession.getUserProperties().get("idSala") == null) {
			// entonces la quito de la coleccion de sesiones sin sala asignada
			sesionesNuevas.remove(userSession);
		}else {
			// si SI tiene una sala asignada, obtengo su ID
			int idSala = (int) userSession.getUserProperties().get("idSala");
			String nickname = (String) userSession.getUserProperties().get("nickname");
			// por las dudas verifico que la sala existe
			if (salas.containsKey(idSala)) {
				// quito la sesion de la coleccion de sesiones de esa sala especifica
				salas.get(idSala).remove(userSession);

				// Envia un mensaje a todos los usuarios para avisar que alguien se ha unido
				MensajeChat mcParaTodos = new MensajeChat(idSala, "TodoArte", nickname + " se ha ido");
				try {
					enviarMensajeATodos(mcParaTodos.getIdSala(), mcParaTodos);
				} catch (IOException e) {
				}
			}else {
				// y si entra aca, pues ni idea que pasa...
			}
		}
	}

	/**
	 * Esta funcion envia un determinado mensaje a todos los clientes WebSocket
	 * vinculados a una sala de chat especifica
	 * @param idSala ID de la sala en la cual se enviará el mensaje
	 * @param mc Mensaje de chat a enviar a todos los usuarios de la sala
	 * @throws IOException Tipo de excepcione que se puede generar
	 */
	private void enviarMensajeATodos(int idSala, MensajeChat mc) throws IOException {
		// por las dudas verifico que exista una sala con ese ID
		if (salas.containsKey(idSala)) {
			// recorro las sesiones de la sala y a cada una le mando el mensaje
			for (Session ses : salas.get(idSala)) {
				ses.getBasicRemote().sendText(MensajeChat.codificar(mc));
			}
		}
	}
}
