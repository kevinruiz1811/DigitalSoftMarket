package validadores;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validadorDescripcion")
public class ValidadorDescripcion implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String objeto = (String) value;
        if (objeto != null && !objeto.equals("")) {
            if (objeto.length() <= 500 && objeto.length() >= 10) {

                String patron = "^[Á-ÿ\\u00f1\\u00d1\\w\\,\\.]+(\\s[Á-ÿ\\u00f1\\u00d1\\w\\,\\.]+){0,}";
                boolean test = Pattern.matches(patron, objeto);
                if (!test) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Descripción", "Los caracteres no son validos"));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo Descripción", "Solo se permiten entre 10 a 500 caracteres"));
            }
        } else {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Descripción", "Debe escribir la Descripción"));
        }
    }
}
