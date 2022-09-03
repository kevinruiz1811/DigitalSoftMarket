package facades;

import entidades.DetalleCotizacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DetalleCotizacionFacade extends AbstractFacade<DetalleCotizacion> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCotizacionFacade() {
        super(DetalleCotizacion.class);
    }

    public List<DetalleCotizacion> consultarCotizacionesCliente(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT d FROM DetalleCotizacion d WHERE d.idCotizacion.idCliente.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public DetalleCotizacion consultarDetalleCotizacion(Long idCotizacion) {
        try {
            Query query = em.createQuery("SELECT d FROM DetalleCotizacion d WHERE d.idCotizacion.idCotizacion = :idCotizacion");
            query.setParameter("idCotizacion", idCotizacion);
            return (DetalleCotizacion) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
