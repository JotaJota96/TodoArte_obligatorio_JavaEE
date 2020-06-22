package wsREST;

public class DtLogin {
	
	private String idUsuario;
	private String contrasenia;
	
	public DtLogin() {};
	
	public DtLogin(String idUsuario, String contrasenia) {
		this.idUsuario = idUsuario;
		this.contrasenia = contrasenia;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
