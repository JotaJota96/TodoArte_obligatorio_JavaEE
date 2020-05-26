package beans;

import java.io.InputStream;
import java.sql.Date;

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
	 * Para agregar nuevas funciones, recordar que debe ser 'public static'
	 */
	
}
