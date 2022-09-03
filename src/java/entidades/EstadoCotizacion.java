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
@Table(name = "estadocotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCotizacion.findAll", query = "SELECT e FROM EstadoCotizacion e")
    , @NamedQuery(name = "EstadoCotizacion.findByIdEstadoCotizacion", query = "SELECT e FROM EstadoCotizacion e WHERE e.idEstadoCotizacion = :idEstadoCotizacion")
    , @NamedQuery(name = "EstadoCotizacion.findByEstadoCotizacion", query = "SELECT e FROM EstadoCotizacion e WHERE e.estadoCotizacion = :estadoCotizacion")})
public class EstadoCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoCotizacion")
    private Short idEstadoCotizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estadoCotizacion")
    private String estadoCotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCotizacion", fetch = FetchType.EAGER)
    private List<Cotizacion> cotizacionList;

    public EstadoCotizacion() {
    }

    public EstadoCotizacion(Short idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public EstadoCotizacion(Short idEstadoCotizacion, String estadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
        this.estadoCotizacion = estadoCotizacion;
    }

    public Short getIdEstadoCotizacion() {
        return idEstadoCotizacion;
    }

    public void setIdEstadoCotizacion(Short idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    public String getEstadoCotizacion() {
        return estadoCotizacion;
    }

    public void setEstadoCotizacion(String estadoCotizacion) {
        this.estadoCotizacion = estadoCotizacion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCotizacion != null ? idEstadoCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCotizacion)) {
            return false;
        }
        EstadoCotizacion other = (EstadoCotizacion) object;
        if ((this.idEstadoCotizacion == null && other.idEstadoCotizacion != null) || (this.idEstadoCotizacion != null && !this.idEstadoCotizacion.equals(other.idEstadoCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoCotizacion[ idEstadoCotizacion=" + idEstadoCotizacion + " ]";
    }
    
}
