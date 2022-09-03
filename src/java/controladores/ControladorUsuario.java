package controladores;

import entidades.Cliente;
import entidades.Departamento;
import entidades.Empleado;
import entidades.EstadoUsuario;
import entidades.Influenciador;
import entidades.Mail;
import entidades.Prensa;
import entidades.RedSocial;
import entidades.Rol;
import entidades.TipoCliente;
import entidades.TipoEmpleado;
import entidades.TipoIdentificacion;
import entidades.TipoNicho;
import entidades.TipoPrensa;
import entidades.Usuario;
import facades.ClienteFacade;
import facades.DepartamentoFacade;
import facades.EmpleadoFacade;
import facades.EstadoUsuarioFacade;
import facades.InfluenciadorFacade;
import facades.MailFacade;
import facades.PrensaFacade;
import facades.RedSocialFacade;
import facades.RolFacade;
import facades.TipoClienteFacade;
import facades.TipoEmpleadoFacade;
import facades.TipoIdentificacionFacade;
import facades.TipoNichoFacade;
import facades.TipoPrensaFacade;
import facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "controladorUsuario")
@SessionScoped
public class ControladorUsuario implements Serializable {

    public ControladorUsuario() {
    }

    private Usuario usuario;
    private List<Usuario> buscarUsuario;
    private TipoIdentificacion tipoIdentificacion;
    private Departamento departamento;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    private Cliente cliente;
    private TipoCliente tipoCliente;
    private Empleado empleado;
    private TipoEmpleado tipoEmpleado;
    private Influenciador influenciador;
    private TipoNicho tipoNicho;
    private RedSocial redSocialPrincipal;
    private RedSocial redSocialSecundaria;
    private Prensa prensa;
    private TipoPrensa tipoPrensa;
    private Mail mail;
    private String confirmarClave;

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    @EJB
    private DepartamentoFacade departamentoFacade;
    @EJB
    private RolFacade rolFacade;
    @EJB
    private EstadoUsuarioFacade estadoUsuarioFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private TipoClienteFacade tipoClienteFacade;
    @EJB
    private EmpleadoFacade empleadoFacade;
    @EJB
    private TipoEmpleadoFacade tipoEmpleadoFacade;
    @EJB
    private InfluenciadorFacade influenciadorFacade;
    @EJB
    private TipoNichoFacade tipoNichoFacade;
    @EJB
    private RedSocialFacade redSocialFacade;
    @EJB
    private PrensaFacade prensaFacade;
    @EJB
    private TipoPrensaFacade tipoPrensaFacade;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getBuscarUsuario() {
        return buscarUsuario;
    }

    public void setBuscarUsuario(List<Usuario> buscarUsuario) {
        this.buscarUsuario = buscarUsuario;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Influenciador getInfluenciador() {
        return influenciador;
    }

    public void setInfluenciador(Influenciador influenciador) {
        this.influenciador = influenciador;
    }

    public TipoNicho getTipoNicho() {
        return tipoNicho;
    }

    public void setTipoNicho(TipoNicho tipoNicho) {
        this.tipoNicho = tipoNicho;
    }

    public RedSocial getRedSocialPrincipal() {
        return redSocialPrincipal;
    }

    public void setRedSocialPrincipal(RedSocial redSocialPrincipal) {
        this.redSocialPrincipal = redSocialPrincipal;
    }

    public RedSocial getRedSocialSecundaria() {
        return redSocialSecundaria;
    }

    public void setRedSocialSecundaria(RedSocial redSocialSecundaria) {
        this.redSocialSecundaria = redSocialSecundaria;
    }

    public Prensa getPrensa() {
        return prensa;
    }

    public void setPrensa(Prensa prensa) {
        this.prensa = prensa;
    }

    public TipoPrensa getTipoPrensa() {
        return tipoPrensa;
    }

    public void setTipoPrensa(TipoPrensa tipoPrensa) {
        this.tipoPrensa = tipoPrensa;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        buscarUsuario = new ArrayList<>();
        tipoIdentificacion = new TipoIdentificacion();
        departamento = new Departamento();
        rol = new Rol();
        estadoUsuario = new EstadoUsuario();
        cliente = new Cliente();
        tipoCliente = new TipoCliente();
        empleado = new Empleado();
        tipoEmpleado = new TipoEmpleado();
        influenciador = new Influenciador();
        tipoNicho = new TipoNicho();
        redSocialPrincipal = new RedSocial();
        redSocialSecundaria = new RedSocial();
        prensa = new Prensa();
        tipoPrensa = new TipoPrensa();
        mail = new Mail();
    }

    public void limpiarUsuario() {
        usuario = new Usuario();
        tipoIdentificacion = new TipoIdentificacion();
        departamento = new Departamento();
        rol = new Rol();
        estadoUsuario = new EstadoUsuario();
    }

    public void limpiarCliente() {
        limpiarUsuario();
        cliente = new Cliente();
    }

    public void limpiarEmpleado() {
        limpiarUsuario();
        empleado = new Empleado();
    }

    public void limpiarInfluenciador() {
        limpiarEmpleado();
        influenciador = new Influenciador();
        tipoNicho = new TipoNicho();
        redSocialPrincipal = new RedSocial();
        redSocialSecundaria = new RedSocial();
    }

    public void limpiarPrensa() {
        limpiarEmpleado();
        prensa = new Prensa();
        tipoPrensa = new TipoPrensa();
    }

    public void limpiarTodo() {
        limpiarCliente();
        tipoCliente = new TipoCliente();
        tipoEmpleado = new TipoEmpleado();
        limpiarInfluenciador();
        limpiarPrensa();
    }

    public void generarClave() {
        try {
            if (usuario.getClave() != null) {
                if (usuario.getClave().equals(confirmarClave) || confirmarClave.equals(usuario.getClave())) {
                    usuario.setClave(confirmarClave);
                } else {
                    usuario.setClave(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Las Contraseñas no coinciden."));
                }
            } else if (usuario.getClave() == null || usuario.getClave().equals("")) {
                String generarClave = UUID.randomUUID().toString().toUpperCase().substring(0, 10);
                StringBuilder stringBuilder = new StringBuilder(generarClave);
                String clave = generarClave + " " + stringBuilder.reverse().toString().toLowerCase();
                usuario.setClave(clave);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error."));
        }
    }

    public void correoBienvenida() {
        try {
            mail.setDestinatario(usuario.getCorreoElectronico());
            mail.setAsunto("Gracias por hacer parte de nuestra comunidad");
            mail.setMensaje("<p>Estimado usuario " + usuario.getNombres() + " " + usuario.getApellidos() + " sus credenciales de inicio de sesión son:</p>"
                    + "<ul><li><b>Correo Electrónico: </b>" + usuario.getCorreoElectronico() + "</li>"
                    + "<li><b>Contraseña: </b>" + usuario.getClave() + "</li></ul>"
                    + "<br>" + "<p style=\"font-weight:bold;\">Gracias por hacer parte de nuestra comunidad." + "</p>");
            MailFacade.send("", mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo enviar el Correo Electrónico con las credenciales del usuario."));
        }
        mail = new Mail();
    }

    public void registrarUsuario() {
        Usuario validarNumeroIdentificacion = usuarioFacade.validarNumeroIdentificacion(usuario.getNumeroIdentificacion());
        Usuario validarCorreoElectronico = usuarioFacade.validarCorreoElectronico(usuario.getCorreoElectronico());
        Usuario validarNumeroCelular = usuarioFacade.validarNumeroCelular(usuario.getNumeroCelular());
        try {
            if (validarNumeroIdentificacion != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se puede registrar el usuario porque ya existe uno con este Número de Identificación."));
            } else if (validarCorreoElectronico != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo registrar el usuario porque ya existe uno con este Correo Electrónico."));
            } else if (validarNumeroCelular != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo registrar el usuario porque ya existe uno con este Número de Celular."));
            } else if (validarNumeroIdentificacion == null && validarCorreoElectronico == null && validarNumeroCelular == null) {
                Date fechaActual = new Date();
                usuario.setIdTipoIdentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTipoIdentificacion()));
                usuario.setIdDepartamento(departamentoFacade.find(departamento.getIdDepartamento()));
                usuario.setIdEstadoUsuario(estadoUsuarioFacade.find((short) 1));
                usuario.setFechaRegistro(fechaActual);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario."));
        }
    }

    public void mensajeRegistro() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha registrado exitosamente."));
    }

    public void registroUsuarioExitoso() {
        correoBienvenida();
        mensajeRegistro();
    }

    public void registrarAdministrador() {
        try {
            registrarUsuario();
            usuario.setIdRol(rolFacade.find((short) 1));
            generarClave();
            usuarioFacade.create(usuario);
            registroUsuarioExitoso();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario."));
        }
        limpiarUsuario();
    }

    public void preRegistroCliente() {
        Cliente clienteValidar = clienteFacade.consultarDocumento(usuario.getNumeroIdentificacion());
        try {
            if (clienteValidar != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Este usuario ya existe."));
            } else {
                switch (tipoCliente.getIdTipoCliente()) {
                    case 1:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("registroPersonaJuridica.xhtml");
                    case 2:
                        FacesContext.getCurrentInstance().getExternalContext().redirect("registroPersonaNatural.xhtml");
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Recurso no encontrado"));
        }
    }

    public void valoresCliente() {
        try {
            cliente.setNumeroIdentificacion(usuario);
            cliente.setIdTipoCliente(tipoClienteFacade.find(tipoCliente.getIdTipoCliente()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los datos del usuario no están disponibles."));
        }
    }

    public void validarDatosPersonaJuridica() {
        Cliente validarNit = clienteFacade.consultarNit(cliente.getNit());
        Cliente validarNombreEmpresa = clienteFacade.consultarNombreEmpresa(cliente.getNombreEmpresa());
        try {
            if (validarNit == null && validarNombreEmpresa == null) {
                usuarioFacade.create(usuario);
                clienteFacade.create(cliente);
                registroUsuarioExitoso();
            } else if (validarNombreEmpresa != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo registrar el usuario porque ya existe uno con este Nombre de Empresa."));
            } else if (validarNit != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No se pudo registrar el usuario porque ya existe uno con este NIT."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error."));
        }
    }

    public void registrarCliente() {
        try {
            registrarUsuario();
            usuario.setIdRol(rolFacade.find((short) 2));
            generarClave();
            valoresCliente();
            if (usuario.getClave() != null) {
                switch (cliente.getIdTipoCliente().getIdTipoCliente()) {
                    case 1:
                        validarDatosPersonaJuridica();
                        break;
                    case 2:
                        usuarioFacade.create(usuario);
                        clienteFacade.create(cliente);
                        registroUsuarioExitoso();
                        break;
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario"));
        }
        limpiarCliente();
    }

    public void valoresEmpleado() {
        try {
            empleado.setNumeroIdentificacion(usuario);
            empleado.setIdTipoEmpleado(tipoEmpleadoFacade.find(tipoEmpleado.getIdTipoEmpleado()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los valores del usuario no están disponibles."));
        }
    }

    public String preRegistroEmpleado() {
        Empleado validarEmpleado = empleadoFacade.consultarDocumento(usuario.getNumeroIdentificacion());
        try {
            if (validarEmpleado == null) {
                switch (tipoEmpleado.getIdTipoEmpleado()) {
                    case 1:
                        return "registrarEmpleado?faces-redirect=true";
                    case 2:
                        return "registrarInfluencer?faces-redirect=true";
                    case 3:
                        return "registrarEmpleado?faces-redirect=true";
                    case 4:
                        return "registrarEmpleado?faces-redirect=true";
                    case 5:
                        return "registrarPrensa?faces-redirect=true";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Este empleado ya esta registrado como " + validarEmpleado.getIdTipoEmpleado().getTipoEmpleado()));
            }
        } catch (Exception e) {
            return "/Errores/Error404?faces-redirect=true";
        }
        return null;
    }

    public void registroEmpleado() {
        try {
            registrarUsuario();
            usuario.setIdRol(rolFacade.find((short) 3));
            valoresEmpleado();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los valores del usuario no están disponibles."));
        }
    }

    public void registrarEmpleado() {
        try {
            registroEmpleado();
            generarClave();
            usuarioFacade.create(usuario);
            empleadoFacade.create(empleado);
            registroUsuarioExitoso();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario."));
        }
        limpiarEmpleado();
    }

    public void valoresInfluenciador() {
        try {
            influenciador.setIdEmpleado(empleado);
            influenciador.setIdTipoNicho(tipoNichoFacade.find(tipoNicho.getIdTipoNicho()));
            influenciador.setIdRedSocialPrincipal(redSocialFacade.find(redSocialPrincipal.getIdRedSocial()));
            influenciador.setIdRedSocialSecundaria(redSocialFacade.find(redSocialSecundaria.getIdRedSocial()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los valores del usuario no están disponibles."));
        }
    }

    public void registrarInfluenciador() {
        try {
            registroEmpleado();
            valoresInfluenciador();
            usuarioFacade.create(usuario);
            empleadoFacade.create(empleado);
            influenciadorFacade.create(influenciador);
            mensajeRegistro();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario."));
        }
        limpiarInfluenciador();
    }

    public void valoresPrensa() {
        try {
            prensa.setIdEmpleado(empleado);
            prensa.setIdTipoPrensa(tipoPrensaFacade.find(tipoPrensa.getIdTipoPrensa()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los valores del usuario no están disponibles."));
        }
    }

    public void registrarPrensa() {
        try {
            registroEmpleado();
            valoresPrensa();
            usuarioFacade.create(usuario);
            empleadoFacade.create(empleado);
            prensaFacade.create(prensa);
            mensajeRegistro();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo registrar el usuario."));
        }
        limpiarPrensa();
    }

    public Usuario consultarUsuario(Usuario usuario) {
        try {
            this.usuario = usuario;
            this.tipoIdentificacion = usuario.getIdTipoIdentificacion();
            this.departamento = usuario.getIdDepartamento();
            this.rol = usuario.getIdRol();
            this.estadoUsuario = usuario.getIdEstadoUsuario();
            return usuario;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo encontrar al usuario."));
        }
        return null;
    }

    public Cliente consultarCliente(Usuario usuario) {
        try {
            Cliente clienteConsultado = clienteFacade.consultarDocumento(usuario.getNumeroIdentificacion());
            this.cliente = clienteConsultado;
            this.tipoCliente = clienteConsultado.getIdTipoCliente();
            return clienteConsultado;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo encontrar al usuario."));
        }
        return null;
    }

    public Empleado consultarEmpleado(Usuario usuario) {
        try {
            Empleado empleadoConsultado = empleadoFacade.consultarDocumento(usuario.getNumeroIdentificacion());
            this.empleado = empleadoConsultado;
            this.tipoEmpleado = empleadoConsultado.getIdTipoEmpleado();
            return empleadoConsultado;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo encontrar al usuario."));
        }
        return null;
    }

    public Influenciador consultarInfluenciador(Usuario usuario) {
        try {
            Influenciador influenciadorConsultado = influenciadorFacade.consultarDocumento(usuario.getNumeroIdentificacion());
            this.influenciador = influenciadorConsultado;
            this.tipoNicho = influenciadorConsultado.getIdTipoNicho();
            this.redSocialPrincipal = influenciadorConsultado.getIdRedSocialPrincipal();
            this.redSocialSecundaria = influenciadorConsultado.getIdRedSocialSecundaria();
            return influenciadorConsultado;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo encontrar al usuario."));
        }
        return null;
    }

    public Prensa consultarPrensa(Usuario usuario) {
        try {
            Prensa prensaConsultado = prensaFacade.consultarDocumento(usuario.getNumeroIdentificacion());
            this.prensa = prensaConsultado;
            this.tipoPrensa = prensaConsultado.getIdTipoPrensa();
            return prensaConsultado;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo encontrar al usuario."));
        }
        return null;
    }

    public String preConsultarUsuario(Usuario usuario) {
        try {
            switch (consultarUsuario(usuario).getIdRol().getIdRol()) {
                case 1:
                    return "consultarAdministrador?faces-redirect=true";
                case 2:
                    switch (consultarCliente(usuario).getIdTipoCliente().getIdTipoCliente()) {
                        case 1:
                            return "consultarPersonaJuridica?faces-redirect=true";
                        case 2:
                            return "consultarPersonaNatural?faces-redirect=true";
                    }
                case 3:
                    switch (consultarEmpleado(usuario).getIdTipoEmpleado().getIdTipoEmpleado()) {
                        case 1:
                            return "consultarEmpleado?faces-redirect=true";
                        case 2:
                            consultarInfluenciador(usuario);
                            return "consultarInfluencer?faces-redirect=true";
                        case 3:
                            return "consultarEmpleado?faces-redirect=true";
                        case 4:
                            return "consultarEmpleado?faces-redirect=true";
                        case 5:
                            consultarPrensa(usuario);
                            return "consultarPrensa?faces-redirect=true";
                    }
            }
        } catch (Exception e) {
            return "/Errores/Error404?faces-redirect=true";
        }
        return null;
    }

    public String preEditarUsuario(Usuario usuario) {
        try {
            switch (consultarUsuario(usuario).getIdRol().getIdRol()) {
                case 1:
                    return "actualizarAdministrador?faces-redirect=true";
                case 2:
                    switch (consultarCliente(usuario).getIdTipoCliente().getIdTipoCliente()) {
                        case 1:
                            return "actualizarPersonaJuridica?faces-redirect=true";
                        case 2:
                            return "actualizarPersonaNatural?faces-redirect=true";
                    }
                case 3:
                    switch (consultarEmpleado(usuario).getIdTipoEmpleado().getIdTipoEmpleado()) {
                        case 1:
                            return "actualizarEmpleado?faces-redirect=true";
                        case 2:
                            consultarInfluenciador(usuario);
                            return "actualizarInfluencer?faces-redirect=true";
                        case 3:
                            return "actualizarEmpleado?faces-redirect=true";
                        case 4:
                            return "actualizarEmpleado?faces-redirect=true";
                        case 5:
                            consultarPrensa(usuario);
                            return "actualizarPrensa?faces-redirect=true";
                    }
            }
        } catch (Exception e) {
            return "/Errores/Error404?faces-redirect=true";
        }
        return null;
    }

    public boolean edicionUsuario() { //Se usa para editar cualquier usuario
        try {
            usuario.setIdTipoIdentificacion(tipoIdentificacionFacade.find(tipoIdentificacion.getIdTipoIdentificacion()));
            usuario.setIdDepartamento(departamentoFacade.find(departamento.getIdDepartamento()));
            usuario.setIdEstadoUsuario(estadoUsuarioFacade.find(estadoUsuario.getIdEstadoUsuario()));
            usuarioFacade.edit(usuario);
            return true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo editar el usuario."));
        }
        return false;
    }

    public void editarAdministrador() {
        try {
            if (edicionUsuario() == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha editado exitosamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ocurrio un error."));
        }
    }

    public void editarCliente() {
        try {
            if (edicionUsuario() == true) {
                valoresCliente();
                clienteFacade.edit(cliente);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha editado exitosamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ocurrio un error."));
        }
    }

    public boolean edicionEmpleado() {
        try {
            if (edicionUsuario() == true) {
                edicionUsuario();
                valoresEmpleado();
                empleadoFacade.edit(empleado);
                return true;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Los datos del usuario no están disponibles."));
        }
        return false;
    }

    public void editarEmpleado() {
        try {
            if (edicionEmpleado() == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha editado exitosamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo editar el usuario."));
        }
    }

    public void editarInfluenciador() {
        try {
            if (edicionEmpleado() == true) {
                valoresInfluenciador();
                influenciadorFacade.edit(influenciador);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha editado exitosamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo editar el usuario."));
        }
    }

    public void editarPrensa() {
        try {
            if (edicionEmpleado() == true) {
                valoresPrensa();
                prensaFacade.edit(prensa);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha editado exitosamente."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo editar el usuario."));
        }
    }

    public String deshabilitarBoton(Usuario usuario) {
        if (usuario.getIdEstadoUsuario().getIdEstadoUsuario() == 1) {
            return "false";
        } else {
            return "true";
        }
    }

    public String inactivarUsuario(Usuario usuario) {
        try {
            usuario.setIdEstadoUsuario(estadoUsuarioFacade.find((short) 2));
            usuarioFacade.edit(usuario);
            return "usuario?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioFacade.findAll();
    }
}
