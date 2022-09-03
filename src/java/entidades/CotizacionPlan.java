/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "cotizacionplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CotizacionPlan.findAll", query = "SELECT c FROM CotizacionPlan c")
    , @NamedQuery(name = "CotizacionPlan.findByIdCotizacionPlan", query = "SELECT c FROM CotizacionPlan c WHERE c.idCotizacionPlan = :idCotizacionPlan")})
public class CotizacionPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCotizacionPlan")
    private Long idCotizacionPlan;
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Plan idPlan;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion idCotizacion;

    public CotizacionPlan() {
    }

    public CotizacionPlan(Long idCotizacionPlan) {
        this.idCotizacionPlan = idCotizacionPlan;
    }

    public Long getIdCotizacionPlan() {
        return idCotizacionPlan;
    }

    public void setIdCotizacionPlan(Long idCotizacionPlan) {
        this.idCotizacionPlan = idCotizacionPlan;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCotizacionPlan != null ? idCotizacionPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CotizacionPlan)) {
            return false;
        }
        CotizacionPlan other = (CotizacionPlan) object;
        if ((this.idCotizacionPlan == null && other.idCotizacionPlan != null) || (this.idCotizacionPlan != null && !this.idCotizacionPlan.equals(other.idCotizacionPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CotizacionPlan[ idCotizacionPlan=" + idCotizacionPlan + " ]";
    }
    
}
