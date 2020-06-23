package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.FanSigueSitio;
import com.TodoArte.Classes.PagoAPlataforma;
import com.TodoArte.Classes.Sitio;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.Classes.Venta;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;
import beans.Redirector;

@Named
@RequestScoped
public class EstadisticasBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	FrontOfficeInterface fo = new FrontOfficeController();
	
	private ArrayList<Contenido> lstContenidos = new ArrayList<Contenido>();
	// guardo los contenidos como Mapa porque me facilita en unas funciones tenerlo indizado
	private Map<Integer, Contenido> mapContenidos = new TreeMap<Integer, Contenido>();
	private boolean permitirPagar = false;
	private String  ultimoPago = "";
	private float porcentaje1;
	private float porcentaje2;
	
	//-- Funciones --------------------------------------------------------------------------------------
	public String calcularIngresos(int id) {
		float ret = 0;
		for (Map.Entry<Integer, Venta> entry : mapContenidos.get(id).getMisVentas().entrySet()) {
			ret += entry.getValue().getPrecio();
		}
		return String.valueOf(ret);
	}
	public String bloqueadoSiNo(int id) {
		if (mapContenidos.get(id).getBloqueado()) {
			return "si";
		}else {
			return "no";
		}
	}
	
	public String pagarAPlataforma() {
		try {
			fo.pagarAPlataforma(FuncionesComunes.usuarioActual());
			return Redirector.redirect("sitio-administrar.jsf", "tab=5");
		} catch (Exception e) {
			return Redirector.redirect("500.jsf");
		}
	}
	
	public void calcularPorcentajes() {
		Artista art = (Artista)fo.obtenerDatosUsuario(FuncionesComunes.usuarioActual());
		Sitio s = art.getMiSitio();
		
		float contador = 0;
		for (Map.Entry<Integer, FanSigueSitio> entry : s.getMisFans().entrySet()) {
			if (entry.getValue().getPremiun()) {
				contador++;
			}
		}
		float isp = contador * s.getPrecioPremium(); //ingresos por subscriptores primium
		
		float icv = 0; //ingresos por contenido vendido
		for (Map.Entry<Integer, Contenido> entry : s.getMisContenidos().entrySet()) {
			for (Map.Entry<Integer, Venta> entrada : entry.getValue().getMisVentas().entrySet()) {
				if (entrada.getValue().getFechaYHora().compareTo(FuncionesComunes.haceUnMes()) >= 0) {
					icv += entrada.getValue().getPrecio();
				}
			}
		}
		
		if(icv+isp==0) {
			porcentaje1=0;
			porcentaje2=0;
		}
		else {
			porcentaje1 = ((icv*100)/(icv+isp));
			porcentaje2 = ((isp*100)/(icv+isp));
		}
		
	}
	
	//-- Constructor, getters y setters -----------------------------------------------------------------
	
	public EstadisticasBean() {
		calcularPorcentajes();
		if (!FuncionesComunes.rolActual("artista")) {
			return;
		}
		String idArtista = FuncionesComunes.usuarioActual();
		try {
			lstContenidos = fo.obtenerContenido(idArtista, idArtista);
			for (Contenido c : lstContenidos) {
				mapContenidos.put(c.getId(), c);
			}
			
			Artista a = (Artista) fo.obtenerDatosUsuario(idArtista);
			PagoAPlataforma ultimoPago =  a.obtenerUltimoPago();
			if (ultimoPago != null) {
				if (ultimoPago.getFechaYHora().compareTo(FuncionesComunes.haceUnMes()) > 0) {
				}else {
					permitirPagar = true;
				}
				this.ultimoPago = "Último pago: " + ultimoPago.getFechaYHora() + ".";
			}else {
				this.ultimoPago = "Aun no has realizado ningun pago";
				permitirPagar = true;
			}
		} catch (Exception e) {
		}
	}
	public float getPorcentaje1() {
		return porcentaje1;
	}
	public void setPorcentaje1(float porcentaje1) {
		this.porcentaje1 = porcentaje1;
	}
	public float getPorcentaje2() {
		return porcentaje2;
	}
	public void setPorcentaje2(float porcentaje2) {
		this.porcentaje2 = porcentaje2;
	}
	public Map<Integer, Contenido> getMapContenidos() {
		return mapContenidos;
	}
	public void setMapContenidos(Map<Integer, Contenido> mapContenidos) {
		this.mapContenidos = mapContenidos;
	}
	public ArrayList<Contenido> getLstContenidos() {
		return lstContenidos;
	}
	public void setLstContenidos(ArrayList<Contenido> lstContenidos) {
		this.lstContenidos = lstContenidos;
	}
	public boolean getPermitirPagar() {
		return permitirPagar;
	}
	public void setPermitirPagar(boolean permitirPagar) {
		this.permitirPagar = permitirPagar;
	}
	public String getUltimoPago() {
		return ultimoPago;
	}
	public void setUltimoPago(String ultimoPago) {
		this.ultimoPago = ultimoPago;
	}
	
	
}
