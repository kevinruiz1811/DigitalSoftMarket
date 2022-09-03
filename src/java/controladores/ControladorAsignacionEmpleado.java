package controladores;

import entidades.AsignacionEmpleado;
import entidades.AvanceEvento;
import entidades.Empleado;
import entidades.OrdenPrestacionServicio;
import entidades.TipoEmpleado;
import entidades.Usuario;
import facades.AsignacionEmpleadoFacade;
import facades.AvanceEventoFacade;
import facades.EmpleadoFacade;
import facades.OrdenPrestacionServicioFacade;
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

@Named(value = "controladorAsignacionEmpleado")
@SessionScoped
public class ControladorAsignacionEmpleado implements Serializable {

    public ControladorAsignacionEmpleado() {
    }

    private AvanceEvento avanceEvento;
    private AsignacionEmpleado asignacionEmpleado;
    private List<AsignacionEmpleado> listarAsignacionEmpleado;
    private OrdenPrestacionServicio ordenPrestacionServicio;
    private TipoEmpleado tipoEmpleado;
    private Empleado empleado;
    private Usuario usuario;

    @EJB
    private AsignacionEmpleadoFacade asignacionEmpleadoFacade;
    @EJB
    private EmpleadoFacade empleadoFacade;
    @EJB
    private OrdenPrestacionServicioFacade ordenPrestacionServicioFacade;
    @EJB
    private AvanceEventoFacade avanceEventoFacade;

    public AvanceEvento getAvanceEvento() {
        return avanceEvento;
    }

    public void setAvanceEvento(AvanceEvento avanceEvento) {
        this.avanceEvento = avanceEvento;
    }

    public AsignacionEmpleado getAsignacionEmpleado() {
        return asignacionEmpleado;
    }

    public void setAsignacionEmpleado(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
    }

    public List<AsignacionEmpleado> getListarAsignacionEmpleado() {
        return listarAsignacionEmpleado;
    }

    public void setListarAsignacionEmpleado(List<AsignacionEmpleado> listarAsignacionEmpleado) {
        this.listarAsignacionEmpleado = listarAsignacionEmpleado;
    }

    public OrdenPrestacionServicio getOrdenPrestacionServicio() {
        return ordenPrestacionServicio;
    }

    public void setOrdenPrestacionServicio(OrdenPrestacionServicio ordenPrestacionServicio) {
        this.ordenPrestacionServicio = ordenPrestacionServicio;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void init() {
        avanceEvento = new AvanceEvento();
        asignacionEmpleado = new AsignacionEmpleado();
        listarAsignacionEmpleado = new ArrayList<>();
        ordenPrestacionServicio = new OrdenPrestacionServicio();
        tipoEmpleado = new TipoEmpleado();
        empleado = new Empleado();
        usuario = new Usuario();
    }

    public Date fecha() {
        Date fecha = new Date();
        return fecha;
    }

    public void limpiarAsignacion() {
        asignacionEmpleado = new AsignacionEmpleado();
        empleado = new Empleado();
        ordenPrestacionServicio = new OrdenPrestacionServicio();
        tipoEmpleado = new TipoEmpleado();
        avanceEvento = new AvanceEvento();
    }

    public List<AsignacionEmpleado> listarEmpleadosAsignacion() {
        return asignacionEmpleadoFacade.findAll();
    }

    public void registrarAsignacion() {
        try {
            asignacionEmpleado.setIdEmpleado(empleado);
            asignacionEmpleado.setIdOrdenPrestacionServicio(ordenPrestacionServicio);
            asignacionEmpleado.setFechaRegistro(fecha());
            asignacionEmpleadoFacade.create(asignacionEmpleado);
            limpiarAsignacion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha asignado al empleado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public String preRegistrarAsignacion(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
        return "registrarAsignacion?faces-redirect=true";
    }

    public String consultarEmpleadoAsignado(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
        return "consultarEmpleadosAsignados?faces-redirect=true";
    }

    public List<OrdenPrestacionServicio> listarCampaña() {
        return ordenPrestacionServicioFacade.findAll();
    }

    public List<Empleado> listarEmpleado() {
        return empleadoFacade.findAll();
    }

    public List<AsignacionEmpleado> listarCampañaEmpleado() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return asignacionEmpleadoFacade.consultarCampañaEmpleado(usuario.getNumeroIdentificacion());
    }

    public List<AsignacionEmpleado> listarAsignacionEmpleado() {
        listarAsignacionEmpleado = asignacionEmpleadoFacade.findAll();
        return listarAsignacionEmpleado;
    }

    public List<AvanceEvento> listarAvanceEventos() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return avanceEventoFacade.consultarAvanceEmpleado(usuario.getNumeroIdentificacion());
    }

    public String consultarAvanceEvento(AvanceEvento avanceEvento) {
        this.avanceEvento = avanceEvento;
        return "consultarAvanceEmpleado?faces-redirect=true";
    }
}
