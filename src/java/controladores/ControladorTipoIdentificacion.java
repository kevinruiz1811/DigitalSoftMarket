package controladores;

import entidades.TipoIdentificacion;
import facades.TipoIdentificacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorTipoIdentificacion")
@SessionScoped
public class ControladorTipoIdentificacion implements Serializable {

    public ControladorTipoIdentificacion() {
    }

    private TipoIdentificacion tipoIdentificacion;

    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @PostConstruct
    public void init() {
        tipoIdentificacion = new TipoIdentificacion();
    }

    public List<TipoIdentificacion> listarTipoIdentificacion() {
        return tipoIdentificacionFacade.findAll();
    }
}
