package validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validadorNombreUsuario")
public class ValidadorNombreUsuario implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String objeto = (String) value;
        if (objeto != null && !objeto.equals("")) {
            if (objeto.length() <= 30 && objeto.length() >= 2) {

                String patron = "^[Á-ÿ\\u00f1\\u00d1\\w\\.\\@\\-]+(\\s[Á-ÿ\\u00f1\\u00d1\\w\\.\\@\\-]+){0,}";
                boolean test = Pattern.matches(patron, objeto);
                if (!test) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Nombre Usuario", "Los caracteres no son validos"));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Nombre Usuario", "Solo se permiten entre 2 a 30 caracteres"));
            }
        }
    }
}