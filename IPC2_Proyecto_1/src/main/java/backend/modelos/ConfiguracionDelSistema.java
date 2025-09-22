/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.math.BigDecimal;

/**
 *
 * @author helder
 */
public class ConfiguracionDelSistema {
    private int id;
    private final BigDecimal porcentajeComicion;
    private final BigDecimal precioMinimo;

    public ConfiguracionDelSistema(BigDecimal porcentajeComicion, BigDecimal precioMinimo) {
        this.porcentajeComicion = porcentajeComicion;
        this.precioMinimo = precioMinimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPorcentajeComicion() {
        return porcentajeComicion;
    }

    public BigDecimal getPrecioMinimo() {
        return precioMinimo;
    }
    
}
