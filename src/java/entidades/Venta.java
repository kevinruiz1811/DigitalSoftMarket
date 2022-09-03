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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByNumeroContrato", query = "SELECT v FROM Venta v WHERE v.numeroContrato = :numeroContrato")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numeroContrato")
    private String numeroContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroContrato", fetch = FetchType.EAGER)
    private List<DetalleVenta> detalleVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroContrato", fetch = FetchType.EAGER)
    private List<OrdenPrestacionServicio> ordenPrestacionServicioList;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion idCotizacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroContrato", fetch = FetchType.EAGER)
    private List<OrdenVenta> ordenVentaList;

    public Venta() {
    }

    public Venta(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    @XmlTransient
    @JsonIgnore
    public List<OrdenPrestacionServicio> getOrdenPrestacionServicioList() {
        return ordenPrestacionServicioList;
    }

    public void setOrdenPrestacionServicioList(List<OrdenPrestacionServicio> ordenPrestacionServicioList) {
        this.ordenPrestacionServicioList = ordenPrestacionServicioList;
    }

    public Cotizacion getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Cotizacion idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    @XmlTransient
    @JsonIgnore
    public List<OrdenVenta> getOrdenVentaList() {
        return ordenVentaList;
    }

    public void setOrdenVentaList(List<OrdenVenta> ordenVentaList) {
        this.ordenVentaList = ordenVentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroContrato != null ? numeroContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.numeroContrato == null && other.numeroContrato != null) || (this.numeroContrato != null && !this.numeroContrato.equals(other.numeroContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Venta[ numeroContrato=" + numeroContrato + " ]";
    }
    
}
