package beans.MiSitio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.TodoArte.Enums.Privacidad;
import com.TodoArte.Enums.Sexo;
import com.TodoArte.Enums.TipoContenido;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.TodoArte.Classes.Artista;
import com.TodoArte.Classes.CategoriaContenido;
import com.TodoArte.Classes.Contenido;
import com.TodoArte.Classes.Fan;
import com.TodoArte.FachadeControllers.FrontOfficeController;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@SessionScoped
public class Publicar implements Serializable {

	//---atrivutos--------------------
	private static final long serialVersionUID = 1L;
	private FrontOfficeInterface fo = new FrontOfficeController();
	private List<CategoriaContenido> listaDeCategoria = new ArrayList<CategoriaContenido>();
	
	private Contenido cont = new Contenido();
	
	private int idCategoria;

	//----funciones-------------------
	public void publicarContenido(){
		CategoriaContenido catCon = fo.obtenerUnaCategoriasContenido(idCategoria);
		cont.setMiCategoria(catCon);
		cont.setTipo(null);
		cont.setArchivo(null);
		Contenido nuevoCont =  copiarContenido(cont);
	}
	
	private Contenido copiarContenido(Contenido c) {
		Contenido cont = new Contenido(0, c.getTipo(), c.getPrivacidad(), (int)c.getPrecio(), c.getDescripcion(), c.getTitulo(), c.getArchivo(), null, false, false, c.getMiCategoria());
		return cont;
	}

	//----seters getters contructor----
	
	public Privacidad[] getPrivacidad() {
		return Privacidad.values();
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public Contenido getCont() {
		return cont;
	}

	public void setCont(Contenido cont) {
		this.cont = cont;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public List<CategoriaContenido> getListaDeCategoria() {
		return listaDeCategoria;
	}

	public void setListaDeCategoria(List<CategoriaContenido> listaDeCategoria) {
		this.listaDeCategoria = listaDeCategoria;
	}
	public Publicar() {
		listaDeCategoria = fo.obtenerCategoriasContenido();
	}
}
