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
@Table(name = "influenciador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Influenciador.findAll", query = "SELECT i FROM Influenciador i")
    , @NamedQuery(name = "Influenciador.findByIdInfluenciador", query = "SELECT i FROM Influenciador i WHERE i.idInfluenciador = :idInfluenciador")
    , @NamedQuery(name = "Influenciador.findByCantidadSeguidoresPrincipal", query = "SELECT i FROM Influenciador i WHERE i.cantidadSeguidoresPrincipal = :cantidadSeguidoresPrincipal")
    , @NamedQuery(name = "Influenciador.findByNombreUsuarioPrincipal", query = "SELECT i FROM Influenciador i WHERE i.nombreUsuarioPrincipal = :nombreUsuarioPrincipal")
    , @NamedQuery(name = "Influenciador.findByCantidadSeguidoresSecundaria", query = "SELECT i FROM Influenciador i WHERE i.cantidadSeguidoresSecundaria = :cantidadSeguidoresSecundaria")
    , @NamedQuery(name = "Influenciador.findByNombreUsuarioSecundaria", query = "SELECT i FROM Influenciador i WHERE i.nombreUsuarioSecundaria = :nombreUsuarioSecundaria")})
public class Influenciador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInfluenciador")
    private Long idInfluenciador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadSeguidoresPrincipal")
    private Integer cantidadSeguidoresPrincipal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreUsuarioPrincipal")
    private String nombreUsuarioPrincipal;
    @Column(name = "cantidadSeguidoresSecundaria")
    private Integer cantidadSeguidoresSecundaria;
    @Size(max = 30)
    @Column(name = "nombreUsuarioSecundaria")
    private String nombreUsuarioSecundaria;
    @JoinColumn(name = "idEmpleado", referencedColumnName = "idEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empleado idEmpleado;
    @JoinColumn(name = "idRedSocialPrincipal", referencedColumnName = "idRedSocial")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RedSocial idRedSocialPrincipal;
    @JoinColumn(name = "idRedSocialSecundaria", referencedColumnName = "idRedSocial")
    @ManyToOne(fetch = FetchType.EAGER)
    private RedSocial idRedSocialSecundaria;
    @JoinColumn(name = "idTipoNicho", referencedColumnName = "idTipoNicho")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoNicho idTipoNicho;

    public Influenciador() {
    }

    public Influenciador(Long idInfluenciador) {
        this.idInfluenciador = idInfluenciador;
    }

    public Influenciador(Long idInfluenciador, Integer cantidadSeguidoresPrincipal, String nombreUsuarioPrincipal) {
        this.idInfluenciador = idInfluenciador;
        this.cantidadSeguidoresPrincipal = cantidadSeguidoresPrincipal;
        this.nombreUsuarioPrincipal = nombreUsuarioPrincipal;
    }

    public Long getIdInfluenciador() {
        return idInfluenciador;
    }

    public void setIdInfluenciador(Long idInfluenciador) {
        this.idInfluenciador = idInfluenciador;
    }

    public Integer getCantidadSeguidoresPrincipal() {
        return cantidadSeguidoresPrincipal;
    }

    public void setCantidadSeguidoresPrincipal(Integer cantidadSeguidoresPrincipal) {
        this.cantidadSeguidoresPrincipal = cantidadSeguidoresPrincipal;
    }

    public String getNombreUsuarioPrincipal() {
        return nombreUsuarioPrincipal;
    }

    public void setNombreUsuarioPrincipal(String nombreUsuarioPrincipal) {
        this.nombreUsuarioPrincipal = nombreUsuarioPrincipal;
    }

    public Integer getCantidadSeguidoresSecundaria() {
        return cantidadSeguidoresSecundaria;
    }

    public void setCantidadSeguidoresSecundaria(Integer cantidadSeguidoresSecundaria) {
        this.cantidadSeguidoresSecundaria = cantidadSeguidoresSecundaria;
    }

    public String getNombreUsuarioSecundaria() {
        return nombreUsuarioSecundaria;
    }

    public void setNombreUsuarioSecundaria(String nombreUsuarioSecundaria) {
        this.nombreUsuarioSecundaria = nombreUsuarioSecundaria;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public RedSocial getIdRedSocialPrincipal() {
        return idRedSocialPrincipal;
    }

    public void setIdRedSocialPrincipal(RedSocial idRedSocialPrincipal) {
        this.idRedSocialPrincipal = idRedSocialPrincipal;
    }

    public RedSocial getIdRedSocialSecundaria() {
        return idRedSocialSecundaria;
    }

    public void setIdRedSocialSecundaria(RedSocial idRedSocialSecundaria) {
        this.idRedSocialSecundaria = idRedSocialSecundaria;
    }

    public TipoNicho getIdTipoNicho() {
        return idTipoNicho;
    }

    public void setIdTipoNicho(TipoNicho idTipoNicho) {
        this.idTipoNicho = idTipoNicho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfluenciador != null ? idInfluenciador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Influenciador)) {
            return false;
        }
        Influenciador other = (Influenciador) object;
        if ((this.idInfluenciador == null && other.idInfluenciador != null) || (this.idInfluenciador != null && !this.idInfluenciador.equals(other.idInfluenciador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Influenciador[ idInfluenciador=" + idInfluenciador + " ]";
    }
    
}
