package facades;

import entidades.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public Empleado consultarDocumento(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT e FROM Empleado e WHERE e.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return (Empleado) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
