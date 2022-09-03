package controladores;

import entidades.TipoEmpleado;
import facades.TipoEmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorTipoEmpleado")
@SessionScoped
public class ControladorTipoEmpleado implements Serializable {

    public ControladorTipoEmpleado() {
    }

    private TipoEmpleado tipoEmpleado;

    @EJB
    private TipoEmpleadoFacade tipoEmpleadoFacade;

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @PostConstruct
    public void init() {
        tipoEmpleado = new TipoEmpleado();
    }

    public List<TipoEmpleado> listarTipoEmpleado() {
        return tipoEmpleadoFacade.findAll();
    }
}