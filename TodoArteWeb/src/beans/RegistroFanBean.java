package beans;

import java.sql.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Fan;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;
import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;

@Named
@SessionScoped
public class RegistroFanBean extends SerializableLocatorImpl {
	@EJB
	FrontOfficeInterface fo;
	
	private Fan fan = new Fan();
	private String contrasenia2 = "";
	private java.util.Date fechaUtil = new java.util.Date();
	
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
	
	//--------------------------------------------------------------------------
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
