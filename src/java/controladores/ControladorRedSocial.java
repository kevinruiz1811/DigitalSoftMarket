package controladores;

import entidades.RedSocial;
import facades.RedSocialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorRedSocial")
@SessionScoped
public class ControladorRedSocial implements Serializable {

    public ControladorRedSocial() {
    }

    private RedSocial redSocial;

    @EJB
    private RedSocialFacade redSocialFacade;

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    @PostConstruct
    public void init() {
        redSocial = new RedSocial();
    }

    public List<RedSocial> listarRedSocial() {
        return redSocialFacade.findAll();
    }
}
