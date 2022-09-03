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
@Table(name = "redsocial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedSocial.findAll", query = "SELECT r FROM RedSocial r")
    , @NamedQuery(name = "RedSocial.findByIdRedSocial", query = "SELECT r FROM RedSocial r WHERE r.idRedSocial = :idRedSocial")
    , @NamedQuery(name = "RedSocial.findByRedSocial", query = "SELECT r FROM RedSocial r WHERE r.redSocial = :redSocial")})
public class RedSocial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRedSocial")
    private Short idRedSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "redSocial")
    private String redSocial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRedSocialPrincipal", fetch = FetchType.EAGER)
    private List<Influenciador> influenciadorList;
    @OneToMany(mappedBy = "idRedSocialSecundaria", fetch = FetchType.EAGER)
    private List<Influenciador> influenciadorList1;

    public RedSocial() {
    }

    public RedSocial(Short idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public RedSocial(Short idRedSocial, String redSocial) {
        this.idRedSocial = idRedSocial;
        this.redSocial = redSocial;
    }

    public Short getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(Short idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public String getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(String redSocial) {
        this.redSocial = redSocial;
    }

    @XmlTransient
    @JsonIgnore
    public List<Influenciador> getInfluenciadorList() {
        return influenciadorList;
    }

    public void setInfluenciadorList(List<Influenciador> influenciadorList) {
        this.influenciadorList = influenciadorList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Influenciador> getInfluenciadorList1() {
        return influenciadorList1;
    }

    public void setInfluenciadorList1(List<Influenciador> influenciadorList1) {
        this.influenciadorList1 = influenciadorList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRedSocial != null ? idRedSocial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedSocial)) {
            return false;
        }
        RedSocial other = (RedSocial) object;
        if ((this.idRedSocial == null && other.idRedSocial != null) || (this.idRedSocial != null && !this.idRedSocial.equals(other.idRedSocial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RedSocial[ idRedSocial=" + idRedSocial + " ]";
    }
    
}
