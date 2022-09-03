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
@Table(name = "estadousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoUsuario.findAll", query = "SELECT e FROM EstadoUsuario e")
    , @NamedQuery(name = "EstadoUsuario.findByIdEstadoUsuario", query = "SELECT e FROM EstadoUsuario e WHERE e.idEstadoUsuario = :idEstadoUsuario")
    , @NamedQuery(name = "EstadoUsuario.findByEstadoUsuario", query = "SELECT e FROM EstadoUsuario e WHERE e.estadoUsuario = :estadoUsuario")})
public class EstadoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEstadoUsuario")
    private Short idEstadoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estadoUsuario")
    private String estadoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoUsuario", fetch = FetchType.EAGER)
    private List<Usuario> usuarioList;

    public EstadoUsuario() {
    }

    public EstadoUsuario(Short idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public EstadoUsuario(Short idEstadoUsuario, String estadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public Short getIdEstadoUsuario() {
        return idEstadoUsuario;
    }

    public void setIdEstadoUsuario(Short idEstadoUsuario) {
        this.idEstadoUsuario = idEstadoUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    @XmlTransient
    @JsonIgnore
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoUsuario != null ? idEstadoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoUsuario)) {
            return false;
        }
        EstadoUsuario other = (EstadoUsuario) object;
        if ((this.idEstadoUsuario == null && other.idEstadoUsuario != null) || (this.idEstadoUsuario != null && !this.idEstadoUsuario.equals(other.idEstadoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoUsuario[ idEstadoUsuario=" + idEstadoUsuario + " ]";
    }
    
}
