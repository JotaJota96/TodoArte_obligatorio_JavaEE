package beans;

import java.io.InputStream;
import java.sql.Date;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**

 * Esta clase contiene funciones que pueden ser utiles en cualquier Bean de este proyecto web
 * Para llamar una funcion de esta clase, se debe hacer: FuncionesComunes.funcionAEjecutar(...)
 */

import com.TodoArte.Enums.TipoContenido;
public class FuncionesComunes {

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Convierte el Part de un HTTP request que contiene un archivo, a un array de bytes
	 * @param file Part de un HTTP request 
	 * @return Array de bytes del Part
	 */
	public static byte[] partToBytes(Part file) {
		if (file == null) return null;
		
	    try (InputStream input = file.getInputStream()) {
	    	
	        //Files.copy(input, new File(uploads, filename).toPath());
			
	    	Part partImagen = file;
	        //String nombreArchivo = Paths.get(partImagen.getSubmittedFileName()).getFileName().toString();
	        InputStream archivoContenido = partImagen.getInputStream();
	        
	        if (archivoContenido.available() > 0) {
	            byte[] byteArr = new byte[archivoContenido.available()];
	            archivoContenido.read(byteArr);
	            return byteArr;
	        } else {
	        	return null;
	        }
	    }
	    catch (Exception e) {
        	return null;
	    }
	}

	//---------------------------------------------------------------------------------------------
	/**
	 * Convierte fecha de tipo java.util.Date a java.sql.Date
	 * @param fechaUtil Fecha a convertir
	 * @return Resultado de la conversion
	 */
	public static java.sql.Date utilDateToSqlDate(java.util.Date fechaUtil){
		return new Date(fechaUtil.getTime());
	}

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Devuelve true si el usuario actual esta logueado como el rol esecificado (admin, fan, artista)
	 * @param rol rol que se quiere saber si está logueado
	 * @return
	 */
	public static boolean rolActual(String rol) {
		try {
			String rolSesion = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");
			if (rolSesion != null) {
				return rolSesion.equals(rol);
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * (para ser usada desde los filtros) Devuelve true si el usuario actual esta logueado como el rol esecificado (admin, fan, artista)
	 * @param request peticion recibida como parametro en el filtro
	 * @param rol rol que se quiere saber si está logueado
	 * @return
	 */
	public static boolean rolActual(ServletRequest request, String rol) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String rolSesion = (String ) req.getSession().getAttribute("rol");
			if (rolSesion != null) {
				return rolSesion.equals(rol);
			}
		} catch (Exception e) {
		}
		return false;
	}
	public static String rolActual(ServletRequest request) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String rolSesion = (String ) req.getSession().getAttribute("rol");
			if (rolSesion != null && rolSesion.equals("")) {
				return null;
			}
			return rolSesion;
		} catch (Exception e) {
		}
		return null;
	}

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Devuelve el nickname del usuario actual o NULL si no hay uno logueado
	 * @param request peticion recibida como parametro en el filtro
	 * @return Devuelve el nickname del usuario actual o NULL si no hay uno logueado
	 */
	public static String usuarioActual() {
		try {
			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol") == null) {
				return null;
			}
			return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nickname");
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * (para ser usada desde los filtros)  Devuelve el nickname del usuario actual o NULL si no hay uno logueado
	 * @return Devuelve el nickname del usuario actual o NULL si no hay uno logueado
	 */
	public static String usuarioActual(ServletRequest request) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			return (String ) req.getSession().getAttribute("nickname");
		} catch (Exception e) {
		}
		return null;
	}

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Devuelve el parametro que viene en la URL (si es que realmente viene)
	 * @param name Nombre del parametro
	 * @return Parametro en la URL o NULL si no viene nada
	 */
	public static String getParametro(String name) {
		try {
			return (String) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
		}catch (Exception e) {
			return null;
		}
	}
	
	public static String getPaginaSolicitada() {
		try {
			String url = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
			String[] partes = url.split("/");
			return partes[partes.length - 1];
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	public static String getPaginaSolicitada(ServletRequest request) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURI();
			String[] partes = url.split("/");
			return partes[partes.length - 1];
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	
	/**
	 * (para ser usada desde los filtros) Devuelve el parametro que viene en la URL (si es que realmente viene)
	 * @param request peticion recibida como parametro en el filtro
	 * @param name Nombre del parametro
	 * @return Parametro en la URL o NULL si no viene nada
	 */
	public static String getParametro(ServletRequest request, String name) {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			return (String) req.getParameter(name);
		} catch (Exception e) {
		}
		return null;
	}

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Debuele la extencion del archivo.
	 * @param nombreDelArchivo, que contiene el nombre completo del archivo (pepito.jpg)
	 * @return con la extecion del archvo con el punto egemplo (.jpg), null si no tiene extencion
	 */
	public static String obtenerExtecion(String nombreDelArchivo) {
		int lastIndex = nombreDelArchivo.lastIndexOf(".");
		if(lastIndex ==-1) {
			return null;
		}
		return nombreDelArchivo.substring(lastIndex);
	}
	
	/**
	 * Devuelve el tipo de contenido correspondiente a la extencion.
	 * @param extencion, extencion del archivo
	 * @return TipoContenido
	 */
	public static TipoContenido obtenerTipoContenido(String extencion) {
		if(extencion==null) {
			return TipoContenido.Otros;
		}
		switch (extencion.toLowerCase()) {
		
		case ".jpg" :
		case ".png" :
		case ".gif" :
			return TipoContenido.Imagen;
			
		case ".mp3" :
		case ".wav" :
		case ".ogg" :
			return TipoContenido.Audio;
			
		case ".mp4" :
		case ".mpeg" :
		case ".avi" :
		case ".mov" :
			return TipoContenido.Video;
		
		case ".pdf" :
			return TipoContenido.PDF;
			
		default:
			return TipoContenido.Otros;
		}
	}

	/**
	 * Devuelve la fecha actual
	 * @return
	 */
	public static Date fechaActual() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * Para agregar nuevas funciones, recordar que debe ser 'public static'
	 */
	
}
