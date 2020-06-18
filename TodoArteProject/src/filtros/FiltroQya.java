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
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

/**
 * Servlet Filter implementation class FiltroQya
 */
@WebFilter(description = "Solo permite el acceso a un QyA al artista proipietario y a los fans que lo siguen", urlPatterns = { "/qya.jsf" })
public class FiltroQya implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroQya() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
		
		FrontOfficeInterface fo = new FrontOfficeController();
		String idArtista = FuncionesComunes.getParametro(req, "id");
		
		if (idArtista == null) {
			res.sendRedirect(req.getContextPath() + "/404.jsf");
		}
		Artista artista = (Artista) fo.obtenerDatosUsuario(idArtista);
		if (artista == null) {
			res.sendRedirect(req.getContextPath() + "/404.jsf");
		}
		
		if (FuncionesComunes.rolActual(req, "artista")) {
			String nick = FuncionesComunes.usuarioActual(req);
			
			if (idArtista.equals(nick)) {
				chain.doFilter(request, response);
			}
		}else if (FuncionesComunes.rolActual(req, "fan")) {
			String nick = FuncionesComunes.usuarioActual(req);
			
			if (artista.getMiSitio().esFan(nick)){
				chain.doFilter(request, response);
			}
		}else {
			// no no no, aca no podes entrar wacho
			res.sendRedirect(req.getContextPath() + "/401.jsf");
		}
	}
	//****************************************************************************************************

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
