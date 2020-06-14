package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.Enums.TipoContenido;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;
import beans.Redirector;

@Named
@RequestScoped
public class HeaderCompletoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	FrontOfficeInterface fo = new FrontOfficeController();
	
	private boolean mostrarAdministrar = false;
	private String idArtista = "";
	private ArrayList<String[]> opciones = new ArrayList<String[]>();
	

	//*********************************************************************************************************
	private void definirOpcionesMenu() {
		// Carg en el menu la opcion 'Sobre mi' (siempre debe estar)
		String opcion[] = new String[2];
		opcion[0] = "Sobre mí";
		opcion[1] = Redirector.redirect("sitio.jsf", "id=" + idArtista);
		opciones.add(opcion);
		
		// Obtengo un listado de los tipos de contenidos disponibles y agrego las opciones al menu
		ArrayList<TipoContenido> tipos = this.obtenerTiposContenidosDisponibles(fo.obtenerContenido(idArtista, idArtista));
		for (TipoContenido tc : tipos) {
			opcion = new String[2];
			// segun el tipo agrego la opcion
			switch (tc) {
			case Imagen:
				opcion[0] = "Imágenes";
				opcion[1] = Redirector.redirect("sitio-imagenes.jsf", "id=" + idArtista);
				break;
			case Audio:
				opcion[0] = "Música";
				opcion[1] =  Redirector.redirect("sitio-musica.jsf", "id=" + idArtista);
				break;
			case Video:
				opcion[0] = "Videos";
				opcion[1] =  Redirector.redirect("sitio-videos.jsf", "id=" + idArtista);
				break;
			case PDF:
				opcion[0] = "PDFs";
				opcion[1] =  Redirector.redirect("sitio-pdf.jsf", "id=" + idArtista);
				break;
			case Otros:
				opcion[0] = "Otros";
				opcion[1] =  Redirector.redirect("sitio-otros.jsf", "id=" + idArtista);
				break;
			default:
				opcion[0] = "WTF?"; // lol xD
				opcion[1] =  Redirector.redirect("sitio.jsf", "id=" + idArtista);
				break;
			}
			opciones.add(opcion);
		}
	}
	
	// y aqui una funcion recursiva... ¿podria haberla hecho con un for? si, pero yo vivo al limite
	private ArrayList<TipoContenido> obtenerTiposContenidosDisponibles(ArrayList<Contenido> contenidos) {
		if (contenidos == null || contenidos.isEmpty()) return new ArrayList<TipoContenido>();
		
		Contenido c = contenidos.remove(contenidos.size() - 1);
		ArrayList<TipoContenido> tipos = obtenerTiposContenidosDisponibles(contenidos);
		
		if ( ! tipos.contains(c.getTipo())) tipos.add(c.getTipo());
		
		return tipos;
	}

	//*********************************************************************************************************
	public HeaderCompletoBean() {
		mostrarAdministrar = FuncionesComunes.rolActual("artista");
		try {
			// cargo el ID del artista
			this.idArtista = FuncionesComunes.getParametro("id");
			// Cargo las opciones que deben aparecer en el menu
			this.definirOpcionesMenu();
		}catch (Exception e) {
		}
	}
	public boolean isMostrarAdministrar() {
		return mostrarAdministrar;
	}
	public void setMostrarAdministrar(boolean mostrarAdministrar) {
		this.mostrarAdministrar = mostrarAdministrar;
	}
	public String getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	public ArrayList<String[]> getOpciones() {
		return opciones;
	}
	public void setOpciones(ArrayList<String[]> opciones) {
		this.opciones = opciones;
	}
}
