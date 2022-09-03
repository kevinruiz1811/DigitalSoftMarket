package facades;

import entidades.Prensa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PrensaFacade extends AbstractFacade<Prensa> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrensaFacade() {
        super(Prensa.class);
    }

    public Prensa consultarDocumento(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT p FROM Prensa p WHERE p.idEmpleado.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return (Prensa) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
