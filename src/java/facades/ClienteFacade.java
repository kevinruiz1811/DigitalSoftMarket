package facades;

import entidades.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public Cliente consultarDocumento(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return (Cliente) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Cliente consultarNit(String nit) {
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nit = :nit");
            query.setParameter("nit", nit);
            return (Cliente) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Cliente consultarNombreEmpresa(String nombreEmpresa) {
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombreEmpresa = :nombreEmpresa");
            query.setParameter("nombreEmpresa", nombreEmpresa);
            return (Cliente) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
