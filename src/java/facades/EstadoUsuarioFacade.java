package facades;

import entidades.EstadoUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoUsuarioFacade extends AbstractFacade<EstadoUsuario> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoUsuarioFacade() {
        super(EstadoUsuario.class);
    }   
}