package controladores;

import entidades.TipoPrensa;
import facades.TipoPrensaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorTipoPrensa")
@SessionScoped
public class ControladorTipoPrensa implements Serializable {

    public ControladorTipoPrensa() {
    }

    private TipoPrensa tipoPrensa;

    @EJB
    private TipoPrensaFacade tipoPrensaFacade;

    public TipoPrensa getTipoPrensa() {
        return tipoPrensa;
    }

    public void setTipoPrensa(TipoPrensa tipoPrensa) {
        this.tipoPrensa = tipoPrensa;
    }

    @PostConstruct
    public void init() {
        tipoPrensa = new TipoPrensa();
    }

    public List<TipoPrensa> listarTipoPrensa() {
        return tipoPrensaFacade.findAll();
    }
}