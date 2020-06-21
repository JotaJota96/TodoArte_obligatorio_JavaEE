package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
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
	
	private String idArtista = "";
	private ArrayList<String[]> opcionesIzquierda = new ArrayList<String[]>();
	private ArrayList<String[]> opcionesDerecha= new ArrayList<String[]>();
	private boolean fanLogueado = false;
	private boolean artistaLogueado = false;
	private String linkLogo = "";
	private String nickUsuario = null;
	private boolean mostrarOpcionQyA = false;
	
	public ArrayList<String[]> getOpcionesIzquierda() {
		return opcionesIzquierda;
	}

	public void setOpcionesIzquierda(ArrayList<String[]> opcionesIzquierda) {
		this.opcionesIzquierda = opcionesIzquierda;
	}

	//*********************************************************************************************************
	private void definirOpcionesIzquierda() {
		// Carg en el menu la opcion 'Sobre mi' (siempre debe estar)
		String[] opcion = new String[] {"Sobre mí", Redirector.redirect("sitio.jsf", "id=" + idArtista)};
		opcionesIzquierda.add(opcion);
		
		// Obtengo un listado de los tipos de contenidos disponibles y agrego las opciones al menu
		ArrayList<TipoContenido> tipos = this.obtenerTiposContenidosDisponibles(fo.obtenerContenido(idArtista, idArtista));
		for (TipoContenido tc : tipos) {
			opcion = new String[2];
			// segun el tipo agrego la opcion
			switch (tc) {
			case Imagen:
				opcion = new String[] {"Imágenes", Redirector.redirect("sitio-imagenes.jsf", "id=" + idArtista)};
				break;
			case Audio:
				opcion = new String[] {"Música", Redirector.redirect("sitio-musica.jsf", "id=" + idArtista)};
				break;
			case Video:
				opcion = new String[] {"Videos", Redirector.redirect("sitio-videos.jsf", "id=" + idArtista)};
				break;
			case PDF:
				//opcion = new String[] {"PDFs", Redirector.redirect("sitio-pdf.jsf", "id=" + idArtista)};
				break;
			case Otros:
				//opcion = new String[] {"Otros", Redirector.redirect("sitio-otros.jsf", "id=" + idArtista)};
				break;
			default:
				opcion = new String[] {"WTF?", Redirector.redirect("sitio.jsf", "id=" + idArtista)};
				break;
			}
			opcionesIzquierda.add(opcion);
		}

		// si es el artista
		if (FuncionesComunes.rolActual("artista")) {
			mostrarOpcionQyA = true;
			opcionesIzquierda.add(new String[]{"Administrar", Redirector.redirect("sitio-administrar.jsf")});
			linkLogo = Redirector.redirect("sitio.jsf", "id="+idArtista);
		}
		
		// si es un fan y ademas es seguidor del artista
		if (FuncionesComunes.rolActual("fan")) {
			String nick = FuncionesComunes.usuarioActual();
			if (((Artista) fo.obtenerDatosUsuario(idArtista)).getMiSitio().esFan(nick)) {
				mostrarOpcionQyA = true;
			}
		}
		
		// si es un visitante o un fan
		if (FuncionesComunes.usuarioActual() == null || FuncionesComunes.rolActual("fan")) {
			linkLogo = Redirector.redirect("home.jsf");
		}
		
	}

	private void definirOpcionesDerecha() {
		// si es un visitante
		if (FuncionesComunes.usuarioActual() == null ) {
			opcionesDerecha.add(new String[]{"Iniciar sesión", Redirector.redirect("login.jsf")});
			opcionesDerecha.add(new String[]{"Registrarse", Redirector.redirect("registro.jsf")});
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
		try {
			// cargo el ID del artista
			if (FuncionesComunes.rolActual("artista")) {
				this.idArtista = FuncionesComunes.usuarioActual();
			}else {
				this.idArtista = FuncionesComunes.getParametro("id");
			}
			
			// establezco variables
			nickUsuario = FuncionesComunes.usuarioActual();
			if (FuncionesComunes.rolActual("fan"))   fanLogueado = true;
			if (FuncionesComunes.rolActual("artista")) artistaLogueado = true;
			
			// Cargo las opciones que deben aparecer en el menu
			this.definirOpcionesIzquierda();
			this.definirOpcionesDerecha();
		}catch (Exception e) {
		}
	}
	public String getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}

	public ArrayList<String[]> getOpcionesDerecha() {
		return opcionesDerecha;
	}

	public void setOpcionesDerecha(ArrayList<String[]> opcionesDerecha) {
		this.opcionesDerecha = opcionesDerecha;
	}

	public boolean isFanLogueado() {
		return fanLogueado;
	}

	public void setFanLogueado(boolean fanLogueado) {
		this.fanLogueado = fanLogueado;
	}

	public boolean isArtistaLogueado() {
		return artistaLogueado;
	}

	public void setArtistaLogueado(boolean artistaLogueado) {
		this.artistaLogueado = artistaLogueado;
	}

	public String getLinkLogo() {
		return linkLogo;
	}

	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}

	public String getNickUsuario() {
		return nickUsuario;
	}

	public void setNickUsuario(String nickUsuario) {
		this.nickUsuario = nickUsuario;
	}

	public boolean isMostrarOpcionQyA() {
		return mostrarOpcionQyA;
	}

	public void setMostrarOpcionQyA(boolean mostrarOpcionQyA) {
		this.mostrarOpcionQyA = mostrarOpcionQyA;
	}
}
