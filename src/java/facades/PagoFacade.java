package facades;

import entidades.Pago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PagoFacade extends AbstractFacade<Pago> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }

    public Pago consultarPago(Long idOrdenVenta) {
        try {
            Query query = em.createQuery("SELECT p FROM Pago p WHERE p.idOrdenVenta.idOrdenVenta = :idOrdenVenta");
            query.setParameter("idOrdenVenta", idOrdenVenta);
            return (Pago) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Pago> consultarPagoCliente(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT p FROM Pago p WHERE p.idOrdenVenta.numeroContrato.idCotizacion.idCliente.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
