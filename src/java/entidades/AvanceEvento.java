/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "avanceevento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceEvento.findAll", query = "SELECT a FROM AvanceEvento a")
    , @NamedQuery(name = "AvanceEvento.findByIdAvanceEvento", query = "SELECT a FROM AvanceEvento a WHERE a.idAvanceEvento = :idAvanceEvento")
    , @NamedQuery(name = "AvanceEvento.findByDescripcionAvance", query = "SELECT a FROM AvanceEvento a WHERE a.descripcionAvance = :descripcionAvance")
    , @NamedQuery(name = "AvanceEvento.findByFechaRegistro", query = "SELECT a FROM AvanceEvento a WHERE a.fechaRegistro = :fechaRegistro")})
public class AvanceEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAvanceEvento")
    private Long idAvanceEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcionAvance")
    private String descripcionAvance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "idAsignacionEmpleado", referencedColumnName = "idAsignacionEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AsignacionEmpleado idAsignacionEmpleado;

    public AvanceEvento() {
    }

    public AvanceEvento(Long idAvanceEvento) {
        this.idAvanceEvento = idAvanceEvento;
    }

    public AvanceEvento(Long idAvanceEvento, String descripcionAvance, Date fechaRegistro) {
        this.idAvanceEvento = idAvanceEvento;
        this.descripcionAvance = descripcionAvance;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdAvanceEvento() {
        return idAvanceEvento;
    }

    public void setIdAvanceEvento(Long idAvanceEvento) {
        this.idAvanceEvento = idAvanceEvento;
    }

    public String getDescripcionAvance() {
        return descripcionAvance;
    }

    public void setDescripcionAvance(String descripcionAvance) {
        this.descripcionAvance = descripcionAvance;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public AsignacionEmpleado getIdAsignacionEmpleado() {
        return idAsignacionEmpleado;
    }

    public void setIdAsignacionEmpleado(AsignacionEmpleado idAsignacionEmpleado) {
        this.idAsignacionEmpleado = idAsignacionEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvanceEvento != null ? idAvanceEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvanceEvento)) {
            return false;
        }
        AvanceEvento other = (AvanceEvento) object;
        if ((this.idAvanceEvento == null && other.idAvanceEvento != null) || (this.idAvanceEvento != null && !this.idAvanceEvento.equals(other.idAvanceEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AvanceEvento[ idAvanceEvento=" + idAvanceEvento + " ]";
    }
    
}
