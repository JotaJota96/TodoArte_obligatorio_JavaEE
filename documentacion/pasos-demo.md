

# Sitio web
## FrontOffice
1. Registrarse como artista (con costo premium)
    **Nickname:** pepe  
    **Biografía:** Lorem ipsum dolor, sit amet consectetur adipisicing elit. At voluptatibus culpa voluptatem distinctio voluptas eum officia rerum placeat similique dolorem doloremque perferendis explicabo maxime, adipisci nemo necessitatibus quas! Nihil, odit!  
    **Precio Premium:** 150  
    **Youtube:** https://www.youtube.com/channel/UC6p0TCY8JtOVzuH_TjSy4VQ  
    **Twitter:** https://twitter.com/UTECuy  
    **Seccion Twitter:** Derecha  

2. Subir contenido  
	2.1. Subir un contenido  
		**Titulo:** Mi ciudad  
		**Descripción:** Time lapse de una tarde en mi ciudad  
		**Precio:** 15  
		**Privacidad:** premium  
	2.2. Subir otro contenido  
		**Titulo:** Otra vez mi ciudad  
		**Descripción:** otro timelapse  
		**Precio:** 15  
		**Privacidad:** premium  

3. Cerrar sesion y loguearse como fan y mostrar su saldo
    **Usuario:** JotaJota96
    **Contraseña:** 1234

4. Buscar artista `pepe`

5. Seguir el sitio creado
6. Ver sus contenidos (no se ve el premium)
7. Comprar contenido premium
8. Comprar suscripcion premium
9. Mostrar el saldo del fan
10. Ver sus contenidos (si se ve el premium)
11. Calificar contenido
12. Comentar contenido
13. Ingresar al Q&A en dos pestañas y chatear

14. Cerrar sesion y loguearse como el artista creado
15. Ver notificacion artista
16. Ver estadisticas de sitio
17. Pagar a plataforma
18. Ver saldo


## BackOffice
1. Loguearse como administrador
2. Ver pagos a plataforma


# API REST
1. Obtener datos del artista
	**Método:** GET
	**URL:** http://localhost:8080/TodoArteProject/api/usuario/obtener/pepe

2. Recargar saldo a un usuario
	**Método:** PUT
	**URL:** http://localhost:8080/TodoArteProject/api/usuario/recargar
	**Cuerpo:** 
	```json
	{
		"idUsuario": "pepe",
		"monto": 200
	}
	```

3. Modificar contenido
	**Método:** PUT
	**URL:** http://localhost:8080/TodoArteProject/api/contenido/agregar-modificar/pepe
	**Cuerpo:** 
	```json
	{
		"id": 10,
		"titulo": "Timelapse de mi ciudad",
		"descripcion": "Esto lo filmé el otro dia, es un time lapse de mi ciudad en la tarde",
		"privacidad": "Premium",
		"precio": 20
	}
	```


4. Eliminar contenido
	**Método:** DELETE
	**URL:** http://localhost:8080/TodoArteProject/api/contenido/eliminar/pepe/10
	










