 
package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.*;
import com.TodoArte.Enums.*;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;

import beans.FuncionesComunes;

public class Pruebas {

	public static void main(String[] args) {
		//FrontOfficeController fo = new FrontOfficeController();
		//BackOfficeController bo = new BackOfficeController();
		
		cargarDatosDePrueba(true);

		//System.out.println("-- fin --");
		
	}
	public static void cargarDatosDePrueba(boolean log) {
		FrontOfficeController fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		bo.agregarAdministrador(new Administrador("admin", "1234", "admin@admin.com"));
		if (log) System.out.println("Creado Administrador: admin, 1234");
		
		CategoriaSitio categoriasSitio[] = {
				new CategoriaSitio(0, "Música"),
				new CategoriaSitio(0, "Fotografía"),
				new CategoriaSitio(0, "Pintura"),
				new CategoriaSitio(0, "Videos"),
		};
		for (int i = 0; i < categoriasSitio.length; i++) {
			categoriasSitio[i] = bo.agregarCategoriaDeSitio(categoriasSitio[i]);
			if (log) System.out.println("Creada CategoriaSitio: " + categoriasSitio[i].toString());
		}
		// ----------------------------------------------------------
		CategoriaContenido categoriasContenido[] = {
				new CategoriaContenido(0, "Música"),
				new CategoriaContenido(0, "Fotografía paisajistica"),
				new CategoriaContenido(0, "Comics"),
				new CategoriaContenido(0, "Pintura"),
				new CategoriaContenido(0, "Videoclip musical"),
				new CategoriaContenido(0, "Literatura"),
				new CategoriaContenido(0, "Poesia"),
				new CategoriaContenido(0, "Cuentos")
		};
		for (int i = 0; i < categoriasContenido.length; i++) {
			categoriasContenido[i] = bo.agregarCategoriaDeContenido(categoriasContenido[i]);
			if (log) System.out.println("Creada CategoriaContenido: " + categoriasContenido[i].toString());
		}

		// ----------------------------------------------------------
		Fuente fuentes[] = {
				new Fuente(0, "Arial"),
				new Fuente(0, "Comic Sans"),
				new Fuente(0, "Times New Roman")
		};
		for (int i = 0; i < fuentes.length; i++) {
			fuentes[i] = bo.agregarFuente(fuentes[i]);
			if (log) System.out.println("Creada Fuente: " + fuentes[i].toString());
		}

		// ----------------------------------------------------------
		Artista artistas[] = {
				new Artista("ergo", "1234", "ergo@gmail.com", 
						0, null, 
						"Ergo", "Una banda de rock flancito", 
						new Sitio(0, null, 0, 
								"82f5a2", "90b099", "000000", 
								"https://www.youtube.com/channel/UC58QzW1oJPoRk90V-mlMLZw", 
								"https://www.facebook.com/ERGOsitiooficial", 
								"", 
								"", 
								0, 
								categoriasSitio[0], 
								null, 
								fuentes[0])),
				new Artista("lorem", "1234", "lorem@lorem.com", 
						0, null, 
						"Lorem Images", "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Tenetur eos aspernatur amet repellendus! Quas vitae quod, rem facere fugiat iure eaque, mollitia ea deleniti voluptatem sit animi atque ipsa dolor!", 
						new Sitio(0, null, 0, 
								"a9caeb", "95acc2", "000c30", 
								"", 
								"", 
								"", 
								"https://twitter.com/cnatalpicasso?lang=es", 
								1, 
								categoriasSitio[1], 
								null, 
								fuentes[1])),
				new Artista("ipsum", "1234", "ipsum@ipsum.com", 
						0, null, 
						"Ipsum Videos", "Ipsum dolor sit amet, consectetur adipisicing elit. Repellendus voluptates veritatis eaque ab architecto illum assumenda? Blanditiis, consequuntur voluptate? Fuga tempora alias adipisci temporibus maxime natus reprehenderit aspernatur porro maiores.", 
						new Sitio(0, null, 0, 
								"540026", "82013c", "fcebf3", 
								"https://www.youtube.com/channel/UCDupeqPlIEnjmtPmUEvgvLg", 
								"", 
								"https://www.instagram.com/explore/tags/picaso/?hl=es", 
								"https://twitter.com/spacex", 
								2, 
								categoriasSitio[3], 
								null, 
								fuentes[2]))
		};
		NotificacionArtista notificacionesArtista[] = {
				new NotificacionArtista(0, "!Bienvenido!", "!Bienvenido a la plataforma TodoArte!", new Date(2020,00,01)),
				new NotificacionArtista(0, "!Sube contenido!", "No esperes mas, comienza a subir contenido a tu sitio", new Date(2020,00,01))
		};
		for (int i = 0; i < artistas.length; i++) {
			artistas[i] = fo.registrarUsuarioArtista(artistas[i], artistas[i].getMiSitio());
			for (int n = 0; n < notificacionesArtista.length; n++) {
				bo.notificarArtista(artistas[i].getNikname(), notificacionesArtista[n]);
				if (log) System.out.println("Creada NotificacionArtista: " + notificacionesArtista[n].toString());
			}
			if (log) System.out.println("Creada Artista: " + artistas[i].toString());
		}
		
		Fan fans[] = {
				new Fan("JotaJota96", "1234", "jjap96@gmail.com", 
						0, null, 
						"Juan", "Alvarez", 
						new Date(1996,4,11), 
						"San José de Mayo", 
						Sexo.Masculino),
				new Fan("luc31g", "1234", "luc31g@gmail.com", 
						0, null, 
						"Lucas", "Garrido", 
						new Date(1998,11,31), 
						"San Jos de Mayo", 
						Sexo.Masculino),
				new Fan("alfajor", "1234", "alfajor@gmail.com", 
						0, null, 
						"Carlos", "Balbiani", 
						new Date(1992,6,16), 
						"San José de Mayo", 
						Sexo.Neutro)
		};
		for (int i = 0; i < fans.length; i++) {
			fans[i] = fo.registrarUsuarioFan(fans[i]);
			if (log) System.out.println("Creada Fan: " + fans[i].toString());
		}
		Contenido contenidos1[] = {
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Publico, 0, 
						"Primera cancion de la banda", "Mil Poemas", 
						null, new Date(2016,05,13), false, false, categoriasContenido[0]),
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Privado, 0, 
						"El primer gran hit de la banda", "La flor", 
						null, new Date(2016,05,13), false, false, categoriasContenido[0]),
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Premium, 15, 
						"La cancion del segundo videoclip de la banda", "Otra vez", 
						null, new Date(2016,05,13), false, false, categoriasContenido[0])
		};
		for (int i = 0; i < contenidos1.length; i++) {
			contenidos1[i] = fo.agregarModificarContenido("ergo", contenidos1[i]);
			if (log) System.out.println("Creada Contenido: " + contenidos1[i].toString());
		}
		Contenido contenidos2[] = {
				new Contenido(0, TipoContenido.Imagen, 
						Privacidad.Publico, 0, 
						"Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quod odio molestias adipisci repellat culpa. Quis atque deserunt quae fuga, odio eius necessitatibus nulla unde velit voluptatibus doloribus veniam autem provident.", "Imagen lorem 1", 
						null, new Date(2017,05,3), false, false, categoriasContenido[1]),
				new Contenido(0, TipoContenido.Imagen, 
						Privacidad.Privado, 0, 
						"Lorem ipsum dolor sit amet consectetur adipisicing elit. Tenetur dolorem ipsum voluptates  nemo excepturi iste.", "Imagen lorem 2", 
						null, new Date(2018,07,13), false, false, categoriasContenido[1]),
				new Contenido(0, TipoContenido.Imagen, 
						Privacidad.Premium, 25, 
						"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi ut reiciendis labore laborum voluptatibus iure aliquid soluta sint, qui similique  possimus dicta, ad quas. Illo?", "Imagen lorem 3", 
						null, new Date(2019,10,23), false, false, categoriasContenido[1]),
				new Contenido(0, TipoContenido.Imagen, 
						Privacidad.Privado, 0, 
						"Lorem ipsum dolor sit amet consectetur adipisicing elit. Tenetur dolorem ipsum voluptates  nemo excepturi iste.", "Imagen lorem 4", 
						null, new Date(2018,07,13), false, false, categoriasContenido[1])
		};
		for (int i = 0; i < contenidos2.length; i++) {
			contenidos2[i] = fo.agregarModificarContenido("lorem", contenidos2[i]);
			if (log) System.out.println("Creada Contenido: " + contenidos2[i].toString());
		}
		
		Contenido contenidos3[] = {
				new Contenido(0, TipoContenido.Video, 
						Privacidad.Publico, 0, 
						"Lorem, ipsum dolor sit amet consectetur adipisicing elit. Maiores sed accusamus porro aspernatur distinctio nesciunt odio est dolorem similique doloribus odit corrupti, harum obcaecati deleniti iste explicabo sit error perspiciatis.", "Video lorem 1",
						null, new Date(2020,03,3), false, false, categoriasContenido[1]),
				new Contenido(0, TipoContenido.Video, 
						Privacidad.Privado, 0, 
						"Lorem ipsum dolor sit amet consectetur, adipisicing elit. Eius ex labore quam deleniti dolores quisquam possimus molestias animi at nostrum! Minima modi quia consectetur, nesciunt architecto delectus tempora alias quaerat.", "Video lorem 2",
						null, new Date(2020,04,13), false, false, categoriasContenido[1])
		};
		for (int i = 0; i < contenidos3.length; i++) {
			contenidos3[i] = fo.agregarModificarContenido("ipsum", contenidos3[i]);
			if (log) System.out.println("Creada Contenido: " + contenidos3[i].toString());
		}
		
		fo.suscribirseFanArtista("JotaJota96", "ergo");
		fo.suscribirseFanArtista("luc31g", "ergo");
		fo.suscribirseFanArtista("alfajor", "lorem");
		if (log) System.out.println("Creadas suscripciones");
		
		fo.comentarContenido(
				new Comentario(0, "Muy bueno", FuncionesComunes.fechaActual(), fans[0]),
				fans[0].getNikname(), 1, "ergo");
		fo.comentarContenido(
				new Comentario(0, "Excelente", FuncionesComunes.fechaActual(), fans[1]),
				fans[1].getNikname(), 1, "ergo");
		fo.comentarContenido(
				new Comentario(0, "Imponente!", FuncionesComunes.fechaActual(), fans[0]),
				fans[0].getNikname(), 2, "ergo");
		fo.comentarContenido(
				new Comentario(0, "Está muy buena esa imagen!", FuncionesComunes.fechaActual(), fans[2]),
				fans[2].getNikname(), 5, "ergo");
		if (log) System.out.println("Creados comentarios");
		
		if (log) System.out.println();
		if (log) System.out.println("-----------------------------------------");
		if (log) System.out.println();
	}
	
}





