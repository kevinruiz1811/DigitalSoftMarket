package controladores;

import entidades.EstadoCotizacion;
import facades.EstadoCotizacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorEstadoCotizacion")
@SessionScoped
public class ControladorEstadoCotizacion implements Serializable {

    public ControladorEstadoCotizacion() {
    }

    private EstadoCotizacion estadoCotizacion;

    @EJB
    private EstadoCotizacionFacade estadoCotizacionFacade;

    public EstadoCotizacion getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(EstadoCotizacion estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    @PostConstruct
    public void init() {
        estadoCotizacion = new EstadoCotizacion();
    }

    public List<EstadoCotizacion> listarEstadoCotizacion() {
        return estadoCotizacionFacade.findAll();
    }
}