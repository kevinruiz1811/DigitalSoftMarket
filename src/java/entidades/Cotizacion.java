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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByIdCotizacion", query = "SELECT c FROM Cotizacion c WHERE c.idCotizacion = :idCotizacion")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCotizacion")
    private Long idCotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCotizacion", fetch = FetchType.EAGER)
    private List<CotizacionPlan> cotizacionPlanList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCotizacion", fetch = FetchType.EAGER)
    private List<DetalleCotizacion> detalleCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCotizacion", fetch = FetchType.EAGER)
    private List<RespuestaCotizacion> respuestaCotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCotizacion", fetch = FetchType.EAGER)
    private List<Venta> ventaList;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente idCliente;
    @JoinColumn(name = "idEstadoCotizacion", referencedColumnName = "idEstadoCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoCotizacion idEstadoCotizacion;

    public Cotizacion() {
    }

    public Cotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @XmlTransient
    @JsonIgnore
    public List<CotizacionPlan> getCotizacionPlanList() {
        return cotizacionPlanList;
    }

    public void setCotizacionPlanList(List<CotizacionPlan> cotizacionPlanList) {
        this.cotizacionPlanList = cotizacionPlanList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleCotizacion> getDetalleCotizacionList() {
        return detalleCotizacionList;
    }

    public void setDetalleCotizacionList(List<DetalleCotizacion> detalleCotizacionList) {
        this.detalleCotizacionList = detalleCotizacionList;
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
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public EstadoCotizacion getIdEstadoCotizacion() {
        return idEstadoCotizacion;
    }

    public void setIdEstadoCotizacion(EstadoCotizacion idEstadoCotizacion) {
        this.idEstadoCotizacion = idEstadoCotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCotizacion != null ? idCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.idCotizacion == null && other.idCotizacion != null) || (this.idCotizacion != null && !this.idCotizacion.equals(other.idCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cotizacion[ idCotizacion=" + idCotizacion + " ]";
    }
    
}
