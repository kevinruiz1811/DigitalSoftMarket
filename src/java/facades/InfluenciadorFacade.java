package facades;

import entidades.Influenciador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InfluenciadorFacade extends AbstractFacade<Influenciador> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InfluenciadorFacade() {
        super(Influenciador.class);
    }

    public Influenciador consultarDocumento(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT i FROM Influenciador i WHERE i.idEmpleado.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return (Influenciador) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
