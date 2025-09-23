/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class Congreso {

    private int idCongreso;
    private final String idUsuario;
    private int idInstitucion;
    private final String nombre;
    private final String descripcion;
    private final String ubicacion;
    private final BigDecimal precio;
    private final boolean convocatoria;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public Congreso(String idUsuario, String nombre, String descripcion, String ubicacion, BigDecimal precio, boolean convocatoria, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.convocatoria = convocatoria;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdCongreso() {
        return idCongreso;
    }

    public void setIdCongreso(int idCongreso) {
        this.idCongreso = idCongreso;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public boolean isConvocatoria() {
        return convocatoria;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public boolean esValido() {
        return StringUtils.isBlank(nombre)
                || StringUtils.isBlank(ubicacion)
                || StringUtils.isBlank(idUsuario)
                || StringUtils.isBlank(descripcion)
                || fechaFin == null
                || fechaInicio == null
                || precio == null;
    }
    
    public boolean esValidoLaFecha(){
        return fechaFin.isBefore(fechaInicio) || fechaInicio.equals(fechaFin);
    }

}
