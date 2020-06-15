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

import beans.FuncionesComunes;

/**
 * Servlet Filter implementation class FiltroAdministrarSitio
 */
@WebFilter(description = "Controla el acceso a las paginas de administracion del sitio del artista", urlPatterns = { "/sitio-administrar.jsf" })
public class FiltroAdministrarSitio implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAdministrarSitio() {
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
		
		// Cuando alguien quiera entrar a la pagina 'sitio-administrar.jsf'
		// si el que esta logueado es un artista
		if (FuncionesComunes.rolActual(request, "artista")) {
			// todo bien, lo dejo pasar
			chain.doFilter(request, response);
		}else {
			// no no no, aca no podes entrar wacho
			res.sendRedirect(req.getContextPath() + "/401.jsf");
		}
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
