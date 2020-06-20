package beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RegistroFanBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private FrontOfficeInterface fo = new FrontOfficeController();
	
	private Fan fan = new Fan();
	private String contrasenia2 = "";
	private java.util.Date fechaUtil = new java.util.Date();
	private Part file;
	
	//--------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public String registrar() {
		try {
			fan.setFechaNac(FuncionesComunes.utilDateToSqlDate(fechaUtil));
			fan.getFechaNac().setDate(fan.getFechaNac().getDate() + 1);
	
			Fan nuevoFan = copiarFan(fan);
			
			if ( ! contrasenia2.equals(nuevoFan.getContrasenia())) {
				// las contrasenias no coinciden, error
			}
			nuevoFan.setImagen(FuncionesComunes.partToBytes(file));
			
			Fan ret = null;
			ret = fo.registrarUsuarioFan(nuevoFan);

			if (ret == null) {
				return Redirector.redirect("500.jsf");
			}else {
				return Redirector.redirect("login.jsf");
			}
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	
	private Fan copiarFan(Fan f) {
		Fan copia = new Fan();
		copia.setNikname(f.getNikname());
		copia.setContrasenia(f.getContrasenia());
		copia.setCorreo(f.getCorreo());
		copia.setImagen(f.getImagen());
		copia.setNombre(f.getNombre());
		copia.setApellido(f.getApellido());
		copia.setFechaNac(f.getFechaNac());
		copia.setUbicacion(f.getUbicacion());
		copia.setSexo(f.getSexo());
		return copia;
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
