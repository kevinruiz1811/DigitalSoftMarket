package facades;

import entidades.TipoNicho;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoNichoFacade extends AbstractFacade<TipoNicho> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoNichoFacade() {
        super(TipoNicho.class);
    }
}