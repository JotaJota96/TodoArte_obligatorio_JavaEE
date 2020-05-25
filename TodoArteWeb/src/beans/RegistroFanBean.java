package beans;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.sql.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RegistroFanBean implements Serializable {
	@EJB
	FrontOfficeInterface fo;
	
	private Fan fan = new Fan();
	private String contrasenia2 = "";
	private java.util.Date fechaUtil = new java.util.Date();
	
	private Part file;
	
	//--------------------------------------------------------------------------
	public void registrar() {
		fan.setFechaNac(new Date(fechaUtil.getTime()));
		fan.getFechaNac().setDate(fan.getFechaNac().getDate() + 1);
		
		if ( ! contrasenia2.equals(fan.getContrasenia())) {
			// las contrasenias no coinciden, error
		}
		
		Fan ret = null;
		try {
			ret = fo.registrarUsuarioFan(fan);
		} catch (Exception e) {
		}
		
		if (ret == null) {
			// no se pudo registrar el fan
		}else {
			// fan registrado exitosamente
		}
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
	            fan.setImagen(byteArr);
	        } else {
	        	fan.setImagen(null);
	        }
	    }
	    catch (Exception e) {
	    	System.out.println("NOOOO se guardo ----------------------------------");
	    	fan.setImagen(null);
	    }
	}
	//--------------------------------------------------------------------------
	public Part getFile() {
		return file;
	} 

	public void setFile(Part file) {
		this.file = file;
	}
	
	public RegistroFanBean() {
	}
	
	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
	}

	public String getContrasenia2() {
		return contrasenia2;
	}

	public void setContrasenia2(String contrasenia2) {
		this.contrasenia2 = contrasenia2;
	}
	
	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public java.util.Date getFechaUtil() {
		return fechaUtil;
	}

	public void setFechaUtil(java.util.Date fechaUtil) {
		this.fechaUtil = fechaUtil;
	}
	
}
