/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.exceptions.IcompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author helder
 */
public class ExtraccionDeDatos {
    public Usuario extraerUsuarioFormulario (HttpServletRequest request) throws IcompletoException, 
            ObjetoExistenteException {
        Usuario usuario = new Usuario(
        request.getParameter("dpi"),
        request.getParameter("foto"),
        request.getParameter("nombre"),
        request.getParameter("telefono"),
        request.getParameter("organizacion"),
        request.getParameter("email"),
        request.getParameter("contraseña"));
        
        if (usuario.esValido()) {
            throw new IcompletoException("Faltan datos, Vuelva a intentar");
        }
        
        return usuario;
    }
}
