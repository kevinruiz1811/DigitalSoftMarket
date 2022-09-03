package facades;

import entidades.Plan;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PlanFacade extends AbstractFacade<Plan> {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanFacade() {
        super(Plan.class);
    }

    public void cargarDatos(String ruta, String tabla) {
        try {
            Query query = em.createNativeQuery("LOAD DATA LOCAL INFILE '" + ruta + "' IGNORE INTO TABLE " + tabla + " FIELDS TERMINATED BY ';' ENCLOSED BY '\\\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\n'");
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
