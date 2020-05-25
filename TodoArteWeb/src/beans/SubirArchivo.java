package beans;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import java.io.File;
import java.io.InputStream;

@Named
@SessionScoped
public class SubirArchivo implements Serializable  {
	
	private String uploads = "C:\\Users\\Carlos BM\\Desktop"; //ruta de descarga del archivo
	private String filename = "archivoUno"; //nombre del archivo subido
	
	private Part file;
	
	public Part getFile() {
		return file;
	} 

	public void setFile(Part file) {
		this.file = file;
	}

	public void save() {
	    try (InputStream input = file.getInputStream()) {
	    	/*
	        Files.copy(input, new File(uploads, filename).toPath());
	    	System.out.println("se guardo el archivo---------------------------------");
			*/
	    	System.out.println("entro---------------------------------");
	    	Part partImagen = file;
	        String nombreArchivo = Paths.get(partImagen.getSubmittedFileName()).getFileName().toString();
	        System.out.println("-----------------------nombreArchivo-----------------"+nombreArchivo);
	         
	        InputStream archivoContenido = partImagen.getInputStream();
	        
	        System.out.println("algo---------------------------------");
	        
	        if (archivoContenido.available() > 0) {
	            byte[] byteArr = new byte[archivoContenido.available()];
	            archivoContenido.read(byteArr);
	            System.out.println("array---------------------------------");
	        } 
	    }
	    catch (Exception e) {
	    	System.out.println("NOOOO se guardo ----------------------------------");
	    }
	}
	
	public SubirArchivo() {}
	
	

	

	
}

	