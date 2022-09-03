package controladores;

import entidades.Plan;
import facades.PlanFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

@Named(value = "controladorPlan")
@SessionScoped
public class ControladorPlan implements Serializable {

    public ControladorPlan() {
    }

    private Plan plan;
    private ArrayList<Plan> planes;
    private short idPlanABuscar;
    private UploadedFile file;
    private String nombre, ruta;
    private List<Plan> listaPlanes;

    @EJB
    private PlanFacade planFacade;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<Plan> planes) {
        this.planes = planes;
    }

    public short getIdPlanABuscar() {
        return idPlanABuscar;
    }

    public void setIdPlanABuscar(short idPlanABuscar) {
        this.idPlanABuscar = idPlanABuscar;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<Plan> getListaPlanes() {
        return listaPlanes;
    }

    public void setListaPlanes(List<Plan> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }

    @PostConstruct
    public void init() {
        plan = new Plan();
        planes = new ArrayList<>();
        listaPlanes = new ArrayList<>();
    }

    public void limpiarPlan() {
        plan = new Plan();
    }

    public void registrarPlan() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path += "\\web\\Archivos\\";
        try {
            this.nombre = file.getFileName();
            if (nombre != null) {
                ruta = "/DigitalSoftMarket/Archivos/" + nombre;
                String path1 = path + this.nombre;
                InputStream in = file.getInputStream();

                byte[] data = new byte[in.available()];
                in.read(data);
                File archivo = new File(path1);
                FileOutputStream out = new FileOutputStream(archivo);
                out.write(data);
                in.close();
                out.close();

                plan.setRutaImagen(ruta);
                planFacade.create(plan);
                limpiarPlan();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado el plan correctamente."));
            } else {
                plan.setRutaImagen("");
                planFacade.create(plan);
                limpiarPlan();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado el plan correctamente, te recomendamos que establezcas una imagen al plan lo más pronto posible."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error" + e.getMessage()));
        }
    }

    public List<Plan> listarPlan() {
        return planFacade.findAll();
    }

    public String preEditarPlan(Plan plan) {
        this.plan = plan;
        return "modificarPlanes?faces-redirect=true";
    }

    public String perVerDetallesPlan(Plan plan) {
        this.plan = plan;
        return "detallesPlan.xhtml?faces-redirect=true";
    }

    public void editarPlan() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path += "\\web\\Archivos\\";
        try {
            this.nombre = file.getFileName();
            if (nombre != null) {
                ruta = "/DigitalSoftMarket/Archivos/" + nombre;
                String path1 = path + this.nombre;
                InputStream in = file.getInputStream();

                byte[] data = new byte[in.available()];
                in.read(data);
                File archivo = new File(path1);
                FileOutputStream out = new FileOutputStream(archivo);
                out.write(data);
                in.close();
                out.close();
                plan.setRutaImagen(ruta);
                planFacade.edit(plan);
            } else {
                plan.setRutaImagen(plan.getRutaImagen());
                planFacade.edit(plan);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha modificado el plan correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public String eliminarPlan(Plan plan) {
        this.plan = plan;
        planFacade.remove(plan);
        listarPlan();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El plan se ha eliminado correctamente."));
        return "planesAdmin?faces-redirect=true";
    }

    public Plan PlanSeleccionado(Integer idPlan) {
        return planFacade.find(idPlan);
    }

    public String planId() {
        planes.clear();
        Plan plan = planFacade.find(idPlanABuscar);
        planes.add(plan);
        return "planesCliente?faces-redirect=true";
    }

    public Plan buscarPlanCarrito(Integer idPlan) {
        return planFacade.find(idPlan);
    }

    public void cargaExcel() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path += "\\web\\Archivos\\";
        try {
            this.nombre = file.getFileName();
            ruta = "/DigitalSoftMarket/Archivos/" + nombre;
            String path1 = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            File archivo = new File(path1);
            FileOutputStream out = new FileOutputStream(archivo);
            out.write(data);
            in.close();
            out.close();
            path1 = path1.replace("\\", "\\\\");
            planFacade.cargarDatos(path1, "plan");
            archivo.delete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Los datos se han subido exitosamente"));
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }
}
