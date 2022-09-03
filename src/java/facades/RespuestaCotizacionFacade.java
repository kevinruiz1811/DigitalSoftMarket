package facades;

import entidades.RespuestaCotizacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RespuestaCotizacionFacade extends AbstractFacade<RespuestaCotizacion> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaCotizacionFacade() {
        super(RespuestaCotizacion.class);
    }

    public List<RespuestaCotizacion> consultarCotizacionesCliente(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT r FROM RespuestaCotizacion r WHERE r.idCotizacion.idCliente.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
