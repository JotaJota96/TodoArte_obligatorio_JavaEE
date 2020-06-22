package beans.MiSitio;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Fan;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;
import beans.Redirector;

@Named
@RequestScoped
public class SitioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	FrontOfficeInterface fo = new FrontOfficeController();
	
	private String idArtista = "";
	private Artista artista = new Artista();
	private Sitio sitio = new Sitio();
	
	private boolean mostrarIconoFacebook = false;
	private boolean mostrarIconoTwitter = false;
	private boolean mostrarIconoInstagram= false;
	private boolean mostrarIconoYoutube= false;
	
	private boolean mostrarBotonComprar = false;
	private boolean mostrarBotonSeguir = false;
	
	//****************************************************************************
	public String seguir() {
		String redireccion = Redirector.redirect("500.jsf");
		
		if (FuncionesComunes.rolActual("fan")) {
			String nick = FuncionesComunes.usuarioActual();
			try {
				fo.suscribirseFanArtista(nick, idArtista);
				redireccion = Redirector.redirect("sitio.jsf", "id=" + idArtista);
			} catch (Exception e) {
				System.out.println("CATCH-2: " + e.getMessage());
			}
		}
		return redireccion;
	}
	
	public String comprar() {
		String redireccion = Redirector.redirect("500.jsf");

		if (FuncionesComunes.rolActual("fan")) {
			String nick = FuncionesComunes.usuarioActual();
			try {
				fo.comprarPremium(nick, idArtista);
				redireccion = Redirector.redirect("sitio.jsf", "id=" + idArtista);
			} catch (Exception e) {
				System.out.println("CATCH: " + e.getMessage());
			}
		}
		return redireccion;
	}
	
	public boolean saldoSuficienteParaComprar() {
		if (FuncionesComunes.rolActual("fan")) {
			String nick = FuncionesComunes.usuarioActual();
			try {
				Fan f = (Fan) fo.obtenerDatosUsuario(nick);
				return f.getSaldo() > sitio.getPrecioPremium();
			} catch (Exception e) {
			}
		}
		return false;
	}
	//****************************************************************************
 	public SitioBean() {
		try {
			if (FuncionesComunes.rolActual("artista")) {
				idArtista = FuncionesComunes.usuarioActual();
			}else {
				idArtista = FuncionesComunes.getParametro("id");
			}
			artista = (Artista) fo.obtenerDatosUsuario(idArtista);
			sitio = artista.getMiSitio();

			mostrarIconoFacebook = sitio.getRrssFacebook() != null && !sitio.getRrssFacebook().equals("");
			mostrarIconoTwitter = sitio.getRrssTwitter() != null && !sitio.getRrssTwitter().equals("");
			mostrarIconoInstagram = sitio.getRrssInstagram() != null && !sitio.getRrssInstagram().equals("");
			mostrarIconoYoutube = sitio.getRrssYouTube() != null && !sitio.getRrssYouTube().equals("");
			
			if (FuncionesComunes.rolActual("fan")) {
				String nick = FuncionesComunes.usuarioActual();
				mostrarBotonSeguir = true;
				for (Map.Entry<Integer, FanSigueSitio> entry : sitio.getMisFans().entrySet()) {
					if (entry.getValue().getMiFan().getNikname().equals(nick)) {
						if ( ! entry.getValue().getPremiun()) {
							mostrarBotonComprar = true;
						}
						mostrarBotonSeguir = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("CATCH: " + e.getMessage());
		}
	}

	public String getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(String idArtista) {
		this.idArtista = idArtista;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Sitio getSitio() {
		return sitio;
	}
	public void setSitio(Sitio sitio) {
		this.sitio = sitio;
	}
	public boolean isMostrarIconoFacebook() {
		return mostrarIconoFacebook;
	}
	public void setMostrarIconoFacebook(boolean mostrarIconoFacebook) {
		this.mostrarIconoFacebook = mostrarIconoFacebook;
	}
	public boolean isMostrarIconoTwitter() {
		return mostrarIconoTwitter;
	}
	public void setMostrarIconoTwitter(boolean mostrarIconoTwitter) {
		this.mostrarIconoTwitter = mostrarIconoTwitter;
	}
	public boolean isMostrarIconoInstagram() {
		return mostrarIconoInstagram;
	}
	public void setMostrarIconoInstagram(boolean mostrarIconoInstagram) {
		this.mostrarIconoInstagram = mostrarIconoInstagram;
	}
	public boolean isMostrarIconoYoutube() {
		return mostrarIconoYoutube;
	}
	public void setMostrarIconoYoutube(boolean mostrarIconoYoutube) {
		this.mostrarIconoYoutube = mostrarIconoYoutube;
	}

	public boolean isMostrarBotonComprar() {
		return mostrarBotonComprar;
	}
	public void setMostrarBotonComprar(boolean mostrarBotonComprar) {
		this.mostrarBotonComprar = mostrarBotonComprar;
	}
	public boolean isMostrarBotonSeguir() {
		return mostrarBotonSeguir;
	}
	public void setMostrarBotonSeguir(boolean mostrarBotonSeguir) {
		this.mostrarBotonSeguir = mostrarBotonSeguir;
	}
	
}
