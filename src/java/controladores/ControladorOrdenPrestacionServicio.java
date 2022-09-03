package controladores;

import entidades.DetalleVenta;
import entidades.EstadoOrdenPrestacionServicio;
import entidades.OrdenPrestacionServicio;
import entidades.Usuario;
import entidades.Venta;
import facades.DetalleVentaFacade;
import facades.EstadoOrdenPrestacionServicioFacade;
import facades.OrdenPrestacionServicioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controladorOrdenPrestacionServicio")
@SessionScoped
public class ControladorOrdenPrestacionServicio implements Serializable {

    public ControladorOrdenPrestacionServicio() {
    }

    private OrdenPrestacionServicio ordenPrestacionServicio;
    private DetalleVenta detalleVenta;
    private EstadoOrdenPrestacionServicio estadoOrdenPrestacionServicio;
    private Venta venta;
    private Usuario usuario;

    @EJB
    private OrdenPrestacionServicioFacade ordenPrestacionServicioFacade;
    @EJB
    private DetalleVentaFacade detalleVentaFacade;
    @EJB
    private EstadoOrdenPrestacionServicioFacade estadoOrdenPrestacionServicioFacade;

    public OrdenPrestacionServicio getOrdenPrestacionServicio() {
        return ordenPrestacionServicio;
    }

    public void setOrdenPrestacionServicio(OrdenPrestacionServicio ordenPrestacionServicio) {
        this.ordenPrestacionServicio = ordenPrestacionServicio;
    }

    public DetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public EstadoOrdenPrestacionServicio getEstadoOrdenPrestacionServicio() {
        return estadoOrdenPrestacionServicio;
    }

    public void setEstadoOrdenPrestacionServicio(EstadoOrdenPrestacionServicio estadoOrdenPrestacionServicio) {
        this.estadoOrdenPrestacionServicio = estadoOrdenPrestacionServicio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        ordenPrestacionServicio = new OrdenPrestacionServicio();
        detalleVenta = new DetalleVenta();
        estadoOrdenPrestacionServicio = new EstadoOrdenPrestacionServicio();
        venta = new Venta();
        usuario = new Usuario();
    }

    public String seleccionarDetalleVenta(DetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
        return "registrarOrdenPrestacionServicio?faces-redirect=true";
    }

    public void limpiarOrdenPrestacion() {
        ordenPrestacionServicio = new OrdenPrestacionServicio();
    }

    public Date fecha() {
        Date fecha = new Date();
        return fecha;
    }

    public void registrarOrdenPrestacionServicio() {
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            ordenPrestacionServicio.setIdAdministrador(usuario);
            ordenPrestacionServicio.setNumeroContrato(detalleVenta.getNumeroContrato());
            ordenPrestacionServicio.setIdEstadoOrdenPrestacionServicio(estadoOrdenPrestacionServicioFacade.find((short) 1));
            ordenPrestacionServicio.setFechaRegistro(fecha());
            ordenPrestacionServicioFacade.create(ordenPrestacionServicio);
            limpiarOrdenPrestacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado la orden correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public List<OrdenPrestacionServicio> listarOrdenPrestacionServicio() {
        return ordenPrestacionServicioFacade.findAll();
    }

    public List<DetalleVenta> listarDetalleVenta() {
        return detalleVentaFacade.findAll();
    }

    public String preEditarOrdenPrestacionServicio(OrdenPrestacionServicio ordenPrestacionServicio) {
        this.ordenPrestacionServicio = ordenPrestacionServicio;
        return "modificarOrdenPrestacionServicio?faces-redirect=true";
    }

    public void editarOrdenPrestacionServicio() {
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            ordenPrestacionServicio.setIdAdministrador(usuario);
            ordenPrestacionServicio.setIdEstadoOrdenPrestacionServicio(estadoOrdenPrestacionServicioFacade.find(estadoOrdenPrestacionServicio.getIdEstadoOrdenPrestacionServicio()));
            ordenPrestacionServicioFacade.edit(ordenPrestacionServicio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha modificado la orden correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
        limpiarOrdenPrestacion();
    }

    public String inactivarOrdenPrestacionServicio(OrdenPrestacionServicio ordenPrestacionServicio) {
        try {
            ordenPrestacionServicio.setIdEstadoOrdenPrestacionServicio(estadoOrdenPrestacionServicioFacade.find((short) 2));
            ordenPrestacionServicioFacade.edit(ordenPrestacionServicio);
            return "ordenPrestacionServicios?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
            return "ordenPrestacionServicios?faces-redirect=true";
        }
    }

    public String deshabilitarInactivarOrden(OrdenPrestacionServicio ordenPrestacionServicio) {
        this.ordenPrestacionServicio = ordenPrestacionServicio;
        if (ordenPrestacionServicio.getIdEstadoOrdenPrestacionServicio().getIdEstadoOrdenPrestacionServicio() == 2) {
            return "true";
        } else {
            return "false";
        }
    }
}
