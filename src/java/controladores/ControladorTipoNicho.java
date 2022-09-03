package controladores;

import entidades.TipoNicho;
import facades.TipoNichoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorTipoNicho")
@SessionScoped
public class ControladorTipoNicho implements Serializable {

    public ControladorTipoNicho() {
    }

    private TipoNicho tipoNicho;

    @EJB
    private TipoNichoFacade tipoNichoFacade;

    public TipoNicho getTipoNicho() {
        return tipoNicho;
    }

    public void setTipoNicho(TipoNicho tipoNicho) {
        this.tipoNicho = tipoNicho;
    }

    @PostConstruct
    public void init() {
        tipoNicho = new TipoNicho();
    }

    public List<TipoNicho> listarTipoNicho() {
        return tipoNichoFacade.findAll();
    }
}
