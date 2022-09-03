package controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@Named(value = "controladorLenguaje")
@SessionScoped
public class ControladorLenguaje implements Serializable {

    public ControladorLenguaje() {
    }

    private Locale lenguajeSeleccionado;
    private Locale ingles;
    private Locale espanol;

    public Locale getLenguajeSeleccionado() {
        return lenguajeSeleccionado;
    }

    public void setLenguajeSeleccionado(Locale lenguajeSeleccionado) {
        this.lenguajeSeleccionado = lenguajeSeleccionado;
    }

    public Locale getIngles() {
        return ingles;
    }

    public void setIngles(Locale ingles) {
        this.ingles = ingles;
    }

    public Locale getEspanol() {
        return espanol;
    }

    public void setEspanol(Locale espanol) {
        this.espanol = espanol;
    }

    @PostConstruct
    public void init() {
        lenguajeSeleccionado = new Locale("es");
        ingles = new Locale("en");
        espanol = new Locale("es");
    }

    public String cambiarIdioma(String idiomas) {
        Locale idioma = new Locale(idiomas);
        if (idioma != null) {
            this.lenguajeSeleccionado = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(lenguajeSeleccionado);
        }
        return "";
    }
}