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
@Table(name = "planservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanServicio.findAll", query = "SELECT p FROM PlanServicio p")
    , @NamedQuery(name = "PlanServicio.findByIdPlanServicio", query = "SELECT p FROM PlanServicio p WHERE p.idPlanServicio = :idPlanServicio")})
public class PlanServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanServicio")
    private Long idPlanServicio;
    @JoinColumn(name = "idServicio", referencedColumnName = "idServicio")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Servicio idServicio;
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Plan idPlan;

    public PlanServicio() {
    }

    public PlanServicio(Long idPlanServicio) {
        this.idPlanServicio = idPlanServicio;
    }

    public Long getIdPlanServicio() {
        return idPlanServicio;
    }

    public void setIdPlanServicio(Long idPlanServicio) {
        this.idPlanServicio = idPlanServicio;
    }

    public Servicio getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Servicio idServicio) {
        this.idServicio = idServicio;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanServicio != null ? idPlanServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanServicio)) {
            return false;
        }
        PlanServicio other = (PlanServicio) object;
        if ((this.idPlanServicio == null && other.idPlanServicio != null) || (this.idPlanServicio != null && !this.idPlanServicio.equals(other.idPlanServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PlanServicio[ idPlanServicio=" + idPlanServicio + " ]";
    }
    
}
