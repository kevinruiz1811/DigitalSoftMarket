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
@Table(name = "tiponicho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNicho.findAll", query = "SELECT t FROM TipoNicho t")
    , @NamedQuery(name = "TipoNicho.findByIdTipoNicho", query = "SELECT t FROM TipoNicho t WHERE t.idTipoNicho = :idTipoNicho")
    , @NamedQuery(name = "TipoNicho.findByTipoNicho", query = "SELECT t FROM TipoNicho t WHERE t.tipoNicho = :tipoNicho")})
public class TipoNicho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoNicho")
    private Short idTipoNicho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoNicho")
    private String tipoNicho;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoNicho", fetch = FetchType.EAGER)
    private List<Influenciador> influenciadorList;

    public TipoNicho() {
    }

    public TipoNicho(Short idTipoNicho) {
        this.idTipoNicho = idTipoNicho;
    }

    public TipoNicho(Short idTipoNicho, String tipoNicho) {
        this.idTipoNicho = idTipoNicho;
        this.tipoNicho = tipoNicho;
    }

    public Short getIdTipoNicho() {
        return idTipoNicho;
    }

    public void setIdTipoNicho(Short idTipoNicho) {
        this.idTipoNicho = idTipoNicho;
    }

    public String getTipoNicho() {
        return tipoNicho;
    }

    public void setTipoNicho(String tipoNicho) {
        this.tipoNicho = tipoNicho;
    }

    @XmlTransient
    @JsonIgnore
    public List<Influenciador> getInfluenciadorList() {
        return influenciadorList;
    }

    public void setInfluenciadorList(List<Influenciador> influenciadorList) {
        this.influenciadorList = influenciadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoNicho != null ? idTipoNicho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNicho)) {
            return false;
        }
        TipoNicho other = (TipoNicho) object;
        if ((this.idTipoNicho == null && other.idTipoNicho != null) || (this.idTipoNicho != null && !this.idTipoNicho.equals(other.idTipoNicho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoNicho[ idTipoNicho=" + idTipoNicho + " ]";
    }
    
}
