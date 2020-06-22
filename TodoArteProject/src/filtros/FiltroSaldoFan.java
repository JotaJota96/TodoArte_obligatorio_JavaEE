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
 * Servlet Filter implementation class FiltroSaldoFan
 */
@WebFilter(
		description = "Permite el acceso a la consulta de saldo solo si se esta logueado como fan", 
		urlPatterns = { 
				"/saldo-fan.jsf" ,
				"/notificaciones-fan.jsf" 
})
public class FiltroSaldoFan implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroSaldoFan() {
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
		
		// Cuando alguien quiera entrar a la pagina 'sitio-administrar.jsf'
		// si el que esta logueado es un fan
		if (FuncionesComunes.rolActual(req, "fan")) {
			// todo bien, lo dejo pasar
			chain.doFilter(request, response);
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
