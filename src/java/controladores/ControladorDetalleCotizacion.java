package controladores;

import entidades.Cliente;
import entidades.Cotizacion;
import entidades.DetalleCotizacion;
import entidades.Mail;
import entidades.Plan;
import entidades.Usuario;
import facades.ClienteFacade;
import facades.CotizacionFacade;
import facades.DetalleCotizacionFacade;
import facades.EstadoCotizacionFacade;
import facades.MailFacade;
import facades.PlanFacade;
import facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "controladorDetalleCotizacion")
@SessionScoped
public class ControladorDetalleCotizacion implements Serializable {

    public ControladorDetalleCotizacion() {
    }

    @Inject
    private ControladorPlan controladorPlan;

    private Plan planSeleccionado;
    private ArrayList<Plan> carrito;
    private Cotizacion cotizacion;
    private DetalleCotizacion detalleCotizacion;
    private Cliente cliente;
    private ArrayList<Usuario> admins;
    private Usuario usuario;
    private Mail mail;

    private Integer idPlanSeleccionado;
    private BigDecimal totalCompra = new BigDecimal(BigInteger.ZERO);
    private short totalCantidad = 0;
    private String nombrePlanes = "";

    @EJB
    private PlanFacade planFacade;
    @EJB
    private CotizacionFacade cotizacionFacade;
    @EJB
    private DetalleCotizacionFacade detalleCotizacionFacade;
    @EJB
    private EstadoCotizacionFacade estadoCotizacionFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    public Plan getPlanSeleccionado() {
        return planSeleccionado;
    }

    public void setPlanSeleccionado(Plan planSeleccionado) {
        this.planSeleccionado = planSeleccionado;
    }

    public ArrayList<Plan> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Plan> carrito) {
        this.carrito = carrito;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public DetalleCotizacion getDetalleCotizacion() {
        if (detalleCotizacion == null) {
            detalleCotizacion = new DetalleCotizacion();
        }
        return detalleCotizacion;
    }

    public void setDetalleCotizacion(DetalleCotizacion detalleCotizacion) {
        this.detalleCotizacion = detalleCotizacion;
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

    public Integer getIdPlanSeleccionado() {
        return idPlanSeleccionado;
    }

    public void setIdPlanSeleccionado(Integer idPlanSeleccionado) {
        this.idPlanSeleccionado = idPlanSeleccionado;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public short getTotalCantidad() {
        return totalCantidad;
    }

    public void setTotalCantidad(short totalCantidad) {
        this.totalCantidad = totalCantidad;
    }

    public String getNombrePlanes() {
        return nombrePlanes;
    }

    public void setNombrePlanes(String nombrePlanes) {
        this.nombrePlanes = nombrePlanes;
    }

    public PlanFacade getPlanFacade() {
        return planFacade;
    }

    public void setPlanFacade(PlanFacade planFacade) {
        this.planFacade = planFacade;
    }

    @PostConstruct
    public void init() {
        planSeleccionado = new Plan();
        carrito = new ArrayList<>();
        cotizacion = new Cotizacion();
        detalleCotizacion = new DetalleCotizacion();
        cliente = new Cliente();
        admins = new ArrayList<>();
        usuario = new Usuario();
        mail = new Mail();
    }

    public Plan PlanSeleccionado(Integer idPlan) {
        return planFacade.find(idPlan);
    }

    public void añadirMensaje(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String agregarAlCarrito(Integer idPlan) {
        setIdPlanSeleccionado(idPlan);
        Plan p = buscarPlanCarrito(idPlanSeleccionado);
        if (p != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No puedes añadir el mismo plan más de 1 vez al carro de compras, por favor, escoge otro o si ya lo has hecho, envia una cotización a los administradores para que te respondan y te ayuden en lo que necesites."));
        } else {
            planSeleccionado = controladorPlan.buscarPlanCarrito(idPlanSeleccionado);
            planSeleccionado.setCantidad((short) 1);
            carrito.add(planSeleccionado);
            totalCompra = totalCompra.add(planSeleccionado.getPrecioPlan());
            nombrePlanes = nombrePlanes.concat(planSeleccionado.getNombrePlan() + "\n");
            totalCantidad++;
            return "carritoCompras?faces-redirect=true";
        }
        return null;
    }

    private Plan buscarPlanCarrito(Integer idPlan) {
        Plan p = null;
        for (Plan pln : carrito) {
            if (pln.getIdPlan() == idPlan) {
                p = pln;
                break;
            }
        }
        return p;
    }

    public String borrarDelCarrito(Integer idPlan) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (carrito.get(i).getIdPlan() == idPlan) {
                totalCompra = totalCompra.subtract(carrito.get(i).getPrecioPlan());
                nombrePlanes = nombrePlanes.replaceAll(carrito.get(i).getNombrePlan(), "");
                nombrePlanes = nombrePlanes.replaceFirst("\n", "");
                totalCantidad--;
                carrito.remove(i);
                encontrado = true;
                añadirMensaje("Confirmado", "El plan ha sido eliminado del carro.");
            }
            i++;
        }
        return "carritoCompras?faces-redirect=true";
    }

    public String preRegistrarCotización(short totalCant) {
        if (totalCant != 0) {
            return "registrarCotizacionCliente?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Primero tienes que escoger un plan para realizar la cotización."));
            return null;
        }
    }

    public void enviarCotizacion() {
        Date fechaRegistro = new Date();
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            cliente = clienteFacade.consultarDocumento(usuario.getNumeroIdentificacion());
            cotizacion.setIdCliente(cliente);
            cotizacion.setIdEstadoCotizacion(estadoCotizacionFacade.find((short) 2));
            cotizacionFacade.create(cotizacion);
            detalleCotizacion.setIdCotizacion(cotizacion);
            detalleCotizacion.setFechaRegistro(fechaRegistro);
            detalleCotizacion.setCantidadTotalPlanes((short) getTotalCantidad());
            detalleCotizacion.setPrecioTotalPlanes(getTotalCompra());
            detalleCotizacion.setNombrePlanes(getNombrePlanes());
            detalleCotizacionFacade.create(detalleCotizacion);
            short idRol = 1;
            mail.setRemitente("");
            mail.setDestinatarios(usuarioFacade.consultarEmail(idRol));
            mail.setAsunto("¡Un cliente ha registrado una cotización!");
            mail.setMensaje("Hola administrador, el cliente " + detalleCotizacion.getIdCotizacion().getIdCliente().getNumeroIdentificacion().getNombres() + " " + detalleCotizacion.getIdCotizacion().getIdCliente().getNumeroIdentificacion().getApellidos() + " acaba de registrar una cotización, la petición es: " + detalleCotizacion.getDescripcionCotizacion() + ", los planes que ha escogido son: " + detalleCotizacion.getNombrePlanes() + " y el precio total de los planes que ha cotizado es de: $" + detalleCotizacion.getPrecioTotalPlanes() + "<br/>Esperamos que la respondas lo más pronto posible, ¡Que tengas un buen día!");
            MailFacade.send(mail.getRemitente(), null, mail.getDestinatarios(), mail.getAsunto(), mail.getMensaje());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha enviado la cotización correctamente, en los próximos días te estaremos respondiendo la petición, ¡Muchas gracias por contar con nosotros!, recuerda que siempre estamos a tu servicio."));
            carrito = new ArrayList<>();
            totalCantidad = 0;
            totalCompra = null;
            nombrePlanes = "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error" + e.getMessage()));
        }
        limpiarCotizacion();
    }

    public void limpiarCotizacion() {
        cotizacion = new Cotizacion();
        detalleCotizacion = new DetalleCotizacion();
    }
}
