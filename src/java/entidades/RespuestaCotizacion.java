/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
@Table(name = "respuestacotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaCotizacion.findAll", query = "SELECT r FROM RespuestaCotizacion r")
    , @NamedQuery(name = "RespuestaCotizacion.findByIdRespuestaCotizacion", query = "SELECT r FROM RespuestaCotizacion r WHERE r.idRespuestaCotizacion = :idRespuestaCotizacion")
    , @NamedQuery(name = "RespuestaCotizacion.findByMensaje", query = "SELECT r FROM RespuestaCotizacion r WHERE r.mensaje = :mensaje")
    , @NamedQuery(name = "RespuestaCotizacion.findByFechaRegistro", query = "SELECT r FROM RespuestaCotizacion r WHERE r.fechaRegistro = :fechaRegistro")})
public class RespuestaCotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRespuestaCotizacion")
    private Long idRespuestaCotizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "idCotizacion", referencedColumnName = "idCotizacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cotizacion idCotizacion;
    @JoinColumn(name = "idAdministrador", referencedColumnName = "numeroIdentificacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario idAdministrador;

    public RespuestaCotizacion() {
    }

    public RespuestaCotizacion(Long idRespuestaCotizacion) {
        this.idRespuestaCotizacion = idRespuestaCotizacion;
    }

    public RespuestaCotizacion(Long idRespuestaCotizacion, String mensaje, Date fechaRegistro) {
        this.idRespuestaCotizacion = idRespuestaCotizacion;
        this.mensaje = mensaje;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdRespuestaCotizacion() {
        return idRespuestaCotizacion;
    }

    public void setIdRespuestaCotizacion(Long idRespuestaCotizacion) {
        this.idRespuestaCotizacion = idRespuestaCotizacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public Usuario getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Usuario idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaCotizacion != null ? idRespuestaCotizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaCotizacion)) {
            return false;
        }
        RespuestaCotizacion other = (RespuestaCotizacion) object;
        if ((this.idRespuestaCotizacion == null && other.idRespuestaCotizacion != null) || (this.idRespuestaCotizacion != null && !this.idRespuestaCotizacion.equals(other.idRespuestaCotizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RespuestaCotizacion[ idRespuestaCotizacion=" + idRespuestaCotizacion + " ]";
    }
    
}
