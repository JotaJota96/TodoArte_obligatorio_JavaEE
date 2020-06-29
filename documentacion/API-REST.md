
# TodoArte API REST
A continuación se muestran los recursos expuestos y un ejemplo del cuerpo de la peticion (si se requiere)

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

# Usuario
## (POST) /api/usuario/login
```json
{
	"idUsuario": "user",
	"contrasenia": "pass"
}
```

## (GET) /api/usuario/obtener/{idUsuario}
```json
```

## (PUT) /api/usuario/recargar
```json
{
	"idUsuario": "user",
	"monto": 0.0
}
```


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

# Artista
## (POST) /api/artista/registrar
```json
{
    "contrasenia": "pass",
    "correo": "ejemplo@ejemplo.com",
    "nikname": "nick",
    "biografia": "descripcion",
    "nombre": "nombre",
    "miSitio": {
        "id": 0,
        "miCategoria": {
            "id": 1,
            "nombreCat": "Cuentos"
        },
        "miFuente": {
            "id": 1,
            "nombre": "Arial"
        },
        "precioPremium": 0.0,
        "rrssFacebook": "",
        "rrssInstagram": "",
        "rrssTwitter": "",
        "rrssYouTube": "",
        "seccionTwitter": 0
    }
}
```

## (GET) /api/artista/notificaciones/{idArtista}
```json
```
## (POST) /api/artista/suscribirse
```json
{
	"idFan":"nickFan",
	"idArtista": "nickArtista"
}
```

## (GET) /api/artista/fans/{idArtista}
```json
```

## (POST) /api/artista/comprar-suscripcion-premium/{idFan}/{idArtista}
```json
```


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

# Fan
## (GET) /api/fan/notificaciones/{idFan}
```json
```

## (POST) /api/fan/registrar
```json
{
    "nikname": "nick",
    "correo": "ejemplo@ejemplo.com",
    "contrasenia": "pass",
    "nombre": "nombre",
    "apellido": "apellido",
    "fechaNac": "1990-01-01Z",
    "sexo": "Neutro",
    "ubicacion": "ubicacion"
}
```

## (GET) /api/fan/bloquear
```json
{
	"idFan":"nickFan",
	"idArtista": "nickArtista"
}
```


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

# Contenido
## (GET) /api/contenido/obtener/uno/{idContenido}
```json
```

## (GET) /api/contenido/obtener/todos/{idArtista}/{idFan}
**Nota:** el parámetro 'idFan' puede ser omitido si se trata de un visitante
```json
```

## (GET) /api/contenido/obtener/premium/{idArtista}/{idFan}
**Nota:** el parámetro 'idFan' puede ser omitido si se trata de un visitante
```json
```

## (PUT) /api/contenido/agregar-modificar/{idartista}
**JSON para agregar:**
```json
{
    "id": 0,
    "titulo": "titulo",
    "descripcion": "descripcion",
    "privacidad": "Publico",
    "precio": 0.0,
    "archivo": null,
    "tipo": "Audio",
    "miCategoria": {
        "id": 1,
        "nombreCategoria": "Música"
    }
}
```

**JSON para modificar:**
```json
{
    "id": 0,
    "titulo": "titulo",
    "descripcion": "descripcion",
    "privacidad": "Publico",
    "precio": 0.0
}
```


## (DELETE) /api/contenido/eliminar/{idartista}/{idContenido}
```json
```

## (POST) /api/contenido/calificar/{idFan}/{idContenido}/{idArtista}
```json
{
	"val": 5
}
```

## (POST) /api/contenido/calificar/{idFan}/{idContenido}/{idArtista}
```json
{
	"texto": "comentario"
}
```

## (POST) /api/contenido/reportar/{idFan}/{idContenido}/{idArtista}
```json
{
	"reporte": "reporte"
}
```

## (POST) /api/contenido/comprar/{idFan}/{idContenido}/{idArtista}
```json
```

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

# sin implementar
listarArtistas
obtenerFansDeSitio
obtenerCategoriasSitios
obtenerFuentes
obtenerCategoriasContenido
notificarFan
notificarFansArtista
pagarAPlataforma
