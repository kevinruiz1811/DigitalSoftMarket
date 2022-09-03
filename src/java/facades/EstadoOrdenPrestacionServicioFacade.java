package facades;

import entidades.EstadoOrdenPrestacionServicio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoOrdenPrestacionServicioFacade extends AbstractFacade<EstadoOrdenPrestacionServicio> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoOrdenPrestacionServicioFacade() {
        super(EstadoOrdenPrestacionServicio.class);
    }   
}