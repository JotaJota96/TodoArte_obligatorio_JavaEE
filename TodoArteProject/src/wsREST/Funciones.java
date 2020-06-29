package wsREST;

import java.util.ArrayList;
import java.util.Map;

import com.TodoArte.Classes.*;

public class Funciones {
	
	public static void limpiarVisibilidadesLista(ArrayList<?> lista) {
		for (Object o : lista) {
			limpiarVisibilidades(o);
		}
	}
	
	/**
	 * OJO esta funcion es recursiva...
	 * basicamente le pasas cualquier objeto, y rompe los vinculos 'Sitio -> Artista' y 'FanSigueSitio -> Fan'
	 * @param obj
	 */
	public static void limpiarVisibilidades(Object obj) {
		// Segun que tipo de objeto venga, se hace alguna cosa y se llama la recursividad
		if (obj == null) {
		} else if (obj instanceof Valoracion){
			Valoracion o = (Valoracion) obj;
		    limpiarVisibilidades(o.getMiFan());
		} else if (obj instanceof Venta){
			Venta o = (Venta) obj;
		    limpiarVisibilidades(o.getMiFan());
		} else if (obj instanceof Comentario){
		    Comentario o = (Comentario) obj;
		    limpiarVisibilidades(o.getMiFan());
		} else if (obj instanceof Reporte){
			Reporte o = (Reporte) obj;
		    limpiarVisibilidades(o.getMiFan());
		} else if (obj instanceof Artista){
			Artista a = (Artista) obj;
			a.setImagen(null);
			if (a.getMiSitio() == null) return;
			a.getMiSitio().setMiArtista(null);
			limpiarVisibilidades(a.getMiSitio());
		} else if (obj instanceof Sitio){
			Sitio s = (Sitio) obj;
			s.setImagenPortada(null);
			if (s.getMiArtista() != null) {
				limpiarVisibilidades(s.getMiArtista());
			}else {
				for (Map.Entry<Integer, Contenido> entry : s.getMisContenidos().entrySet()) {
					limpiarVisibilidades(entry.getValue());
				}
				for (Map.Entry<Integer, FanSigueSitio> entry : s.getMisFans().entrySet()) {
					limpiarVisibilidades(entry.getValue());
				}
			}
		} else if (obj instanceof Contenido){
			Contenido c = (Contenido) obj;
			c.setArchivo(null);
			for (Map.Entry<Integer, Valoracion> entry : c.getMisValoracion().entrySet()) {
				limpiarVisibilidades(entry.getValue());
			}
			for (Map.Entry<Integer, Venta> entry : c.getMisVentas().entrySet()) {
				limpiarVisibilidades(entry.getValue());
			}
			for (Map.Entry<Integer, Reporte> entry : c.getMisReporte().entrySet()) {
				limpiarVisibilidades(entry.getValue());
			}
			for (Map.Entry<Integer, Comentario> entry : c.getMisComentario().entrySet()) {
				limpiarVisibilidades(entry.getValue());
			}
		} else if (obj instanceof Fan){
			Fan f = (Fan) obj;
			f.setImagen(null);
			for (Map.Entry<Integer, FanSigueSitio> entry : f.getMisSitiosSeguidos().entrySet()) {
				limpiarVisibilidades(entry.getValue());
			}
		} else if (obj instanceof FanSigueSitio){
			FanSigueSitio fss = (FanSigueSitio) obj;
			fss.setMiFan(null);
		}
		// PD: Funciono a la primera
	}
	
}
