package beans;

import java.util.Map;

/**
 * Esta clase la hice para facilitar las redirecciones con parametros
 * @author juan
 *
 */
public abstract class Redirector {
	
	/**
	 * Concatena el archivo con los parametros y le agrega unos parametros mas que son necesarios
	 * @param pagina Pagina a la que hay que redirigir
	 * @param parametros Parametros que se desean pasar
	 * @return
	 */
	public static String redirect(String pagina, String parametros) {
		String salida = pagina;
		if (parametros == null || parametros.equals("")) {
			salida += "?";
		}else if (parametros.charAt(0) != '?') {
			salida += "?";
		}
		parametros = "faces-redirect=true" + "&" + parametros;
		if (parametros.charAt(parametros.length() -1) == '&') {
			parametros = parametros.substring(0, parametros.length() - 1);
		}
		return salida + parametros;
	}
	
	public static String redirect(String pagina) {
		return redirect(pagina, "");
	}
	
	
	public static String redirect(String pagina, Map<String, String> parametros) {
		String salida = pagina;
		String strParams= "";
		
		for (Map.Entry<String, String> entry : parametros.entrySet()) {
			strParams += entry.getKey() + "=" + entry.getValue() + "&";
		}
		strParams = strParams.substring(0, strParams.length() - 1);
		
		return redirect(salida, parametros);
	}
}
