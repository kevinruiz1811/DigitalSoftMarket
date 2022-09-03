package controladores;

import entidades.EstadoUsuario;
import facades.EstadoUsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorEstadoUsuario")
@SessionScoped
public class ControladorEstadoUsuario implements Serializable {

    public ControladorEstadoUsuario() {
    }

    private EstadoUsuario estadoUsuario;

    @EJB
    private EstadoUsuarioFacade estadoUsuarioFacade;

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    @PostConstruct
    public void init() {
        estadoUsuario = new EstadoUsuario();
    }

    public List<EstadoUsuario> listarEstadoUsuario() {
        return estadoUsuarioFacade.findAll();
    }
}