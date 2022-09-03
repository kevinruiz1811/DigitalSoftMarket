package facades;

import entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario login(String correoElectronico) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico");
            query.setParameter("correoElectronico", correoElectronico);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String desencriptar(String correoElectronico) {
        try {
            Query query = em.createNativeQuery("CALL desencriptar_clave('" + correoElectronico + "');");
            return (String) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Usuario validarNumeroIdentificacion(String numeroIdentificacion) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.numeroIdentificacion = :numeroIdentificacion");
            query.setParameter("numeroIdentificacion", numeroIdentificacion);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Usuario validarCorreoElectronico(String correoElectronico) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correoElectronico");
            query.setParameter("correoElectronico", correoElectronico);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Usuario validarNumeroCelular(String numeroCelular) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.numeroCelular = :numeroCelular");
            query.setParameter("numeroCelular", numeroCelular);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Usuario> consultarEmail(short idRol) {
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.idRol.idRol = :idRol");
            query.setParameter("idRol", idRol);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
