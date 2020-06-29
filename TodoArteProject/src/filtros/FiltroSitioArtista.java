package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeControllers.FrontOfficeController;

import beans.FuncionesComunes;

/**
 * Servlet Filter implementation class FiltroSitioArtista
 */
@WebFilter(
		description = "Si por la URL no viene el parametro 'id' o no existe el artista, redirige a la 404. Tambien verifica el rol", 
		urlPatterns = { 
				"/sitio.jsf", 
				"/sitio-videos.jsf", 
				"/sitio-musica.jsf", 
				"/sitio-imagenes.jsf"
		})
public class FiltroSitioArtista implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroSitioArtista() {
        // TODO Auto-generated constructor stub
    }

	//****************************************************************************************************
    
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// este codigo se basa en
		// https://stackoverflow.com/questions/12516349/how-control-access-and-rights-in-jsf

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// primero verifico que venga el parametro 'id'
		String id = FuncionesComunes.getParametro(req, "id");
		if (id == null) {
			res.sendRedirect(req.getContextPath() + "/404.jsf");
		}
		// verifico que exista un artista con ese id
		Usuario u = new FrontOfficeController().obtenerDatosUsuario(id);
		if (u == null || (u != null && !(u instanceof Artista))) {
			res.sendRedirect(req.getContextPath() + "/404.jsf");
		}
		
		// si llega hasta aca, la solicitud es correcta, pero hay que verificar los roles
		
		// si el que esta logueado es un admin
		if (FuncionesComunes.rolActual(request, "admin")) {
			// no no no, aca no podes entrar wacho
			res.sendRedirect(req.getContextPath() + "/401.jsf");

		// si el que esta logueado es un artista
		} else if (FuncionesComunes.rolActual(request, "artista")) {
			// obtengo el id del artista logueado actualmente
			String idArtistaLogueado = FuncionesComunes.usuarioActual(req);
			
			//si es un artista visitando el sitio de otro
			if ( ! id.equals(idArtistaLogueado)) {
				// no no no, aca no podes entrar wacho
				res.sendRedirect(req.getContextPath() + "/401.jsf");
			}
		}

		// si llega hasta aca, el que esta logueado es es fan, o es un artista visitando su propio sitio, o no es alguien logueado
		
		// todo bien, lo dejo pasar
		chain.doFilter(request, response);
	}
	
	//****************************************************************************************************
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
