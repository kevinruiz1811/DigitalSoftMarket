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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author CAMILO
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empleado.findByEspecialidad", query = "SELECT e FROM Empleado e WHERE e.especialidad = :especialidad")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmpleado")
    private Long idEmpleado;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "especialidad")
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado", fetch = FetchType.EAGER)
    private List<AsignacionEmpleado> asignacionEmpleadoList;
    @JoinColumn(name = "numeroIdentificacion", referencedColumnName = "numeroIdentificacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario numeroIdentificacion;
    @JoinColumn(name = "idTipoEmpleado", referencedColumnName = "idTipoEmpleado")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoEmpleado idTipoEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado", fetch = FetchType.EAGER)
    private List<Influenciador> influenciadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleado", fetch = FetchType.EAGER)
    private List<Prensa> prensaList;

    public Empleado() {
    }

    public Empleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @XmlTransient
    @JsonIgnore
    public List<AsignacionEmpleado> getAsignacionEmpleadoList() {
        return asignacionEmpleadoList;
    }

    public void setAsignacionEmpleadoList(List<AsignacionEmpleado> asignacionEmpleadoList) {
        this.asignacionEmpleadoList = asignacionEmpleadoList;
    }

    public Usuario getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Usuario numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public TipoEmpleado getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(TipoEmpleado idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
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
    public List<Prensa> getPrensaList() {
        return prensaList;
    }

    public void setPrensaList(List<Prensa> prensaList) {
        this.prensaList = prensaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
