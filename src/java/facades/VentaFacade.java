package facades;

import entidades.Venta;
import entidades.VentaMes;
import entidades.VentaReporte;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }

    public Venta consultarVenta(Long idCotizacion) {
        try {
            Query query = em.createQuery("SELECT v FROM Venta v WHERE v.idCotizacion.idCotizacion = :idCotizacion");
            query.setParameter("idCotizacion", idCotizacion);
            return (Venta) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<VentaMes> graficoVentas() {
        try {
            List<Object[]> listaVentas;
            List<VentaMes> listaVentaMes = new ArrayList<>();
            Query query = em.createNativeQuery("CALL cantidad_ventas();");
            listaVentas = query.getResultList();
            for (Object[] venta : listaVentas) {
                VentaMes ventaMes = new VentaMes();
                ventaMes.setMes(venta[0].toString());
                ventaMes.setTotalVentas(Integer.parseInt(venta[1].toString()));
                listaVentaMes.add(ventaMes);
            }
            return listaVentaMes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<VentaReporte> reporteVentas() {
        try {
            List<VentaReporte> listaVentasReporte = new ArrayList<>();
            Query query = em.createNativeQuery("CALL reporte_ventas();");
            List<Object[]> resultado = query.getResultList();
            for (Object[] objeto : resultado) {
                VentaReporte ventaReporte = new VentaReporte();
                ventaReporte.setCantidadVentasMes(Integer.parseInt(objeto[0].toString()));
                ventaReporte.setTotalVentasMes(new BigDecimal(objeto[1].toString()));
                ventaReporte.setMes(objeto[2].toString());
                ventaReporte.setAno(objeto[3].toString());
                listaVentasReporte.add(ventaReporte);
            }
            return listaVentasReporte;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
