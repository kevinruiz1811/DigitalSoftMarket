package controladores;

import entidades.TipoCliente;
import facades.TipoClienteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorTipoCliente")
@SessionScoped
public class ControladorTipoCliente implements Serializable {
    
    public ControladorTipoCliente() {
    }

    private TipoCliente tipoCliente;

    @EJB
    private TipoClienteFacade tipoClienteFacade;

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @PostConstruct
    public void init() {
        tipoCliente = new TipoCliente();
    }

    public List<TipoCliente> listarTipoCliente() {
        return tipoClienteFacade.findAll();
    }
}
