package controladores;

import entidades.Cotizacion;
import entidades.VentaReporte;
import entidades.DetalleCotizacion;
import entidades.DetalleVenta;
import entidades.EstadoCotizacion;
import entidades.Usuario;
import entidades.Venta;
import entidades.VentaMes;
import facades.DetalleCotizacionFacade;
import facades.DetalleVentaFacade;
import facades.VentaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named(value = "controladorVenta")
@SessionScoped
public class ControladorVenta implements Serializable {

    public ControladorVenta() {
    }

    private Venta venta;
    private List<DetalleVenta> buscarVenta;
    private VentaMes ventaMes;
    private DetalleVenta detalleVenta;
    private Cotizacion cotizacion;
    private DetalleCotizacion detalleCotizacion;
    private EstadoCotizacion estadoCotizacion;
    private Usuario usuario;
    private BarChartModel barModel;
    private List<VentaReporte> listaVentas;

    @EJB
    private VentaFacade ventaFacade;
    @EJB
    private DetalleVentaFacade detalleVentaFacade;
    @EJB
    private DetalleCotizacionFacade detalleCotizacionFacade;

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<DetalleVenta> getBuscarVenta() {
        return buscarVenta;
    }

    public void setBuscarVenta(List<DetalleVenta> buscarVenta) {
        this.buscarVenta = buscarVenta;
    }

    public VentaMes getVentaMes() {
        return ventaMes;
    }

    public void setVentaMes(VentaMes ventaMes) {
        this.ventaMes = ventaMes;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public DetalleCotizacion getDetalleCotizacion() {
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
    }

    public EstadoCotizacion getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(EstadoCotizacion estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    @PostConstruct
    public void init() {
        venta = new Venta();
        buscarVenta = new ArrayList<>();
        detalleVenta = new DetalleVenta();
        cotizacion = new Cotizacion();
        detalleCotizacion = new DetalleCotizacion();
        estadoCotizacion = new EstadoCotizacion();
        usuario = new Usuario();
        createBarModel();
        listaVentas = new ArrayList<>();
    }

    public void limpiarDetalleVenta() {
        venta = new Venta();
        detalleVenta = new DetalleVenta();
    }

    public String venta(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
        return "/Venta/registrarVenta?faces-redirect=true";
    }

    public void registrarVenta() {
        venta.setNumeroContrato(venta.getNumeroContrato());
        cotizacion.setIdCotizacion(detalleCotizacion.getIdCotizacion().getIdCotizacion());
        venta.setIdCotizacion(cotizacion);
        ventaFacade.create(venta);
    }

    public void registrarDetalleVenta() {
        Date fecha = new Date();
        try {
            registrarVenta();
            detalleVenta.setNumeroContrato(venta);
            detalleVenta.setFechaRegistro(fecha);
            detalleVentaFacade.create(detalleVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro la Venta exitosamente."));
            limpiarDetalleVenta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar la Venta."));
        }
    }

    public List<DetalleVenta> listarVentas() {
        return detalleVentaFacade.findAll();
    }

    public void obtenerDatosDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
        this.venta = detalleVenta.getNumeroContrato();
        this.cotizacion = detalleVenta.getNumeroContrato().getIdCotizacion();
        DetalleCotizacion obtenerCotizacion = detalleCotizacionFacade.consultarDetalleCotizacion(cotizacion.getIdCotizacion());
        this.detalleCotizacion = obtenerCotizacion;
        this.estadoCotizacion = detalleVenta.getNumeroContrato().getIdCotizacion().getIdEstadoCotizacion();
        this.usuario = detalleVenta.getNumeroContrato().getIdCotizacion().getIdCliente().getNumeroIdentificacion();
    }

    public String consultarDetalleVenta(DetalleVenta detalleVenta) {
        obtenerDatosDetalleVenta(detalleVenta);
        return "consultarVenta?faces-redirect=true";
    }

    public String preEditarDetalleVenta(DetalleVenta detalleVenta) {
        obtenerDatosDetalleVenta(detalleVenta);
        return "actualizarVenta?faces-redirect=true";
    }

    public void editarDetalleVenta() {
        try {
            detalleVenta.setIdDetalleVenta(detalleVenta.getIdDetalleVenta());
            detalleVentaFacade.edit(detalleVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Venta actualizada"));
            limpiarDetalleVenta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo actualizar"));
        }
    }

    //Se inicializa los parámetros y labels que tendra el grafico
    private void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("Ventas Mensuales");
        barModel.setAnimate(true);
        barModel.setLegendPosition("ne");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mes");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total Ventas");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    //Se obtiene los valores del objeto por medio de la consulta al facade para graficar las ventas
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries ventas = new ChartSeries();
        ventas.setLabel("Ventas");
        List<VentaMes> listaVentaMes = ventaFacade.graficoVentas();
        for (VentaMes venta : listaVentaMes) {
            ventas.set(venta.getMes(), venta.getTotalVentas());
        }
        model.addSeries(ventas);
        return model;
    }

    public String deshabilitarVenta(DetalleCotizacion detalleCotizacion) {
        Venta consultarVenta = ventaFacade.consultarVenta(detalleCotizacion.getIdCotizacion().getIdCotizacion());
        try {
            if (consultarVenta == null) {
                return "false";
            } else {
                return "true";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<VentaReporte> consultarReporteVenta() {
        listaVentas = ventaFacade.reporteVentas();
        return listaVentas;
    }

    public void reporteVenta(ActionEvent actionEvent) throws JRException {
        consultarReporteVenta();
        try {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaVentas);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
            JasperPrint jasperPrint = JasperFillManager.fillReport(ruta + "//ventas.jasper", new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename = Resultado_Ventas.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
