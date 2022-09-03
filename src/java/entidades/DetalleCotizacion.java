/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "detallecotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCotizacion.findAll", query = "SELECT d FROM DetalleCotizacion d")
    , @NamedQuery(name = "DetalleCotizacion.findByIdDetalleCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.idDetalleCotizacion = :idDetalleCotizacion")
    , @NamedQuery(name = "DetalleCotizacion.findByNombrePlanes", query = "SELECT d FROM DetalleCotizacion d WHERE d.nombrePlanes = :nombrePlanes")
    , @NamedQuery(name = "DetalleCotizacion.findByCantidadTotalPlanes", query = "SELECT d FROM DetalleCotizacion d WHERE d.cantidadTotalPlanes = :cantidadTotalPlanes")
    , @NamedQuery(name = "DetalleCotizacion.findByPrecioTotalPlanes", query = "SELECT d FROM DetalleCotizacion d WHERE d.precioTotalPlanes = :precioTotalPlanes")
    , @NamedQuery(name = "DetalleCotizacion.findByFechaInicialPrestacionServicios", query = "SELECT d FROM DetalleCotizacion d WHERE d.fechaInicialPrestacionServicios = :fechaInicialPrestacionServicios")
    , @NamedQuery(name = "DetalleCotizacion.findByFechaFinalPrestacionServicios", query = "SELECT d FROM DetalleCotizacion d WHERE d.fechaFinalPrestacionServicios = :fechaFinalPrestacionServicios")
    , @NamedQuery(name = "DetalleCotizacion.findByDescripcionCotizacion", query = "SELECT d FROM DetalleCotizacion d WHERE d.descripcionCotizacion = :descripcionCotizacion")
    , @NamedQuery(name = "DetalleCotizacion.findByFechaRegistro", query = "SELECT d FROM DetalleCotizacion d WHERE d.fechaRegistro = :fechaRegistro")})
public class DetalleCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleCotizacion")
    private Long idDetalleCotizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrePlanes")
    private String nombrePlanes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadTotalPlanes")
    private Short cantidadTotalPlanes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioTotalPlanes")
    private BigDecimal precioTotalPlanes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicialPrestacionServicios")
    @Temporal(TemporalType.DATE)
    private Date fechaInicialPrestacionServicios;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaFinalPrestacionServicios")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalPrestacionServicios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcionCotizacion")
    private String descripcionCotizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion idCotizacion;

    public DetalleCotizacion() {
    }

    public DetalleCotizacion(Long idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public DetalleCotizacion(Long idDetalleCotizacion, String nombrePlanes, Short cantidadTotalPlanes, BigDecimal precioTotalPlanes, Date fechaInicialPrestacionServicios, Date fechaFinalPrestacionServicios, String descripcionCotizacion, Date fechaRegistro) {
        this.idDetalleCotizacion = idDetalleCotizacion;
        this.nombrePlanes = nombrePlanes;
        this.cantidadTotalPlanes = cantidadTotalPlanes;
        this.precioTotalPlanes = precioTotalPlanes;
        this.fechaInicialPrestacionServicios = fechaInicialPrestacionServicios;
        this.fechaFinalPrestacionServicios = fechaFinalPrestacionServicios;
        this.descripcionCotizacion = descripcionCotizacion;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDetalleCotizacion() {
        return idDetalleCotizacion;
    }

    public void setIdDetalleCotizacion(Long idDetalleCotizacion) {
        this.idDetalleCotizacion = idDetalleCotizacion;
    }

    public String getNombrePlanes() {
        return nombrePlanes;
    }

    public void setNombrePlanes(String nombrePlanes) {
        this.nombrePlanes = nombrePlanes;
    }

    public Short getCantidadTotalPlanes() {
        return cantidadTotalPlanes;
    }

    public void setCantidadTotalPlanes(Short cantidadTotalPlanes) {
        this.cantidadTotalPlanes = cantidadTotalPlanes;
    }

    public BigDecimal getPrecioTotalPlanes() {
        return precioTotalPlanes;
    }

    public void setPrecioTotalPlanes(BigDecimal precioTotalPlanes) {
        this.precioTotalPlanes = precioTotalPlanes;
    }

    public Date getFechaInicialPrestacionServicios() {
        return fechaInicialPrestacionServicios;
    }

    public void setFechaInicialPrestacionServicios(Date fechaInicialPrestacionServicios) {
        this.fechaInicialPrestacionServicios = fechaInicialPrestacionServicios;
    }

    public Date getFechaFinalPrestacionServicios() {
        return fechaFinalPrestacionServicios;
    }

    public void setFechaFinalPrestacionServicios(Date fechaFinalPrestacionServicios) {
        this.fechaFinalPrestacionServicios = fechaFinalPrestacionServicios;
    }

    public String getDescripcionCotizacion() {
        return descripcionCotizacion;
    }

    public void setDescripcionCotizacion(String descripcionCotizacion) {
        this.descripcionCotizacion = descripcionCotizacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
        hash += (idDetalleCotizacion != null ? idDetalleCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCotizacion)) {
            return false;
        }
        DetalleCotizacion other = (DetalleCotizacion) object;
        if ((this.idDetalleCotizacion == null && other.idDetalleCotizacion != null) || (this.idDetalleCotizacion != null && !this.idDetalleCotizacion.equals(other.idDetalleCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleCotizacion[ idDetalleCotizacion=" + idDetalleCotizacion + " ]";
    }
    
}
