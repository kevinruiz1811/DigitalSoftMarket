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
@Table(name = "asignacionempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionEmpleado.findAll", query = "SELECT a FROM AsignacionEmpleado a")
    , @NamedQuery(name = "AsignacionEmpleado.findByIdAsignacionEmpleado", query = "SELECT a FROM AsignacionEmpleado a WHERE a.idAsignacionEmpleado = :idAsignacionEmpleado")
    , @NamedQuery(name = "AsignacionEmpleado.findByDescripcionAsignacion", query = "SELECT a FROM AsignacionEmpleado a WHERE a.descripcionAsignacion = :descripcionAsignacion")
    , @NamedQuery(name = "AsignacionEmpleado.findByFechaRegistro", query = "SELECT a FROM AsignacionEmpleado a WHERE a.fechaRegistro = :fechaRegistro")})
public class AsignacionEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsignacionEmpleado")
    private Long idAsignacionEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcionAsignacion")
    private String descripcionAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "idOrdenPrestacionServicio", referencedColumnName = "idOrdenPrestacionServicio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrdenPrestacionServicio idOrdenPrestacionServicio;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignacionEmpleado", fetch = FetchType.EAGER)
    private List<AvanceEvento> avanceEventoList;

    public AsignacionEmpleado() {
    }

    public AsignacionEmpleado(Long idAsignacionEmpleado) {
        this.idAsignacionEmpleado = idAsignacionEmpleado;
    }

    public AsignacionEmpleado(Long idAsignacionEmpleado, String descripcionAsignacion, Date fechaRegistro) {
        this.idAsignacionEmpleado = idAsignacionEmpleado;
        this.descripcionAsignacion = descripcionAsignacion;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdAsignacionEmpleado() {
        return idAsignacionEmpleado;
    }

    public void setIdAsignacionEmpleado(Long idAsignacionEmpleado) {
        this.idAsignacionEmpleado = idAsignacionEmpleado;
    }

    public String getDescripcionAsignacion() {
        return descripcionAsignacion;
    }

    public void setDescripcionAsignacion(String descripcionAsignacion) {
        this.descripcionAsignacion = descripcionAsignacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public OrdenPrestacionServicio getIdOrdenPrestacionServicio() {
        return idOrdenPrestacionServicio;
    }

    public void setIdOrdenPrestacionServicio(OrdenPrestacionServicio idOrdenPrestacionServicio) {
        this.idOrdenPrestacionServicio = idOrdenPrestacionServicio;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @XmlTransient
    @JsonIgnore
    public List<AvanceEvento> getAvanceEventoList() {
        return avanceEventoList;
    }

    public void setAvanceEventoList(List<AvanceEvento> avanceEventoList) {
        this.avanceEventoList = avanceEventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacionEmpleado != null ? idAsignacionEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionEmpleado)) {
            return false;
        }
        AsignacionEmpleado other = (AsignacionEmpleado) object;
        if ((this.idAsignacionEmpleado == null && other.idAsignacionEmpleado != null) || (this.idAsignacionEmpleado != null && !this.idAsignacionEmpleado.equals(other.idAsignacionEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AsignacionEmpleado[ idAsignacionEmpleado=" + idAsignacionEmpleado + " ]";
    }
    
}
