package facades;

import entidades.ClienteMes;
import entidades.Cotizacion;
import entidades.CotizacionReporte;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CotizacionFacade extends AbstractFacade<Cotizacion> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionFacade() {
        super(Cotizacion.class);
    }

    public List<CotizacionReporte> reporteCotizaciones() {
        try {
            List<CotizacionReporte> listaResultados = new ArrayList<>();
            Query query = em.createNativeQuery("CALL reporte_cotizaciones();");
            List<Object[]> resultado = query.getResultList();
            for (Object[] objeto : resultado) {
                CotizacionReporte cotizacion = new CotizacionReporte();
                cotizacion.setCantidadCotizacionesMes(Integer.parseInt(objeto[0].toString()));
                cotizacion.setTotalCotizacionesMes(new BigDecimal(objeto[1].toString()));
                cotizacion.setMes(objeto[2].toString());
                cotizacion.setAno(objeto[3].toString());
                listaResultados.add(cotizacion);
            }
            return listaResultados;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<ClienteMes> consultarClienteMes() {
        List<Object[]> listaClientes;
        List<ClienteMes> listaClienteMes = new ArrayList<>();
        Query query = em.createNativeQuery("SELECT SUM(detallecotizacion.precioTotalPlanes), usuario.nombres FROM usuario INNER JOIN cliente ON (usuario.numeroIdentificacion=cliente.numeroIdentificacion) INNER JOIN cotizacion ON (cliente.idCliente=cotizacion.idCliente) INNER JOIN detallecotizacion ON (cotizacion.idCotizacion=detallecotizacion.idCotizacion) WHERE detallecotizacion.fechaRegistro ORDER BY detallecotizacion.precioTotalPlanes DESC LIMIT 0, 5;");
        listaClientes = query.getResultList();
        for (Object[] cliente : listaClientes) {
            ClienteMes clienteMes = new ClienteMes();
            clienteMes.setPrecioPlanesTotal((int) Double.parseDouble(cliente[0].toString()));
            clienteMes.setNombreCliente(cliente[1].toString());
            listaClienteMes.add(clienteMes);
        }
        return listaClienteMes;
    }

    public List<Cotizacion> consultarCotizacionesCliente(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT o FROM Cotizacion o WHERE o.idCliente.numeroIdentificacion.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
