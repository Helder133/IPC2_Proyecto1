/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.modelos;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class Usuario {
    private final String DPI_o_Pasaporte;
    private final String foto;
    private final String nombre;
    private final String telefono;
    private final String organizacion;
    private final String email;
    private final String contraseña;
    private Boolean estado;
    private final String rol;

    public Usuario(String DPI_o_Pasaporte, String foto, String nombre, String telefono, String organizacion, String email, String contraseña) {
        this.DPI_o_Pasaporte = DPI_o_Pasaporte;
        this.foto = foto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.organizacion = organizacion;
        this.email = email;
        this.contraseña = incriptar(contraseña);
        this.estado = true; 
        this.rol = "Participante";
    }

    public String getDPI_o_Pasaporte() {
        return DPI_o_Pasaporte;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getRol() {
        return rol;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public boolean esValido () {
    return StringUtils.isBlank(DPI_o_Pasaporte) && 
            StringUtils.isBlank(nombre) &&
            StringUtils.isBlank(telefono) &&
            StringUtils.isBlank(organizacion) &&
            StringUtils.isBlank(email) &&
            StringUtils.isBlank(contraseña) &&
            StringUtils.isBlank(rol);
    }

    private String incriptar(String contraseña) {
        byte[] message = contraseña.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }
}
