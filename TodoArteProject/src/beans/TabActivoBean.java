package beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TabActivoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int indiceTabActivo = 0;

	//********************************************************************************************************
	public String tabActivo(int indice) {
		if (indice == indiceTabActivo) {
			return "active";
		}
		return "";
	}
	public String paneActivo(int indice) {
		if (indice == indiceTabActivo) {
			return "show active";
		}
		return "";
	}
	//********************************************************************************************************
	public TabActivoBean() {
		try {
			String strTab = FuncionesComunes.getParametro("tab");
			if (strTab != null) {
				indiceTabActivo = Integer.parseInt(strTab);
			}
		} catch (Exception e) {
			indiceTabActivo = 0;
		}
	}

	public int getIndiceTabActivo() {
		return indiceTabActivo;
	}

	public void setIndiceTabActivo(int indiceTabActivo) {
		this.indiceTabActivo = indiceTabActivo;
	}
}
