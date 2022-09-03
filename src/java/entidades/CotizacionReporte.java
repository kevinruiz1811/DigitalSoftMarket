package entidades;

import java.math.BigDecimal;

public class CotizacionReporte {
    
    private Integer cantidadCotizacionesMes; //cantidadCotizacionesTotal;
    private BigDecimal totalCotizacionesMes; //totalCotizaciones;
    private String mes, ano;

    public Integer getCantidadCotizacionesMes() {
        return cantidadCotizacionesMes;
    }

    public void setCantidadCotizacionesMes(Integer cantidadCotizacionesMes) {
        this.cantidadCotizacionesMes = cantidadCotizacionesMes;
    }

    /*public Integer getCantidadCotizacionesTotal() {
        return cantidadCotizacionesTotal;
    }

    public void setCantidadCotizacionesTotal(Integer cantidadCotizacionesTotal) {
        this.cantidadCotizacionesTotal = cantidadCotizacionesTotal;
    }*/

    public BigDecimal getTotalCotizacionesMes() {
        return totalCotizacionesMes;
    }

    public void setTotalCotizacionesMes(BigDecimal totalCotizacionesMes) {
        this.totalCotizacionesMes = totalCotizacionesMes;
    }

    /*public BigDecimal getTotalCotizaciones() {
        return totalCotizaciones;
    }

    public void setTotalCotizaciones(BigDecimal totalCotizaciones) {
        this.totalCotizaciones = totalCotizaciones;
    }*/

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}