package facades;

import entidades.DetalleVenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleVentaFacade extends AbstractFacade<DetalleVenta> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleVentaFacade() {
        super(DetalleVenta.class);
    }

    public DetalleVenta consultarDetalleVenta(String numeroContrato) {
        try {
            Query query = em.createQuery("SELECT d FROM DetalleVenta d WHERE d.numeroContrato.numeroContrato = :numeroContrato");
            query.setParameter("numeroContrato", numeroContrato);
            return (DetalleVenta) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
