package beans.backoffice;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.CategoriaSitio;
import com.TodoArte.FachadeInterfaces.BackOfficeInterface;

import beans.Redirector;

@Named
@SessionScoped
public class DefinirCategoria implements Serializable {
	
	@EJB
	BackOfficeInterface bo;
	
	private CategoriaContenido catCont = new CategoriaContenido();
	private CategoriaSitio catSit = new CategoriaSitio();
	
	public String definirCategoriaContenido() {
		try {
			
			bo.agregarCategoriaDeContenido(catCont);
			catCont = new CategoriaContenido();
		} catch (Exception e) {
		}
		
		return Redirector.redirect("DefinirCategoria.jsf");
	}
	
	public String definirCategoriaSitio() {
		try {
			bo.agregarCategoriaDeSitio(catSit);
			catSit = new CategoriaSitio();
		} catch (Exception e) {
		}
		return Redirector.redirect("DefinirCategoria.jsf");
	}
	
	public CategoriaContenido getCatCont() {
		return catCont;
	}

	public void setCatCont(CategoriaContenido catCont) {
		this.catCont = catCont;
	}

	public CategoriaSitio getCatSit() {
		return catSit;
	}

	public void setCatSit(CategoriaSitio catSit) {
		this.catSit = catSit;
	}
	
}
