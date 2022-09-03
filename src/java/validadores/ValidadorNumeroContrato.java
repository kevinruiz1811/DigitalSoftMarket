package validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validadorNumeroContrato")
public class ValidadorNumeroContrato implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String objeto = (String) value;
        if (objeto != null && !objeto.equals("")) {
            if (objeto.length() <= 10 && objeto.length() >= 5) {

                String patron = "^[0-9]{5,10}$";
                boolean test = Pattern.matches(patron, objeto);
                if (!test) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Número Contrato", "Los caracteres no son validos"));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Número Contrato", "Solo se permiten entre 5 a 10 dígitos"));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Número Contrato", "Debe escrbir el Número de Contrato"));
        }
    }
}
