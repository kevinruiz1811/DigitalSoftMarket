package controladores;

import entidades.DetalleVenta;
import entidades.Mail;
import entidades.OrdenVenta;
import entidades.Usuario;
import entidades.Venta;
import facades.DetalleVentaFacade;
import facades.OrdenVentaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controladorOrdenVenta")
@SessionScoped
public class ControladorOrdenVenta implements Serializable {

    public ControladorOrdenVenta() {
    }

    private OrdenVenta ordenVenta;
    private List<OrdenVenta> buscarOrdenVenta;
    private Venta venta;
    private DetalleVenta detalleVenta;
    private Usuario usuario;
    private Mail mail;

    @EJB
    private OrdenVentaFacade ordenVentaFacade;
    @EJB
    private DetalleVentaFacade detalleVentaFacade;

    public OrdenVenta getOrdenVenta() {
        return ordenVenta;
    }

    public void setOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
    }

    public List<OrdenVenta> getBuscarOrdenVenta() {
        return buscarOrdenVenta;
    }

    public void setBuscarOrdenVenta(List<OrdenVenta> buscarOrdenVenta) {
        this.buscarOrdenVenta = buscarOrdenVenta;
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
        ordenVenta = new OrdenVenta();
        buscarOrdenVenta = new ArrayList<>();
        venta = new Venta();
        detalleVenta = new DetalleVenta();
        usuario = new Usuario();
        mail = new Mail();
    }

    public void limpiarOrdenVenta() {
        ordenVenta = new OrdenVenta();
    }

    public void consultarDatosOrdenVenta(OrdenVenta ordenVenta) {
        this.ordenVenta = ordenVenta;
        this.venta = ordenVenta.getNumeroContrato();
        DetalleVenta detalleVentaConsultado = detalleVentaFacade.consultarDetalleVenta(venta.getNumeroContrato());
        this.detalleVenta = detalleVentaConsultado;
        this.usuario = ordenVenta.getNumeroContrato().getIdCotizacion().getIdCliente().getNumeroIdentificacion();
    }

    public String consultarOrdenVenta(OrdenVenta ordenVenta) {
        consultarDatosOrdenVenta(ordenVenta);
        return "consultarOrdenVenta?faces-redirect=true";
    }

    public String preEditarOrdenVenta(OrdenVenta ordenVenta) {
        consultarDatosOrdenVenta(ordenVenta);
        return "actualizarOrdenVenta?faces-redirect=true";
    }

    public void editarOrdenVenta() {
        try {
            ordenVenta.setNumeroContrato(detalleVenta.getNumeroContrato());
            ordenVentaFacade.edit(ordenVenta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Orden de Venta actualizada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo actualizar"));
        }
        ordenVenta = new OrdenVenta();
    }

    public List<OrdenVenta> listarOrdenVenta() {
        return ordenVentaFacade.findAll();
    }

    public List<OrdenVenta> listarOrdenVentaCliente() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return ordenVentaFacade.consultarOrdenVentaCliente(usuario.getNumeroIdentificacion());
    }

    public String consultarOrdenVentaCliente(OrdenVenta ordenVenta) {
        consultarDatosOrdenVenta(ordenVenta);
        return "consultarOrdenVenta?faces-redirect=true";
    }
}
