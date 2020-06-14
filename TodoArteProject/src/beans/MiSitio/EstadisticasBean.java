package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Valoracion;
import com.TodoArte.Classes.Venta;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

import beans.FuncionesComunes;

@Named
@RequestScoped
public class EstadisticasBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	FrontOfficeInterface fo = new FrontOfficeController();
	
	private ArrayList<Contenido> lstContenidos = new ArrayList<Contenido>();
	// guardo los contenidos como Mapa porque me facilita en unas funciones tenerlo indizado
	private Map<Integer, Contenido> mapContenidos = new TreeMap<Integer, Contenido>();
	
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
	
	//-- Constructor, getters y setters -----------------------------------------------------------------
	public EstadisticasBean() {
		if (!FuncionesComunes.rolActual("artista")) {
			return;
		}
		String idArtista = FuncionesComunes.usuarioActual();
		try {
			lstContenidos = fo.obtenerContenido(idArtista, idArtista);
			for (Contenido c : lstContenidos) {
				mapContenidos.put(c.getId(), c);
			}
		} catch (Exception e) {
		}
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
	
}
