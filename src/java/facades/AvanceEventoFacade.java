package facades;

import entidades.AvanceEvento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AvanceEventoFacade extends AbstractFacade<AvanceEvento> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvanceEventoFacade() {
        super(AvanceEvento.class);
    }

    public List<AvanceEvento> consultarAvanceEmpleado(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT o FROM AvanceEvento o WHERE o.idAsignacionEmpleado.idEmpleado.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
