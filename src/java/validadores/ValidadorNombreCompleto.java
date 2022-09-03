package validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validadorNombreCompleto")
public class ValidadorNombreCompleto implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String objeto = (String) value;
        if (objeto != null && !objeto.equals("")) {
            if (objeto.length() <= 50 && objeto.length() >= 3) {

                String patron = "^[A-Za-zÁ-ÿ\\u00f1\\u00d1]+(\\s[A-Za-zÁ-ÿ\\u00f1\\u00d1]+){2,}";
                boolean test = Pattern.matches(patron, objeto);
                if (!test) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Nombre Completo", "Los caracteres no son validos"));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Nombre Completo", "Solo se permiten entre 3 a 50 caracteres"));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Nombre Completo", "Debe escribir el Nombre Completo"));
        }
    }
}
