/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class Salon {

    private int idSalon;
    private final String nombre; 
    private final int idInstitucion;
    private final String ubicacion;
    private final int capacidad;

    public Salon(String nombre, int idInstitucion, String ubicacion, int capacidad) {
        this.nombre = nombre;
        this.idInstitucion = idInstitucion;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(int idSalon) {
        this.idSalon = idSalon;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean esValido() {
        return StringUtils.isBlank(ubicacion);
    }
    
    public boolean esValidoNumero(){
    return idInstitucion <= 0 
            || capacidad <= 0;
    }
}
