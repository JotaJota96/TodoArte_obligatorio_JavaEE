package com.TodoArte;

import java.sql.Date;

import com.TodoArte.Classes.*;
import com.TodoArte.Enums.*;
import com.TodoArte.FachadeControllers.BackOfficeController;
import com.TodoArte.FachadeControllers.FrontOfficeController;

public class Pruebas {

	public static void main(String[] args) {
		//FrontOfficeController fo = new FrontOfficeController();
		//BackOfficeController bo = new BackOfficeController();
		
		//cargarDatosDePrueba();

		//System.out.println("-- fin --");
	}
	
	public static void cargarDatosDePrueba() {
		FrontOfficeController fo = new FrontOfficeController();
		BackOfficeController bo = new BackOfficeController();
		
		CategoriaSitio categoriasSitio[] = {
				new CategoriaSitio(0, "Música"),
				new CategoriaSitio(0, "Fotografía"),
				new CategoriaSitio(0, "Pintura"),
				new CategoriaSitio(0, "Videos"),
		};
		for (int i = 0; i < categoriasSitio.length; i++) {
			categoriasSitio[i] = bo.agregarCategoriaDeSitio(categoriasSitio[i]);
			System.out.println("Creada CategoriaSitio: " + categoriasSitio[i].toString());
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
			System.out.println("Creada CategoriaContenido: " + categoriasContenido[i].toString());
		}

		// ----------------------------------------------------------
		Fuente fuentes[] = {
				new Fuente(0, "Arial"),
				new Fuente(0, "Comic Sans"),
				new Fuente(0, "Times New Roman")
		};
		for (int i = 0; i < fuentes.length; i++) {
			fuentes[i] = bo.agregarFuente(fuentes[i]);
			System.out.println("Creada Fuente: " + fuentes[i].toString());
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
						"Pablo Picasso", "Descripción\n" + 
								"Pablo Ruiz Picasso fue un pintor y escultor español, creador, junto con Georges Braque, del cubismo.", 
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
				new NotificacionArtista(0, "¡Bienvenido!", "¡Bienvenido a la plataforma TodoArte!", new Date(2020,00,01)),
				new NotificacionArtista(0, "¡Sube contenido!", "No esperes mas, comienza a subir contenido a tu sitio", new Date(2020,00,01))
		};
		for (int i = 0; i < artistas.length; i++) {
			artistas[i] = fo.registrarUsuarioArtista(artistas[i], artistas[i].getMiSitio());
			for (int n = 0; n < notificacionesArtista.length; n++) {
				bo.notificarArtista(artistas[i].getNikname(), notificacionesArtista[n]);
				System.out.println("Creada NotificacionArtista: " + notificacionesArtista[n].toString());
			}
			System.out.println("Creada Artista: " + artistas[i].toString());
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
						"San José de Mayo", 
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
			System.out.println("Creada Fan: " + fans[i].toString());
		}
		
		
		
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
}





