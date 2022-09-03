package facades;

import entidades.EstadoCotizacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoCotizacionFacade extends AbstractFacade<EstadoCotizacion> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoCotizacionFacade() {
        super(EstadoCotizacion.class);
    }
}