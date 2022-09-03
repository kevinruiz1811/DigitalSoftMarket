package facades;

import entidades.TipoPrensa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoPrensaFacade extends AbstractFacade<TipoPrensa> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPrensaFacade() {
        super(TipoPrensa.class);
    }
}