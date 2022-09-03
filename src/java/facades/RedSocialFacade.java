package facades;

import entidades.RedSocial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RedSocialFacade extends AbstractFacade<RedSocial> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RedSocialFacade() {
        super(RedSocial.class);
    }   
}