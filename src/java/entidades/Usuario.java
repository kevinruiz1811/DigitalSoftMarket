/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByNumeroIdentificacion", query = "SELECT u FROM Usuario u WHERE u.numeroIdentificacion = :numeroIdentificacion")
    , @NamedQuery(name = "Usuario.findByRutaImagen", query = "SELECT u FROM Usuario u WHERE u.rutaImagen = :rutaImagen")
    , @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByCorreoElectronico", query = "SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "Usuario.findByNumeroCelular", query = "SELECT u FROM Usuario u WHERE u.numeroCelular = :numeroCelular")
    , @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numeroIdentificacion")
    private String numeroIdentificacion;
    @Size(max = 100)
    @Column(name = "rutaImagen")
    private String rutaImagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numeroCelular")
    private String numeroCelular;
    @Lob
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdministrador", fetch = FetchType.EAGER)
    private List<OrdenPrestacionServicio> ordenPrestacionServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacion", fetch = FetchType.EAGER)
    private List<Empleado> empleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdministrador", fetch = FetchType.EAGER)
    private List<RespuestaCotizacion> respuestaCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacion", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;
    @JoinColumn(name = "idDepartamento", referencedColumnName = "idDepartamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Departamento idDepartamento;
    @JoinColumn(name = "idTipoIdentificacion", referencedColumnName = "idTipoIdentificacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoIdentificacion idTipoIdentificacion;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol idRol;
    @JoinColumn(name = "idEstadoUsuario", referencedColumnName = "idEstadoUsuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoUsuario idEstadoUsuario;

    public Usuario() {
    }

    public Usuario(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Usuario(String numeroIdentificacion, String nombres, String apellidos, String correoElectronico, String numeroCelular, Date fechaRegistro) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.numeroCelular = numeroCelular;
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    @JsonIgnore
    public List<OrdenPrestacionServicio> getOrdenPrestacionServicioList() {
        return ordenPrestacionServicioList;
    }

    public void setOrdenPrestacionServicioList(List<OrdenPrestacionServicio> ordenPrestacionServicioList) {
        this.ordenPrestacionServicioList = ordenPrestacionServicioList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @XmlTransient
    @JsonIgnore
    public List<RespuestaCotizacion> getRespuestaCotizacionList() {
        return respuestaCotizacionList;
    }

    public void setRespuestaCotizacionList(List<RespuestaCotizacion> respuestaCotizacionList) {
        this.respuestaCotizacionList = respuestaCotizacionList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public TipoIdentificacion getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(TipoIdentificacion idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public EstadoUsuario getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(EstadoUsuario idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroIdentificacion != null ? numeroIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.numeroIdentificacion == null && other.numeroIdentificacion != null) || (this.numeroIdentificacion != null && !this.numeroIdentificacion.equals(other.numeroIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ numeroIdentificacion=" + numeroIdentificacion + " ]";
    }
    
}
