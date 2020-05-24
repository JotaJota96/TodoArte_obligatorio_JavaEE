 
package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.*;
import com.TodoArte.Enums.*;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;

public class Pruebas {

	public static void main(String[] args) {
		FrontOfficeController fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		cargarDatosDePrueba(false);

		System.out.println("-- fin --");
	}
	public static void cargarDatosDePrueba(boolean log) {
		FrontOfficeController fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		CategoriaSitio categoriasSitio[] = {
				new CategoriaSitio(0, "M�sica"),
				new CategoriaSitio(0, "Fotograf�a"),
				new CategoriaSitio(0, "Pintura"),
				new CategoriaSitio(0, "Videos"),
		};
		for (int i = 0; i < categoriasSitio.length; i++) {
			categoriasSitio[i] = bo.agregarCategoriaDeSitio(categoriasSitio[i]);
			if (log) System.out.println("Creada CategoriaSitio: " + categoriasSitio[i].toString());
		}
		// ----------------------------------------------------------
		CategoriaContenido categoriasContenido[] = {
				new CategoriaContenido(0, "M�sica"),
				new CategoriaContenido(0, "Fotograf�a paisajistica"),
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
				new Artista("ArdeTroya", "1234", "arde@troya.com", 
						0, null, 
						"Arde Troya", "Una banda de covers del peyote y cosas por el estilo", 
						new Sitio(0, null, 0, 
								"a9caeb", "95acc2", "000c30", 
								"", 
								"", 
								"", 
								"", 
								0, 
								categoriasSitio[0], 
								null, 
								fuentes[1])),
				new Artista("picasso", "1234", "pablo@picasso.com", 
						0, null, 
						"Pablo Picasso", "Descripci�n\n" + 
								"Pablo Ruiz Picasso fue un pintor y escultor espa�ol, creador, junto con Georges Braque, del cubismo.", 
						new Sitio(0, null, 0, 
								"540026", "82013c", "fcebf3", 
								"https://www.youtube.com/channel/UCDupeqPlIEnjmtPmUEvgvLg", 
								"", 
								"https://www.instagram.com/explore/tags/picaso/?hl=es", 
								"https://twitter.com/cnatalpicasso?lang=es", 
								1, 
								categoriasSitio[2], 
								null, 
								fuentes[2]))
		};
		NotificacionArtista notificacionesArtista[] = {
				new NotificacionArtista(0, "�Bienvenido!", "�Bienvenido a la plataforma TodoArte!", new Date(2020,00,01)),
				new NotificacionArtista(0, "�Sube contenido!", "No esperes mas, comienza a subir contenido a tu sitio", new Date(2020,00,01))
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
						"San Jos� de Mayo", 
						Sexo.Masculino),
				new Fan("luc31g", "1234", "luc31g@gmail.com", 
						0, null, 
						"Lucas", "Garrido", 
						new Date(1998,11,31), 
						"San Jos� de Mayo", 
						Sexo.Masculino),
				new Fan("alfajor", "1234", "alfajor@gmail.com", 
						0, null, 
						"Carlos", "Balbiani", 
						new Date(1992,6,16), 
						"San Jos� de Mayo", 
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
						"Segunda cancion de la banda (creo)", "Pensando en volver", 
						null, new Date(2016,05,13), false, false, categoriasContenido[0]),
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Premium, 10, 
						"Ultimo tema de la banda", "Cancion de una muerte anunciada", 
						null, new Date(2016,05,13), false, false, categoriasContenido[0])
		};
		for (int i = 0; i < contenidos1.length; i++) {
			contenidos1[i] = fo.agregarModificarContenido("ergo", contenidos1[i]);
			if (log) System.out.println("Creada Contenido: " + contenidos1[i].toString());
		}
		Contenido contenidos2[] = {
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Publico, 0, 
						"Mi pintura 1", "Pintura 1", 
						null, new Date(2016,05,3), false, false, categoriasContenido[3]),
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Privado, 0, 
						"Mi pintura 2", "Pintura 2", 
						null, new Date(2016,05,13), false, false, categoriasContenido[3]),
				new Contenido(0, TipoContenido.Audio, 
						Privacidad.Premium, 10, 
						"Mi pintura 3", "Pintura 3", 
						null, new Date(2016,05,23), false, false, categoriasContenido[3])
		};
		for (int i = 0; i < contenidos2.length; i++) {
			contenidos2[i] = fo.agregarModificarContenido("picasso", contenidos2[i]);
			if (log) System.out.println("Creada Contenido: " + contenidos2[i].toString());
		}
		
		
		if (log) System.out.println();
		if (log) System.out.println("-----------------------------------------");
		if (log) System.out.println();
	}
	
}





