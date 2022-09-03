package controladores;

import entidades.AsignacionEmpleado;
import entidades.AvanceEvento;
import entidades.Empleado;
import entidades.Mail;
import entidades.Usuario;
import facades.AvanceEventoFacade;
import facades.MailFacade;
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

@Named(value = "controladorAvanceEvento")
@SessionScoped
public class ControladorAvanceEvento implements Serializable {

    public ControladorAvanceEvento() {
    }

    private AvanceEvento avanceEvento;
    private List<AvanceEvento> listarAvanceEvento;
    private AsignacionEmpleado asignacionEmpleado;
    private Empleado empleado;
    private Usuario usuario;
    private Mail mail;

    @EJB
    private AvanceEventoFacade avanceEventoFacade;

    public AvanceEvento getAvanceEvento() {
        return avanceEvento;
    }

    public void setAvanceEvento(AvanceEvento avanceEvento) {
        this.avanceEvento = avanceEvento;
    }

    public List<AvanceEvento> getListarAvanceEvento() {
        return listarAvanceEvento;
    }

    public void setListarAvanceEvento(List<AvanceEvento> listarAvanceEvento) {
        this.listarAvanceEvento = listarAvanceEvento;
    }

    public AsignacionEmpleado getAsignacionEmpleado() {
        return asignacionEmpleado;
    }

    public void setAsignacionEmpleado(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
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

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    @PostConstruct
    public void init() {
        avanceEvento = new AvanceEvento();
        listarAvanceEvento = new ArrayList<>();
        asignacionEmpleado = new AsignacionEmpleado();
        empleado = new Empleado();
        usuario = new Usuario();
        mail = new Mail();
    }

    public Date fecha() {
        Date fecha = new Date();
        return fecha;
    }

    public void limpiarAvanceEvento() {
        avanceEvento = new AvanceEvento();
    }

    public List<AvanceEvento> listarAvanceEvento() {
        return avanceEventoFacade.findAll();
    }

    public String consultarAvances(AvanceEvento avanceEvento) {
        this.avanceEvento = avanceEvento;
        return "consultarAvances?faces-redirect=true";
    }

    public String preRegistrarAvanceEvento(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
        if (asignacionEmpleado.getIdOrdenPrestacionServicio().getIdEstadoOrdenPrestacionServicio().getIdEstadoOrdenPrestacionServicio() == 1) {
            return "registrarAvancesEmpleado?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Lo sentimos, esta orden de prestación de servicio actualmente se encuentra en estado inactivo, si crees que es un error, comunicate con los administradores."));
        }
        return null;
    }

    public String deshabilitarAvanceEmpleado(AsignacionEmpleado asignacionEmpleado) {
        this.asignacionEmpleado = asignacionEmpleado;
        if (asignacionEmpleado.getIdOrdenPrestacionServicio().getIdEstadoOrdenPrestacionServicio().getIdEstadoOrdenPrestacionServicio() == 1) {
            return "false";
        } else {
            return "true";
        }
    }

    public void registrarAvanceEvento() {
        try {
            avanceEvento.setIdAsignacionEmpleado(asignacionEmpleado);
            avanceEvento.setFechaRegistro(fecha());
            avanceEventoFacade.create(avanceEvento);
            mail.setRemitente("");
            mail.setDestinatario(avanceEvento.getIdAsignacionEmpleado().getIdOrdenPrestacionServicio().getIdAdministrador().getCorreoElectronico());
            mail.setAsunto("¡Un empleado a registrado un avance de una orden de prestación!");
            mail.setMensaje("Hola " + avanceEvento.getIdAsignacionEmpleado().getIdOrdenPrestacionServicio().getIdAdministrador().getNombres() + " " + avanceEvento.getIdAsignacionEmpleado().getIdOrdenPrestacionServicio().getIdAdministrador().getApellidos() + " un empleado ha regsitrado un avance de la orden " + avanceEvento.getIdAsignacionEmpleado().getIdOrdenPrestacionServicio().getNombreEvento() + " asignado el " + avanceEvento.getIdAsignacionEmpleado().getFechaRegistro() + "<br/>El avance que registró el empleado es: " + avanceEvento.getDescripcionAvance());
            MailFacade.send(mail.getRemitente(), mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
            limpiarAvanceEvento();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado el avance correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }
}
