package controladores;

import entidades.ClienteOrdenVenta;
import entidades.Departamento;
import entidades.Mail;
import entidades.TipoIdentificacion;
import entidades.Usuario;
import facades.DepartamentoFacade;
import facades.MailFacade;
import facades.OrdenVentaFacade;
import facades.TipoIdentificacionFacade;
import facades.UsuarioFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

@Named(value = "controladorLogin")
@SessionScoped
public class ControladorLogin implements Serializable {

    public ControladorLogin() {
    }

    private Usuario usuario;
    private Usuario usuarioLogueado;
    private TipoIdentificacion tipoIdentificacionLogueado;
    private Departamento departamentoLogueado;
    private Mail mail;
    private String nuevaClave, confirmarClave, claveActual;

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    @EJB
    private DepartamentoFacade departamentoFacade;
    @EJB
    private OrdenVentaFacade ordenVentaFacade;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public TipoIdentificacion getTipoIdentificacionLogueado() {
        return tipoIdentificacionLogueado;
    }

    public void setTipoIdentificacionLogueado(TipoIdentificacion tipoIdentificacionLogueado) {
        this.tipoIdentificacionLogueado = tipoIdentificacionLogueado;
    }

    public Departamento getDepartamentoLogueado() {
        return departamentoLogueado;
    }

    public void setDepartamentoLogueado(Departamento departamentoLogueado) {
        this.departamentoLogueado = departamentoLogueado;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuarioLogueado = new Usuario();
        tipoIdentificacionLogueado = new TipoIdentificacion();
        departamentoLogueado = new Departamento();
        mail = new Mail();
    }

    public void limpiar() {
        usuario = new Usuario();
        mail = new Mail();
    }

    public void login() {
        usuarioLogueado = usuarioFacade.login(usuario.getCorreoElectronico());
        if (usuarioLogueado != null) {
            String cast = usuarioFacade.desencriptar(usuario.getCorreoElectronico());
            if (usuario.getClave().equals(cast)) {
                try {
                    if (validarUsuario() == true) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogueado);
                        switch (usuarioLogueado.getIdRol().getIdRol()) {
                            case 1:
                                enviarOrdenVenta();
                                FacesContext.getCurrentInstance().getExternalContext().redirect("Interfaz/admin.xhtml");
                                break;
                            case 2:
                                FacesContext.getCurrentInstance().getExternalContext().redirect("Interfaz/cliente.xhtml");
                                break;
                            case 3:
                                FacesContext.getCurrentInstance().getExternalContext().redirect("Interfaz/empleado.xhtml");
                                break;
                            default:
                                FacesContext.getCurrentInstance().getExternalContext().redirect("Errores/error404.xhtml");
                                break;
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No puede ingresar debido a que esta Inactivado por favor contáctarse con la empresa"));
                    }
                } catch (IOException e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Página no encontrada"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Contraseña Incorrecta"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "El usuario no existe"));
        }
        limpiar();
    }

    public boolean validarUsuario() {
        if (usuarioLogueado.getIdEstadoUsuario().getIdEstadoUsuario() == 2) {
            return false;
        } else {
            tipoIdentificacionLogueado.setIdTipoIdentificacion(usuarioLogueado.getIdTipoIdentificacion().getIdTipoIdentificacion());
            departamentoLogueado.setIdDepartamento(usuarioLogueado.getIdDepartamento().getIdDepartamento());
            return true;
        }
    }

    public void enviarOrdenVenta() {
        try {
            List<ClienteOrdenVenta> listaClientes = ordenVentaFacade.consultarDatosCliente();
            for (ClienteOrdenVenta clienteOrdenVenta : listaClientes) {
                mail.setDestinatario(clienteOrdenVenta.getCorreoElectronico());
                mail.setAsunto("Orden de Venta");
                mail.setMensaje("<center><h2>Orden de Venta</h2></center><p style=\"font-size:17px; text-align:justify;\">Buen día " + clienteOrdenVenta.getNombres() + " " + clienteOrdenVenta.getApellidos() + ", esperamos que se encuentre bien el día de hoy.<br/><br/>"
                        + "Por medio del presente correo, queremos recordarle que la fecha limite de pago por los servicios prestados es <b style=\"color:blue;\">" + clienteOrdenVenta.getFechaPago() + "</b>, según lo acordado en el Número de Contrato " + clienteOrdenVenta.getNumeroContrato() + ".<br/><br/> Por lo tanto, le hacemos la invitación para que realice el pago con anticipación, el cual tiene un valor de $" + clienteOrdenVenta.getCuota() + ".<br/>"
                        + "Para más información por favor ingrese a la plataforma y consulte la orden de venta que tiene como fecha limite " + clienteOrdenVenta.getFechaPago() + "<br/>"
                        + "<br/><b>Gracias por hacer parte de nuestra comunidad.</b></p>");
                MailFacade.send("", mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
                ordenVentaFacade.editarEstadoEnvioOrdenVenta(clienteOrdenVenta.getIdOrdenVenta());
            }
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ocurrio un error."));
        }
        limpiar();
    }

    public void editarUsuarioLogueado() {
        try {
            usuarioLogueado.setIdTipoIdentificacion(tipoIdentificacionFacade.find(tipoIdentificacionLogueado.getIdTipoIdentificacion()));
            usuarioLogueado.setIdDepartamento(departamentoFacade.find(departamentoLogueado.getIdDepartamento()));
            usuarioFacade.edit(usuarioLogueado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Actualización exitosa"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo actualizar la información"));
        }
    }

    public void logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void cambiarClave() {
        try {
            if (nuevaClave.equals(confirmarClave)) {
                String cast = usuarioFacade.desencriptar(usuarioLogueado.getCorreoElectronico());
                if (claveActual.equals(cast)) {
                    usuarioLogueado.setClave(nuevaClave);
                    usuarioFacade.edit(usuarioLogueado);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La Contraseña ha sido actualizada."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "La Contraseña Actual no coincide."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Las Contraseñas no coinciden."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo actualizar la Contraseña."));
        }
        usuario = new Usuario();
    }

    public void verificarSesion() {
        try {
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (user == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public String generarClave() {
        String generarClave = UUID.randomUUID().toString().toUpperCase().substring(0, 10);
        StringBuilder stringBuilder = new StringBuilder(generarClave);
        String clave = generarClave + " " + stringBuilder.reverse().toString().toLowerCase();
        return clave;
    }

    public void validarCorreo() {
        Usuario validarUsuario = usuarioFacade.validarNumeroIdentificacion(usuario.getNumeroIdentificacion());
        try {
            if (validarUsuario != null) {
                if (validarUsuario.getCorreoElectronico().equals(usuario.getCorreoElectronico())) {
                    String clave = generarClave();
                    validarUsuario.setClave(clave);
                    mail.setRemitente("");
                    mail.setDestinatario(validarUsuario.getCorreoElectronico());
                    mail.setAsunto("Recuperación de Contraseña");
                    mail.setMensaje("<p>Estimado usuario " + validarUsuario.getNombres() + " " + validarUsuario.getApellidos() + " sus credenciales de inicio de sesión son:</p>"
                            + "<ul><li><b>Correo Electrónico: </b>" + validarUsuario.getCorreoElectronico() + "</li>"
                            + "<li><b>Contraseña: </b>" + clave + "</li></ul>"
                            + "<br>" + "<p style=\"font-weight:bold;\">Gracias por hacer parte de nuestra comunidad." + "</p>");
                    MailFacade.send(mail.getRemitente(), mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
                    usuarioFacade.edit(validarUsuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Gracias, su nueva contraseña ha sido enviada con exito."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Correo Electrónico incorrecto."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "El usuario no está registrado."));
            }
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo enviar su nueva contraseña."));
        }
        limpiar();
    }

    private UploadedFile file;
    private String nombre;
    private String ruta;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path += "\\web\\Archivos\\";
        try {
            this.nombre = usuarioLogueado.getNumeroIdentificacion() + "_" + file.getFileName();
            ruta = "/Archivos/" + nombre;
            String path1 = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo = new File(path1);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();

            if (file.getFileName() != null) {
                usuarioLogueado.setRutaImagen(ruta);
                usuarioFacade.edit(usuarioLogueado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se subio la imagen con exito"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Debe seleccionar una imagen"));
            }
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo subir la imagen"));
        }
    }

    public void formularioContactanos() {
        Usuario validarNumeroIdentificacion = usuarioFacade.validarNumeroIdentificacion(usuario.getNumeroIdentificacion());
        Usuario validarCorreoElectronico = usuarioFacade.validarCorreoElectronico(usuario.getCorreoElectronico());
        Usuario validarNumeroCelular = usuarioFacade.validarNumeroCelular(usuario.getNumeroCelular());
        try {
            if (validarNumeroIdentificacion != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un usuario con este Número de Identificación, por favor inicie sesión."));
            } else if (validarCorreoElectronico != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un usuario con este Correo Electrónico, por favor inicie sesión."));
            } else if (validarNumeroCelular != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Ya existe un usuario con este Número de Celular, por favor inicie sesión."));
            } else {
                mail.setRemitente(usuario.getNombres());
                mail.setDestinatario("digitalsoftmarket@gmail.com");
                mail.setAsunto("Formulario Contáctanos");
                mail.setMensaje("<h2>Tienes un nuevo mensaje de un posible cliente</h2>" + "<p style=\"font-size:15px; text-align:justify;\">" + mail.getMensaje() + "</p>"
                        + "<center>"
                        + "<table width=\"80%\" border=\"1\" cellspacing=\"2\" cellpadding=\"4\" style=\"border-collapse: collapse;\">"
                        + "<thead><tr align=\"center\"><td style=\"font-size: 17px; font-weight: bold;\" colspan=\"3\">Datos de Contácto</td></tr>"
                        + "<tr>"
                        + "<th>Nombre Completo</th>"
                        + "<th>Correo Electrónico</th>"
                        + "<th>Número Celular</th>"
                        + "</tr></thead>"
                        + "<tbody><tr align=\"center\">"
                        + "<td>" + usuario.getNombres() + "</td>"
                        + "<td>" + usuario.getCorreoElectronico() + "</td>"
                        + "<td>" + usuario.getNumeroCelular() + "</td>"
                        + "</tr></tbody>"
                        + "</table>"
                        + "</center>");
                MailFacade.send(mail.getRemitente(), mail.getDestinatario(), null, mail.getAsunto(), mail.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Mensaje enviado exitosamente."));
            }
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "No se pudo enviar el Mensaje."));
        }
        limpiar();
    }
}
