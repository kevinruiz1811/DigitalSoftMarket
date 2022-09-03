package facades;

import entidades.AsignacionEmpleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AsignacionEmpleadoFacade extends AbstractFacade<AsignacionEmpleado> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignacionEmpleadoFacade() {
        super(AsignacionEmpleado.class);
    }

    public List<AsignacionEmpleado> consultarCampañaEmpleado(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT o FROM AsignacionEmpleado o WHERE o.idEmpleado.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
