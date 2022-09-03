package controladores;

import entidades.Departamento;
import facades.DepartamentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorDepartamento")
@SessionScoped
public class ControladorDepartamento implements Serializable {

    public ControladorDepartamento() {
    }

    private Departamento departamento;

    @EJB
    private DepartamentoFacade departamentoFacade;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @PostConstruct
    public void init() {
        departamento = new Departamento();
    }

    public List<Departamento> listarDepartamento() {
        return departamentoFacade.findAll();
    }
}