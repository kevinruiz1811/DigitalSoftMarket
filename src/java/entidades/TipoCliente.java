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
@Table(name = "tipocliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCliente.findAll", query = "SELECT t FROM TipoCliente t")
    , @NamedQuery(name = "TipoCliente.findByIdTipoCliente", query = "SELECT t FROM TipoCliente t WHERE t.idTipoCliente = :idTipoCliente")
    , @NamedQuery(name = "TipoCliente.findByTipoCliente", query = "SELECT t FROM TipoCliente t WHERE t.tipoCliente = :tipoCliente")})
public class TipoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoCliente")
    private Short idTipoCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoCliente")
    private String tipoCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCliente", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;

    public TipoCliente() {
    }

    public TipoCliente(Short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public TipoCliente(Short idTipoCliente, String tipoCliente) {
        this.idTipoCliente = idTipoCliente;
        this.tipoCliente = tipoCliente;
    }

    public Short getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(Short idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @XmlTransient
    @JsonIgnore
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCliente != null ? idTipoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCliente)) {
            return false;
        }
        TipoCliente other = (TipoCliente) object;
        if ((this.idTipoCliente == null && other.idTipoCliente != null) || (this.idTipoCliente != null && !this.idTipoCliente.equals(other.idTipoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoCliente[ idTipoCliente=" + idTipoCliente + " ]";
    }
    
}
