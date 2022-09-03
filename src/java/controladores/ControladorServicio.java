package controladores;

import entidades.Servicio;
import facades.PlanFacade;
import facades.ServicioFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

@Named(value = "controladorServicio")
@SessionScoped
public class ControladorServicio implements Serializable {

    public ControladorServicio() {
    }

    private Servicio servicio;
    private UploadedFile file;
    private String nombre, ruta;
    private List<Servicio> listaServicio;

    @EJB
    private ServicioFacade servicioFacade;
    @EJB
    private PlanFacade planFacade;

    @PostConstruct
    public void init() {
        servicio = new Servicio();
        listaServicio = new ArrayList<>();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public void limpiarServicio() {
        servicio = new Servicio();
    }

    public void registrarServicio() throws IOException {
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

                servicio.setRutaImagen(ruta);
                servicioFacade.create(servicio);
                limpiarServicio();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El servicio se ha registado correctamente"));
            } else {
                servicio.setRutaImagen("");
                servicioFacade.create(servicio);
                limpiarServicio();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El servicio se ha registado correctamente, te recomendamos que le coloques una imagen al servicio lo más pronto posible."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public List<Servicio> listarServicio() {
        return servicioFacade.findAll();
    }

    public String preEditarServicio(Servicio servicio) {
        this.servicio = servicio;
        return "modificarServicios?faces-redirect=true";
    }

    public void editarServicio() {
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

                servicio.setRutaImagen(ruta);
                servicioFacade.edit(servicio);
            } else {
                servicio.setRutaImagen(servicio.getRutaImagen());
                servicioFacade.edit(servicio);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El servicio se ha modficado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }

    public String eliminarServicio(Servicio servicio) {
        this.servicio = servicio;
        servicioFacade.remove(servicio);
        listarServicio();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El servicio se ha eliminado correctamente."));
        return "servicios?faces-redirect=true";
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
            planFacade.cargarDatos(path1, "servicio");
            archivo.delete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Los datos se han subido exitosamente"));
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Ha ocurrido un error"));
        }
    }
}
