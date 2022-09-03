package controladores;

import entidades.DetalleVenta;
import entidades.Mail;
import entidades.OrdenVenta;
import entidades.Pago;
import entidades.Usuario;
import entidades.Venta;
import facades.DetalleVentaFacade;
import facades.MailFacade;
import facades.PagoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controladorPago")
@SessionScoped
public class ControladorPago implements Serializable {

    public ControladorPago() {
    }

    private Pago pago;
    private List<Pago> buscarPago;
    private OrdenVenta ordenVenta;
    private Venta venta;
    private DetalleVenta detalleVenta;
    private Usuario usuario;
    private Mail mail;

    @EJB
    private PagoFacade pagoFacade;
    @EJB
    private DetalleVentaFacade detalleVentaFacade;

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public List<Pago> getBuscarPago() {
        return buscarPago;
    }

    public void setBuscarPago(List<Pago> buscarPago) {
        this.buscarPago = buscarPago;
    }

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
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

    @PostConstruct
    public void init() {
        pago = new Pago();
        buscarPago = new ArrayList<>();
        ordenVenta = new OrdenVenta();
        venta = new Venta();
        detalleVenta = new DetalleVenta();
        usuario = new Usuario();
        mail = new Mail();
    }

    public String pago(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
        this.venta = ordenVenta.getNumeroContrato();
        DetalleVenta detalleVentaConsultada = detalleVentaFacade.consultarDetalleVenta(venta.getNumeroContrato());
        this.detalleVenta = detalleVentaConsultada;
        this.usuario = ordenVenta.getNumeroContrato().getIdCotizacion().getIdCliente().getNumeroIdentificacion();
        return "/Pago/registrarPago?faces-redirect=true";
    }

    public void limpiarPago() {
        pago = new Pago();
    }

    public void registrarPago() {
        Date fecha = new Date();
        try {
            pago.setIdOrdenVenta(ordenVenta);
            pago.setFechaRegistro(fecha);
            pagoFacade.create(pago);
            mail.setDestinatario(usuario.getCorreoElectronico());
            mail.setAsunto("Pago exitoso");
            mail.setMensaje("<p>Estimado usuario " + usuario.getNombres() + " " + usuario.getApellidos() + " agradecemos su compromiso por el pago que realizo este mes debido a los servicios que le estamos prestando."
                    + "Esperamos que usted y su familia se encuentren bien. \n Gracias por hacer parte de nuestra comunidad.</p>");
            MailFacade.send("", mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El Pago se registro exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el pago"));
        }
        limpiarPago();
    }

    public void consultarDatosPago(Pago pago) {
        this.pago = pago;
        this.ordenVenta = pago.getIdOrdenVenta();
        this.venta = pago.getIdOrdenVenta().getNumeroContrato();
        DetalleVenta detalleVentaConsultado = detalleVentaFacade.consultarDetalleVenta(venta.getNumeroContrato());
        this.detalleVenta = detalleVentaConsultado;
        this.usuario = pago.getIdOrdenVenta().getNumeroContrato().getIdCotizacion().getIdCliente().getNumeroIdentificacion();
    }

    public String consultarPago(Pago pago) {
        consultarDatosPago(pago);
        return "consultarPago?faces-redirect=true";
    }

    public String preEditarPago(Pago pago) {
        consultarDatosPago(pago);
        return "actualizarPago?faces-redirect=true";
    }

    public void editarPago() {
        try {
            pago.setIdOrdenVenta(ordenVenta);
            pagoFacade.edit(pago);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Pago actualizado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo actualizar el pago"));
        }
        limpiarPago();
    }

    public List<Pago> listarPago() {
        return pagoFacade.findAll();
    }

    public String deshabilitarOrdenVenta(OrdenVenta ordenVenta) {
        Pago consultarPago = pagoFacade.consultarPago(ordenVenta.getIdOrdenVenta());
        try {
            if (consultarPago == null) {
                return "false";
            } else {
                return "true";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Pago> listarPagoCliente() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return pagoFacade.consultarPagoCliente(usuario.getNumeroIdentificacion());
    }

    public String consultarPagoCliente(Pago pago) {
        consultarDatosPago(pago);
        return "consultarPago?faces-redirect=true";
    }
}
