package facades;

import entidades.ClienteOrdenVenta;
import entidades.OrdenVenta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class OrdenVentaFacade extends AbstractFacade<OrdenVenta> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenVentaFacade() {
        super(OrdenVenta.class);
    }

    public List<ClienteOrdenVenta> consultarDatosCliente() {
        try {
            List<ClienteOrdenVenta> listaResultados = new ArrayList<>();
            Query query = em.createNativeQuery("CALL envio_orden_venta();");
            List<Object[]> resultado = query.getResultList();
            for (Object[] objeto : resultado) {
                ClienteOrdenVenta cliente = new ClienteOrdenVenta();
                cliente.setNombres(objeto[0].toString());
                cliente.setApellidos(objeto[1].toString());
                cliente.setCorreoElectronico(objeto[2].toString());
                cliente.setNumeroContrato(objeto[3].toString());
                cliente.setCuota(new BigDecimal(objeto[4].toString()));
                cliente.setIdOrdenVenta(Integer.parseInt(objeto[5].toString()));
                cliente.setFechaPago(objeto[6].toString());
                listaResultados.add(cliente);
            }
            return listaResultados;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void editarEstadoEnvioOrdenVenta(int idOrdenVenta) {
        try {
            Query query = em.createNativeQuery("UPDATE ordenventa SET estadoEnvioOrdenVenta = 'Pagada' WHERE idOrdenVenta = " + idOrdenVenta + ";");
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<OrdenVenta> consultarOrdenVentaCliente(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT o FROM OrdenVenta o WHERE o.numeroContrato.idCotizacion.idCliente.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
