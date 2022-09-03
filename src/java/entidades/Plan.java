/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p")
    , @NamedQuery(name = "Plan.findByIdPlan", query = "SELECT p FROM Plan p WHERE p.idPlan = :idPlan")
    , @NamedQuery(name = "Plan.findByRutaImagen", query = "SELECT p FROM Plan p WHERE p.rutaImagen = :rutaImagen")
    , @NamedQuery(name = "Plan.findByNombrePlan", query = "SELECT p FROM Plan p WHERE p.nombrePlan = :nombrePlan")
    , @NamedQuery(name = "Plan.findByContenidoPrincipalPlan", query = "SELECT p FROM Plan p WHERE p.contenidoPrincipalPlan = :contenidoPrincipalPlan")
    , @NamedQuery(name = "Plan.findByPrecioPlan", query = "SELECT p FROM Plan p WHERE p.precioPlan = :precioPlan")
    , @NamedQuery(name = "Plan.findByDescripcionPlan", query = "SELECT p FROM Plan p WHERE p.descripcionPlan = :descripcionPlan")})
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlan")
    private Integer idPlan;
    @Size(max = 100)
    @Column(name = "rutaImagen")
    private String rutaImagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombrePlan")
    private String nombrePlan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "contenidoPrincipalPlan")
    private String contenidoPrincipalPlan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioPlan")
    private BigDecimal precioPlan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "descripcionPlan")
    private String descripcionPlan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.EAGER)
    private List<CotizacionPlan> cotizacionPlanList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.EAGER)
    private List<PlanServicio> planServicioList;
    @Transient
    private short cantidad;
    
    public Plan() {
    }

    public Plan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public Plan(Integer idPlan, String nombrePlan, String contenidoPrincipalPlan, BigDecimal precioPlan, String descripcionPlan) {
        this.idPlan = idPlan;
        this.nombrePlan = nombrePlan;
        this.contenidoPrincipalPlan = contenidoPrincipalPlan;
        this.precioPlan = precioPlan;
        this.descripcionPlan = descripcionPlan;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getContenidoPrincipalPlan() {
        return contenidoPrincipalPlan;
    }

    public void setContenidoPrincipalPlan(String contenidoPrincipalPlan) {
        this.contenidoPrincipalPlan = contenidoPrincipalPlan;
    }

    public BigDecimal getPrecioPlan() {
        return precioPlan;
    }

    public void setPrecioPlan(BigDecimal precioPlan) {
        this.precioPlan = precioPlan;
    }

    public String getDescripcionPlan() {
        return descripcionPlan;
    }

    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
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
    public List<PlanServicio> getPlanServicioList() {
        return planServicioList;
    }

    public void setPlanServicioList(List<PlanServicio> planServicioList) {
        this.planServicioList = planServicioList;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Plan[ idPlan=" + idPlan + " ]";
    }
    
}
