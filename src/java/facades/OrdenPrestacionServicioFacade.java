package facades;

import entidades.OrdenPrestacionServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrdenPrestacionServicioFacade extends AbstractFacade<OrdenPrestacionServicio> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenPrestacionServicioFacade() {
        super(OrdenPrestacionServicio.class);
    }    
}