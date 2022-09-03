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
@Table(name = "detalleventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")
    , @NamedQuery(name = "DetalleVenta.findByIdDetalleVenta", query = "SELECT d FROM DetalleVenta d WHERE d.idDetalleVenta = :idDetalleVenta")
    , @NamedQuery(name = "DetalleVenta.findByFechaVenta", query = "SELECT d FROM DetalleVenta d WHERE d.fechaVenta = :fechaVenta")
    , @NamedQuery(name = "DetalleVenta.findByFechaInicialPrestacionServicios", query = "SELECT d FROM DetalleVenta d WHERE d.fechaInicialPrestacionServicios = :fechaInicialPrestacionServicios")
    , @NamedQuery(name = "DetalleVenta.findByFechaFinalPrestacionServicios", query = "SELECT d FROM DetalleVenta d WHERE d.fechaFinalPrestacionServicios = :fechaFinalPrestacionServicios")
    , @NamedQuery(name = "DetalleVenta.findByMontoCobroMensual", query = "SELECT d FROM DetalleVenta d WHERE d.montoCobroMensual = :montoCobroMensual")
    , @NamedQuery(name = "DetalleVenta.findByDescripcionVenta", query = "SELECT d FROM DetalleVenta d WHERE d.descripcionVenta = :descripcionVenta")
    , @NamedQuery(name = "DetalleVenta.findByFechaRegistro", query = "SELECT d FROM DetalleVenta d WHERE d.fechaRegistro = :fechaRegistro")})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleVenta")
    private Long idDetalleVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaVenta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "montoCobroMensual")
    private BigDecimal montoCobroMensual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcionVenta")
    private String descripcionVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "numeroContrato", referencedColumnName = "numeroContrato")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Venta numeroContrato;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public DetalleVenta(Long idDetalleVenta, Date fechaVenta, Date fechaInicialPrestacionServicios, Date fechaFinalPrestacionServicios, BigDecimal montoCobroMensual, String descripcionVenta, Date fechaRegistro) {
        this.idDetalleVenta = idDetalleVenta;
        this.fechaVenta = fechaVenta;
        this.fechaInicialPrestacionServicios = fechaInicialPrestacionServicios;
        this.fechaFinalPrestacionServicios = fechaFinalPrestacionServicios;
        this.montoCobroMensual = montoCobroMensual;
        this.descripcionVenta = descripcionVenta;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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

    public BigDecimal getMontoCobroMensual() {
        return montoCobroMensual;
    }

    public void setMontoCobroMensual(BigDecimal montoCobroMensual) {
        this.montoCobroMensual = montoCobroMensual;
    }

    public String getDescripcionVenta() {
        return descripcionVenta;
    }

    public void setDescripcionVenta(String descripcionVenta) {
        this.descripcionVenta = descripcionVenta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleVenta != null ? idDetalleVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.idDetalleVenta == null && other.idDetalleVenta != null) || (this.idDetalleVenta != null && !this.idDetalleVenta.equals(other.idDetalleVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DetalleVenta[ idDetalleVenta=" + idDetalleVenta + " ]";
    }
    
}
