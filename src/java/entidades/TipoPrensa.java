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
@Table(name = "tipoprensa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPrensa.findAll", query = "SELECT t FROM TipoPrensa t")
    , @NamedQuery(name = "TipoPrensa.findByIdTipoPrensa", query = "SELECT t FROM TipoPrensa t WHERE t.idTipoPrensa = :idTipoPrensa")
    , @NamedQuery(name = "TipoPrensa.findByTipoPrensa", query = "SELECT t FROM TipoPrensa t WHERE t.tipoPrensa = :tipoPrensa")})
public class TipoPrensa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoPrensa")
    private Short idTipoPrensa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoPrensa")
    private String tipoPrensa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPrensa", fetch = FetchType.EAGER)
    private List<Prensa> prensaList;

    public TipoPrensa() {
    }

    public TipoPrensa(Short idTipoPrensa) {
        this.idTipoPrensa = idTipoPrensa;
    }

    public TipoPrensa(Short idTipoPrensa, String tipoPrensa) {
        this.idTipoPrensa = idTipoPrensa;
        this.tipoPrensa = tipoPrensa;
    }

    public Short getIdTipoPrensa() {
        return idTipoPrensa;
    }

    public void setIdTipoPrensa(Short idTipoPrensa) {
        this.idTipoPrensa = idTipoPrensa;
    }

    public String getTipoPrensa() {
        return tipoPrensa;
    }

    public void setTipoPrensa(String tipoPrensa) {
        this.tipoPrensa = tipoPrensa;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prensa> getPrensaList() {
        return prensaList;
    }

    public void setPrensaList(List<Prensa> prensaList) {
        this.prensaList = prensaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPrensa != null ? idTipoPrensa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPrensa)) {
            return false;
        }
        TipoPrensa other = (TipoPrensa) object;
        if ((this.idTipoPrensa == null && other.idTipoPrensa != null) || (this.idTipoPrensa != null && !this.idTipoPrensa.equals(other.idTipoPrensa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoPrensa[ idTipoPrensa=" + idTipoPrensa + " ]";
    }
    
}
