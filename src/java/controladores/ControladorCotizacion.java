package controladores;

import entidades.Cliente;
import entidades.ClienteMes;
import entidades.Cotizacion;
import entidades.CotizacionPlan;
import entidades.CotizacionReporte;
import entidades.DetalleCotizacion;
import entidades.EstadoCotizacion;
import entidades.Mail;
import entidades.RespuestaCotizacion;
import entidades.Usuario;
import facades.CotizacionFacade;
import facades.DetalleCotizacionFacade;
import facades.EstadoCotizacionFacade;
import facades.MailFacade;
import facades.RespuestaCotizacionFacade;
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

@Named(value = "controladorCotizacion")
@SessionScoped
public class ControladorCotizacion implements Serializable {

    public ControladorCotizacion() {
    }

    private Cotizacion cotizacion;
    private List<DetalleCotizacion> buscarCotizacion;
    private DetalleCotizacion detalleCotizacion;
    private EstadoCotizacion estadoCotizacion;
    private CotizacionPlan cotizacionPlan;
    private RespuestaCotizacion respuestaCotizacion;
    private Cliente cliente;
    private Usuario usuario;
    private Mail mail;
    private BarChartModel animatedModel2;
    private List<DetalleCotizacion> listarDetalleCotizacionReporte;
    private List<CotizacionReporte> listaCotizaciones;

    @EJB
    private CotizacionFacade cotizacionFacade;
    @EJB
    private DetalleCotizacionFacade detalleCotizacionFacade;
    @EJB
    private EstadoCotizacionFacade estadoCotizacionFacade;
    @EJB
    private RespuestaCotizacionFacade respuestaCotizacionFacade;

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public List<DetalleCotizacion> getBuscarCotizacion() {
        return buscarCotizacion;
    }

    public void setBuscarCotizacion(List<DetalleCotizacion> buscarCotizacion) {
        this.buscarCotizacion = buscarCotizacion;
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

    public CotizacionPlan getCotizacionPlan() {
        return cotizacionPlan;
    }

    public void setCotizacionPlan(CotizacionPlan cotizacionPlan) {
        this.cotizacionPlan = cotizacionPlan;
    }

    public RespuestaCotizacion getRespuestaCotizacion() {
        return respuestaCotizacion;
    }

    public void setRespuestaCotizacion(RespuestaCotizacion respuestaCotizacion) {
        this.respuestaCotizacion = respuestaCotizacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
    }

    @PostConstruct
    public void init() {
        cotizacion = new Cotizacion();
        buscarCotizacion = new ArrayList<>();
        detalleCotizacion = new DetalleCotizacion();
        estadoCotizacion = new EstadoCotizacion();
        respuestaCotizacion = new RespuestaCotizacion();
        cotizacionPlan = new CotizacionPlan();
        cliente = new Cliente();
        usuario = new Usuario();
        mail = new Mail();
        createAnimatedModels();
        listarDetalleCotizacionReporte = new ArrayList<>();
    }

    public List<DetalleCotizacion> listarCotizacion() {
        return detalleCotizacionFacade.findAll();
    }

    public String preEditarRespuestaCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
        return "responderCotizacion?faces-redirect=true";
    }

    public void registrarRespuesta() {
        Date fechaRegistro = new Date();
        try {
            cotizacion.setIdCotizacion(detalleCotizacion.getIdCotizacion().getIdCotizacion());
            cotizacion.setIdCliente(detalleCotizacion.getIdCotizacion().getIdCliente());
            cotizacion.setIdEstadoCotizacion(estadoCotizacionFacade.find((short) 1));
            cotizacionFacade.edit(cotizacion);
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            respuestaCotizacion.setIdAdministrador(usuario);
            respuestaCotizacion.setIdCotizacion(cotizacion);
            respuestaCotizacion.setFechaRegistro(fechaRegistro);
            respuestaCotizacionFacade.create(respuestaCotizacion);
            mail.setRemitente("");
            mail.setDestinatario(cotizacion.getIdCliente().getNumeroIdentificacion().getCorreoElectronico());
            mail.setAsunto("¡Te han respondido la cotización!");
            mail.setMensaje("¡Hola " + detalleCotizacion.getIdCotizacion().getIdCliente().getNumeroIdentificacion().getNombres() + "! Un administrador ha respondido tu cotización realizada el " + detalleCotizacion.getFechaRegistro() + " tu mensaje enviado fue: " + detalleCotizacion.getDescripcionCotizacion() + "<br/>El mensaje de la respuesta es el siguiente: " + respuestaCotizacion.getMensaje());
            MailFacade.send(mail.getRemitente(), mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
            respuestaCotizacion = new RespuestaCotizacion();
            cotizacion = new Cotizacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado la respuesta exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public List<RespuestaCotizacion> listarRespuestaCotizacion() {
        return respuestaCotizacionFacade.findAll();
    }

    public List<DetalleCotizacion> listarCotizacionCliente() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return detalleCotizacionFacade.consultarCotizacionesCliente(usuario.getNumeroIdentificacion());
    }

    private void createAnimatedModels() {
        animatedModel2 = initBarModel2();
        animatedModel2.setTitle("Clientes que más han cotizado en el mes.");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setLabel("Nombre del cliente.");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Precio total de todas las cotizaciones.");
        yAxis.setMin(0);
        yAxis.setMax(25000000);
    }

    private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();

        ChartSeries clientes = new ChartSeries();
        clientes.setLabel("Precio de las cotizaciones que han hecho los clientes.");
        List<ClienteMes> listaClienteMes = cotizacionFacade.consultarClienteMes();
        for (ClienteMes clienteMes : listaClienteMes) {
            clientes.set(clienteMes.getNombreCliente(), clienteMes.getPrecioPlanesTotal());
        }
        model.addSeries(clientes);
        return model;
    }

    public String preConsultarCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
        return "consultarCotizacion?faces-redirect=true";
    }

    public String verRespuestaCliente(RespuestaCotizacion respuestaCotizacion) {
        this.respuestaCotizacion = respuestaCotizacion;
        return "verRespuesta?faces-redirect=true";
    }

    public List<RespuestaCotizacion> listarRespuestaCliente() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return respuestaCotizacionFacade.consultarCotizacionesCliente(usuario.getNumeroIdentificacion());
    }

    public List<DetalleCotizacion> consultarCotizacionReporte() {
        listarDetalleCotizacionReporte = detalleCotizacionFacade.findAll();
        return listarDetalleCotizacionReporte;
    }

    public String deshabilitarRespuesta(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
        if (detalleCotizacion.getIdCotizacion().getIdEstadoCotizacion().getIdEstadoCotizacion() == 1) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public String deshabilitarRespuestaCliente(RespuestaCotizacion respuestaCotizacion){
         this.respuestaCotizacion = respuestaCotizacion;
        if (respuestaCotizacion.getIdCotizacion().getIdEstadoCotizacion().getIdEstadoCotizacion() == 2) {
            return "true";
        } else {
            return "false";
        }
    }

    public void reporte(ActionEvent actionEvent) throws JRException {
        consultarCotizacionReporte();
        try {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listarDetalleCotizacionReporte);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
            JasperPrint jasperPrint = JasperFillManager.fillReport(ruta + "//topClientes.jasper", new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename = DetalleCotizacion.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<CotizacionReporte> consultarReporteCotizacion() {
        listaCotizaciones = cotizacionFacade.reporteCotizaciones();
        return listaCotizaciones;
    }

    public void reporteCotizacion(ActionEvent actionEvent) throws JRException {
        consultarReporteCotizacion();
        try {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaCotizaciones);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
            JasperPrint jasperPrint = JasperFillManager.fillReport(ruta + "//cotizaciones.jasper", new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename = Resultado_Cotizaciones.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
