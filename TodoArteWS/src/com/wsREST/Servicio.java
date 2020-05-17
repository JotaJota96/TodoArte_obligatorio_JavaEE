package com.wsREST;

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
GET /obtener/uno
Contenido obtenerContenido(String, int, String);
GET /obtener/todos
ArrayList<Contenido> obtenerContenido(String, String);
POST /agregar
Contenido agregarModificarContenido(String, Contenido);
PUT /modificar
Contenido agregarModificarContenido(String, Contenido);
DELETE /eliminar
void eliminarContenido(String, int);
POST /calificar
void calificarContenido(Valoracion, String, int, String);
POST /comentar
void comentarContenido(Comentario, String, int, String);
POST /reportar
void reportarContenido(Reporte, String, int, String);
POST /comprar
void comprarContenido(String, int, int);

// Clase UsuarioWS
/usuario
POST /login
Usuario iniciarSesion(String, String);
GET /obtener
Usuario obtenerDatosUsuario(String);
PUT /recargar
void recargarSaldo(String, float);

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
Artista registrarUsuarioArtista(Artista, Sitio);
GET /notificaciones
ArrayList<NotificacionArtista> listarNotificacionesArtista(String);
POST /suscribirse
void suscribirseFanArtista(String, String);
GET /fans
ArrayList<Fan> obtenerFansDeSitio(String);
POST /programar
QyAProgramado programarQyA(String, QyAProgramado);
POST /comprar/premium
void comprarPremium(String, String);

 */







