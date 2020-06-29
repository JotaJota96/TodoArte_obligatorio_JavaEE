package beans.backoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Reporte;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class RevisarContenido  implements Serializable {

	private FrontOfficeInterface fo = new FrontOfficeController();
	
	private ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
	private ArrayList<Reporte> reportes = new ArrayList<Reporte>();
	
	//***************************************************************************************************
	public void obtenerReportes(AjaxBehaviorEvent  event) {
		try {
			// Para obtener el ID del contenido
		    UIComponent component = event.getComponent();
		    int idContenido = (int) component.getAttributes().get("idx");
		    
		    // Vacio la lista de reportes
			reportes.clear();
			// recorro los contenidos buscando al que tenga ese ID
			for (Contenido c : contenidos) {
				if (c.getId() == idContenido) {
					// Cuando encuentro al contenido que tiene ese ID
					// Recorro todos los reportes y los paso al array de reportes
					for (Map.Entry<Integer, Reporte> entry : c.getMisReporte().entrySet()) {
						reportes.add(entry.getValue());
					}
				}
			}

		} catch (Exception e) {
			reportes.clear();
		}
	}

	//***************************************************************************************************
	public RevisarContenido() {
		ArrayList<Contenido> todosLosContenidos = fo.listarContenido();
		for (Contenido c : todosLosContenidos) {
			if (c.getMisReporte().size() > 0) {
				contenidos .add(c);
			}
		}
	}
	public ArrayList<Contenido> getContenidos() {
		return contenidos;
	}
	public void setContenidos(ArrayList<Contenido> contenidos) {
		this.contenidos = contenidos;
	}
	public ArrayList<Reporte> getReportes() {
		return reportes;
	}
	public void setReportes(ArrayList<Reporte> reportes) {
		this.reportes = reportes;
	}
	
}
 
