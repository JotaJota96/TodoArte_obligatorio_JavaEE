package beans;

import java.io.InputStream;
import java.sql.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
/**
 * Esta clase contiene funciones que pueden ser utiles en cualquier Bean de este proyecto web
 * Para llamar una funcion de esta clase, se debe hacer: FuncionesComunes.funcionAEjecutar(...)
 */
public class FuncionesComunes {

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
	
	public static java.sql.Date utilDateToSqlDate(java.util.Date fechaUtil){
		return new Date(fechaUtil.getTime());
	}
	
	/**
	 * Devuelve true si el usuario actual esta logueado como el rol esecificado (admin, fan, artista)
	 * @param rol
	 * @return
	 */
	public static boolean rolActual(String rol) {
		String rolSesion = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rol");
		if (rolSesion != null) {
			return rolSesion.equals(rol);
		}
		return false;
	}

	/**
	 * Devuelve el parametro que viene en la URL (si es que realmente viene)
	 * @param name Nombre del parametro
	 * @return Parametro en la URL o NULL si no viene nada
	 */
	public static String getParametro(String name) {
		try {
			return (String) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Para agregar nuevas funciones, recordar que debe ser 'public static'
	 */
	
}
