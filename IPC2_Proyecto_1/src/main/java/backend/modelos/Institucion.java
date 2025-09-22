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
public class Institucion {
    
    private int id;
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final String email;
    private String codigo;
    private String numero;
    
    public Institucion(String nombre, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        id = -1;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public boolean esValido() {
        return StringUtils.isBlank(nombre)
                || StringUtils.isBlank(direccion)
                || StringUtils.isBlank(telefono)
                || StringUtils.isBlank(email);   
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
}
