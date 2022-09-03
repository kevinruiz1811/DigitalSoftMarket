package controladores;

import entidades.EstadoOrdenPrestacionServicio;
import facades.EstadoOrdenPrestacionServicioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorEstadoOrdenPrestacionServicio")
@SessionScoped
public class ControladorEstadoOrdenPrestacionServicio implements Serializable {

    public ControladorEstadoOrdenPrestacionServicio() {
    }

    private EstadoOrdenPrestacionServicio estadoOrdenPrestacionServicio;
    
    @EJB
    private EstadoOrdenPrestacionServicioFacade estadoOrdenPrestacionServicioFacade;

    public EstadoOrdenPrestacionServicio getEstadoOrdenPrestacionServicio() {
        return estadoOrdenPrestacionServicio;
    }

    public void setEstadoOrdenPrestacionServicio(EstadoOrdenPrestacionServicio estadoOrdenPrestacionServicio) {
        this.estadoOrdenPrestacionServicio = estadoOrdenPrestacionServicio;
    }
    
    @PostConstruct
    public void init(){
        estadoOrdenPrestacionServicio = new EstadoOrdenPrestacionServicio();
    }
    
    public List<EstadoOrdenPrestacionServicio> listarEstadoOrdenPrestacionServicio(){
        return estadoOrdenPrestacionServicioFacade.findAll();
    }
}
