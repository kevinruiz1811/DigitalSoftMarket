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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "prensa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prensa.findAll", query = "SELECT p FROM Prensa p")
    , @NamedQuery(name = "Prensa.findByIdPrensa", query = "SELECT p FROM Prensa p WHERE p.idPrensa = :idPrensa")
    , @NamedQuery(name = "Prensa.findByNombrePrensa", query = "SELECT p FROM Prensa p WHERE p.nombrePrensa = :nombrePrensa")
    , @NamedQuery(name = "Prensa.findByMediosComunicacion", query = "SELECT p FROM Prensa p WHERE p.mediosComunicacion = :mediosComunicacion")})
public class Prensa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrensa")
    private Long idPrensa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombrePrensa")
    private String nombrePrensa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mediosComunicacion")
    private String mediosComunicacion;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;
    @JoinColumn(name = "idTipoPrensa", referencedColumnName = "idTipoPrensa")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoPrensa idTipoPrensa;

    public Prensa() {
    }

    public Prensa(Long idPrensa) {
        this.idPrensa = idPrensa;
    }

    public Prensa(Long idPrensa, String nombrePrensa, String mediosComunicacion) {
        this.idPrensa = idPrensa;
        this.nombrePrensa = nombrePrensa;
        this.mediosComunicacion = mediosComunicacion;
    }

    public Long getIdPrensa() {
        return idPrensa;
    }

    public void setIdPrensa(Long idPrensa) {
        this.idPrensa = idPrensa;
    }

    public String getNombrePrensa() {
        return nombrePrensa;
    }

    public void setNombrePrensa(String nombrePrensa) {
        this.nombrePrensa = nombrePrensa;
    }

    public String getMediosComunicacion() {
        return mediosComunicacion;
    }

    public void setMediosComunicacion(String mediosComunicacion) {
        this.mediosComunicacion = mediosComunicacion;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoPrensa getIdTipoPrensa() {
        return idTipoPrensa;
    }

    public void setIdTipoPrensa(TipoPrensa idTipoPrensa) {
        this.idTipoPrensa = idTipoPrensa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrensa != null ? idPrensa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prensa)) {
            return false;
        }
        Prensa other = (Prensa) object;
        if ((this.idPrensa == null && other.idPrensa != null) || (this.idPrensa != null && !this.idPrensa.equals(other.idPrensa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Prensa[ idPrensa=" + idPrensa + " ]";
    }
    
}
