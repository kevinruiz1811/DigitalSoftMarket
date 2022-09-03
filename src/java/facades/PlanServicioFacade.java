package facades;

import entidades.PlanServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlanServicioFacade extends AbstractFacade<PlanServicio> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanServicioFacade() {
        super(PlanServicio.class);
    }    
}