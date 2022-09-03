/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "estadoordenprestacionservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoOrdenPrestacionServicio.findAll", query = "SELECT e FROM EstadoOrdenPrestacionServicio e")
    , @NamedQuery(name = "EstadoOrdenPrestacionServicio.findByIdEstadoOrdenPrestacionServicio", query = "SELECT e FROM EstadoOrdenPrestacionServicio e WHERE e.idEstadoOrdenPrestacionServicio = :idEstadoOrdenPrestacionServicio")
    , @NamedQuery(name = "EstadoOrdenPrestacionServicio.findByEstadoOrdenPrestacionServicio", query = "SELECT e FROM EstadoOrdenPrestacionServicio e WHERE e.estadoOrdenPrestacionServicio = :estadoOrdenPrestacionServicio")})
public class EstadoOrdenPrestacionServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoOrdenPrestacionServicio")
    private Short idEstadoOrdenPrestacionServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estadoOrdenPrestacionServicio")
    private String estadoOrdenPrestacionServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoOrdenPrestacionServicio", fetch = FetchType.EAGER)
    private List<OrdenPrestacionServicio> ordenPrestacionServicioList;

    public EstadoOrdenPrestacionServicio() {
    }

    public EstadoOrdenPrestacionServicio(Short idEstadoOrdenPrestacionServicio) {
        this.idEstadoOrdenPrestacionServicio = idEstadoOrdenPrestacionServicio;
    }

    public EstadoOrdenPrestacionServicio(Short idEstadoOrdenPrestacionServicio, String estadoOrdenPrestacionServicio) {
        this.idEstadoOrdenPrestacionServicio = idEstadoOrdenPrestacionServicio;
        this.estadoOrdenPrestacionServicio = estadoOrdenPrestacionServicio;
    }

    public Short getIdEstadoOrdenPrestacionServicio() {
        return idEstadoOrdenPrestacionServicio;
    }

    public void setIdEstadoOrdenPrestacionServicio(Short idEstadoOrdenPrestacionServicio) {
        this.idEstadoOrdenPrestacionServicio = idEstadoOrdenPrestacionServicio;
    }

    public String getEstadoOrdenPrestacionServicio() {
        return estadoOrdenPrestacionServicio;
    }

    public void setEstadoOrdenPrestacionServicio(String estadoOrdenPrestacionServicio) {
        this.estadoOrdenPrestacionServicio = estadoOrdenPrestacionServicio;
    }

    @XmlTransient
    @JsonIgnore
    public List<OrdenPrestacionServicio> getOrdenPrestacionServicioList() {
        return ordenPrestacionServicioList;
    }

    public void setOrdenPrestacionServicioList(List<OrdenPrestacionServicio> ordenPrestacionServicioList) {
        this.ordenPrestacionServicioList = ordenPrestacionServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoOrdenPrestacionServicio != null ? idEstadoOrdenPrestacionServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOrdenPrestacionServicio)) {
            return false;
        }
        EstadoOrdenPrestacionServicio other = (EstadoOrdenPrestacionServicio) object;
        if ((this.idEstadoOrdenPrestacionServicio == null && other.idEstadoOrdenPrestacionServicio != null) || (this.idEstadoOrdenPrestacionServicio != null && !this.idEstadoOrdenPrestacionServicio.equals(other.idEstadoOrdenPrestacionServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoOrdenPrestacionServicio[ idEstadoOrdenPrestacionServicio=" + idEstadoOrdenPrestacionServicio + " ]";
    }
    
}
