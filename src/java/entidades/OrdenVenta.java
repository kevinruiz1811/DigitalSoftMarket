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
@Table(name = "ordenventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenVenta.findAll", query = "SELECT o FROM OrdenVenta o")
    , @NamedQuery(name = "OrdenVenta.findByIdOrdenVenta", query = "SELECT o FROM OrdenVenta o WHERE o.idOrdenVenta = :idOrdenVenta")
    , @NamedQuery(name = "OrdenVenta.findByFechaPago", query = "SELECT o FROM OrdenVenta o WHERE o.fechaPago = :fechaPago")
    , @NamedQuery(name = "OrdenVenta.findByNumeroCuota", query = "SELECT o FROM OrdenVenta o WHERE o.numeroCuota = :numeroCuota")
    , @NamedQuery(name = "OrdenVenta.findByNombreEmpresaConsignar", query = "SELECT o FROM OrdenVenta o WHERE o.nombreEmpresaConsignar = :nombreEmpresaConsignar")
    , @NamedQuery(name = "OrdenVenta.findByNumeroCuentaBancariaConsignar", query = "SELECT o FROM OrdenVenta o WHERE o.numeroCuentaBancariaConsignar = :numeroCuentaBancariaConsignar")
    , @NamedQuery(name = "OrdenVenta.findByEstadoOrdenVenta", query = "SELECT o FROM OrdenVenta o WHERE o.estadoOrdenVenta = :estadoOrdenVenta")
    , @NamedQuery(name = "OrdenVenta.findByEstadoEnvioOrdenVenta", query = "SELECT o FROM OrdenVenta o WHERE o.estadoEnvioOrdenVenta = :estadoEnvioOrdenVenta")
    , @NamedQuery(name = "OrdenVenta.findByFechaRegistro", query = "SELECT o FROM OrdenVenta o WHERE o.fechaRegistro = :fechaRegistro")})
public class OrdenVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdenVenta")
    private Long idOrdenVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaPago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroCuota")
    private Short numeroCuota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreEmpresaConsignar")
    private String nombreEmpresaConsignar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numeroCuentaBancariaConsignar")
    private String numeroCuentaBancariaConsignar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estadoOrdenVenta")
    private String estadoOrdenVenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "estadoEnvioOrdenVenta")
    private String estadoEnvioOrdenVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenVenta", fetch = FetchType.EAGER)
    private List<Pago> pagoList;
    @JoinColumn(name = "numeroContrato", referencedColumnName = "numeroContrato")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Venta numeroContrato;

    public OrdenVenta() {
    }

    public OrdenVenta(Long idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public OrdenVenta(Long idOrdenVenta, Date fechaPago, Short numeroCuota, String nombreEmpresaConsignar, String numeroCuentaBancariaConsignar, String estadoOrdenVenta, String estadoEnvioOrdenVenta, Date fechaRegistro) {
        this.idOrdenVenta = idOrdenVenta;
        this.fechaPago = fechaPago;
        this.numeroCuota = numeroCuota;
        this.nombreEmpresaConsignar = nombreEmpresaConsignar;
        this.numeroCuentaBancariaConsignar = numeroCuentaBancariaConsignar;
        this.estadoOrdenVenta = estadoOrdenVenta;
        this.estadoEnvioOrdenVenta = estadoEnvioOrdenVenta;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdOrdenVenta() {
        return idOrdenVenta;
    }

    public void setIdOrdenVenta(Long idOrdenVenta) {
        this.idOrdenVenta = idOrdenVenta;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Short getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Short numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getNombreEmpresaConsignar() {
        return nombreEmpresaConsignar;
    }

    public void setNombreEmpresaConsignar(String nombreEmpresaConsignar) {
        this.nombreEmpresaConsignar = nombreEmpresaConsignar;
    }

    public String getNumeroCuentaBancariaConsignar() {
        return numeroCuentaBancariaConsignar;
    }

    public void setNumeroCuentaBancariaConsignar(String numeroCuentaBancariaConsignar) {
        this.numeroCuentaBancariaConsignar = numeroCuentaBancariaConsignar;
    }

    public String getEstadoOrdenVenta() {
        return estadoOrdenVenta;
    }

    public void setEstadoOrdenVenta(String estadoOrdenVenta) {
        this.estadoOrdenVenta = estadoOrdenVenta;
    }

    public String getEstadoEnvioOrdenVenta() {
        return estadoEnvioOrdenVenta;
    }

    public void setEstadoEnvioOrdenVenta(String estadoEnvioOrdenVenta) {
        this.estadoEnvioOrdenVenta = estadoEnvioOrdenVenta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    @JsonIgnore
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
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
        hash += (idOrdenVenta != null ? idOrdenVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenVenta)) {
            return false;
        }
        OrdenVenta other = (OrdenVenta) object;
        if ((this.idOrdenVenta == null && other.idOrdenVenta != null) || (this.idOrdenVenta != null && !this.idOrdenVenta.equals(other.idOrdenVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.OrdenVenta[ idOrdenVenta=" + idOrdenVenta + " ]";
    }
    
}
