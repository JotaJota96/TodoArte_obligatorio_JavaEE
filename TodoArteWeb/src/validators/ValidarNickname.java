package validators;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import com.TodoArte.Classes.Usuario;
import com.TodoArte.FachadeInterfaces.FrontOfficeInterface;

@Named
@RequestScoped
public class ValidarNickname implements Validator<String> {
	
	@EJB
	FrontOfficeInterface fo;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, String arg2) throws ValidatorException {
		String nick = arg2;
		Usuario f = null;
		
		try {
			f = fo.obtenerDatosUsuario(nick);
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("No se pudo validar el nickname"));
		}
		
        if (f != null) {
            throw new ValidatorException(new FacesMessage("Ya existe un usuario con ese nickname"));
        }
	}
	
}
