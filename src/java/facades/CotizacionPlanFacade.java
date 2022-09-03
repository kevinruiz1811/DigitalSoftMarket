package facades;

import entidades.CotizacionPlan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CotizacionPlanFacade extends AbstractFacade<CotizacionPlan> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionPlanFacade() {
        super(CotizacionPlan.class);
    }   
}