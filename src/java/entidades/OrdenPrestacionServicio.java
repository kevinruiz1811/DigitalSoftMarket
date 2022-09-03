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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "ordenprestacionservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenPrestacionServicio.findAll", query = "SELECT o FROM OrdenPrestacionServicio o")
    , @NamedQuery(name = "OrdenPrestacionServicio.findByIdOrdenPrestacionServicio", query = "SELECT o FROM OrdenPrestacionServicio o WHERE o.idOrdenPrestacionServicio = :idOrdenPrestacionServicio")
    , @NamedQuery(name = "OrdenPrestacionServicio.findByNombreEvento", query = "SELECT o FROM OrdenPrestacionServicio o WHERE o.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "OrdenPrestacionServicio.findByDescripcionEvento", query = "SELECT o FROM OrdenPrestacionServicio o WHERE o.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "OrdenPrestacionServicio.findByFechaRegistro", query = "SELECT o FROM OrdenPrestacionServicio o WHERE o.fechaRegistro = :fechaRegistro")})
public class OrdenPrestacionServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdenPrestacionServicio")
    private Long idOrdenPrestacionServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreEvento")
    private String nombreEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcionEvento")
    private String descripcionEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "numeroContrato", referencedColumnName = "numeroContrato")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Venta numeroContrato;
    @JoinColumn(name = "idEstadoOrdenPrestacionServicio", referencedColumnName = "idEstadoOrdenPrestacionServicio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoOrdenPrestacionServicio idEstadoOrdenPrestacionServicio;
    @JoinColumn(name = "idAdministrador", referencedColumnName = "numeroIdentificacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario idAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenPrestacionServicio", fetch = FetchType.EAGER)
    private List<AsignacionEmpleado> asignacionEmpleadoList;

    public OrdenPrestacionServicio() {
    }

    public OrdenPrestacionServicio(Long idOrdenPrestacionServicio) {
        this.idOrdenPrestacionServicio = idOrdenPrestacionServicio;
    }

    public OrdenPrestacionServicio(Long idOrdenPrestacionServicio, String nombreEvento, String descripcionEvento, Date fechaRegistro) {
        this.idOrdenPrestacionServicio = idOrdenPrestacionServicio;
        this.nombreEvento = nombreEvento;
        this.descripcionEvento = descripcionEvento;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdOrdenPrestacionServicio() {
        return idOrdenPrestacionServicio;
    }

    public void setIdOrdenPrestacionServicio(Long idOrdenPrestacionServicio) {
        this.idOrdenPrestacionServicio = idOrdenPrestacionServicio;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Venta getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Venta numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public EstadoOrdenPrestacionServicio getIdEstadoOrdenPrestacionServicio() {
        return idEstadoOrdenPrestacionServicio;
    }

    public void setIdEstadoOrdenPrestacionServicio(EstadoOrdenPrestacionServicio idEstadoOrdenPrestacionServicio) {
        this.idEstadoOrdenPrestacionServicio = idEstadoOrdenPrestacionServicio;
    }

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @XmlTransient
    @JsonIgnore
    public List<AsignacionEmpleado> getAsignacionEmpleadoList() {
        return asignacionEmpleadoList;
    }

    public void setAsignacionEmpleadoList(List<AsignacionEmpleado> asignacionEmpleadoList) {
        this.asignacionEmpleadoList = asignacionEmpleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenPrestacionServicio != null ? idOrdenPrestacionServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenPrestacionServicio)) {
            return false;
        }
        OrdenPrestacionServicio other = (OrdenPrestacionServicio) object;
        if ((this.idOrdenPrestacionServicio == null && other.idOrdenPrestacionServicio != null) || (this.idOrdenPrestacionServicio != null && !this.idOrdenPrestacionServicio.equals(other.idOrdenPrestacionServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.OrdenPrestacionServicio[ idOrdenPrestacionServicio=" + idOrdenPrestacionServicio + " ]";
    }
    
}
