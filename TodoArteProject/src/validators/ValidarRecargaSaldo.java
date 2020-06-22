package validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class ValidarRecargaSaldo implements Validator<String> {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, String strSaldo) throws ValidatorException {
		float saldo;
		try {
			saldo = Float.parseFloat(strSaldo);
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage("Formato incorrecto"));
		}
		
		if (saldo <= 0) {
			throw new ValidatorException(new FacesMessage("El monto debe ser mayor a cero"));
		}
	}

	public void validar(FacesContext arg0, UIComponent arg1, Float saldo) throws ValidatorException {
		if (saldo <= 0) {
			throw new ValidatorException(new FacesMessage("El monto debe ser mayor a cero"));
		}
	}

}
