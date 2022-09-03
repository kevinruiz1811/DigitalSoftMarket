package entidades;

import java.math.BigDecimal;

public class VentaReporte {

    private Integer cantidadVentasMes; //cantidadVentasTotal;
    private BigDecimal totalVentasMes; //totalVentas;
    private String mes, ano;

    public Integer getCantidadVentasMes() {
        return cantidadVentasMes;
    }

    public void setCantidadVentasMes(Integer cantidadVentasMes) {
        this.cantidadVentasMes = cantidadVentasMes;
    }

    /*public Integer getCantidadVentasTotal() {
        return cantidadVentasTotal;
    }

    public void setCantidadVentasTotal(Integer cantidadVentasTotal) {
        this.cantidadVentasTotal = cantidadVentasTotal;
    }*/

    public BigDecimal getTotalVentasMes() {
        return totalVentasMes;
    }

    public void setTotalVentasMes(BigDecimal totalVentasMes) {
        this.totalVentasMes = totalVentasMes;
    }

    /*public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
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