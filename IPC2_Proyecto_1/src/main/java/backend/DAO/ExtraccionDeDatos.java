/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class ExtraccionDeDatos {

    private static final String PATH = "/home/helder/IPC 2/IPC2_Proyecto1/IPC2_Proyecto_1/src/main/webapp/resources/foto";

    public Usuario extraerUsuarioFormulario(HttpServletRequest request) throws IncompletoException,
            ObjetoExistenteException,
            IOException,
            ServletException {
        String ruta = null;
        Part filePart = request.getPart("foto");
        if (filePart != null && filePart.getSize() > 0) {
            ruta = PATH + File.separator + filePart.getSubmittedFileName();
            filePart.write(ruta);
        }
        Usuario usuario = new Usuario(
                request.getParameter("dpi"),
                ruta,
                request.getParameter("nombre"),
                request.getParameter("telefono"),
                request.getParameter("organizacion"),
                request.getParameter("email"),
                request.getParameter("contraseña"));

        if (usuario.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }

        return usuario;
    }

    public Usuario extraerUsuarioPorAdmin(HttpServletRequest request) throws IncompletoException,
            ObjetoExistenteException,
            IOException,
            ServletException {
        String ruta = null;
        Part filePart = request.getPart("foto");
        if (filePart != null && filePart.getSize() > 0) {
            ruta = PATH + File.separator + filePart.getSubmittedFileName();
            filePart.write(ruta);
        }
        Usuario usuario = new Usuario(
                request.getParameter("dpi"),
                ruta,
                request.getParameter("nombre"),
                request.getParameter("telefono"),
                request.getParameter("organizacion"),
                request.getParameter("email"),
                request.getParameter("contraseña"));

        if (usuario.esValido() || StringUtils.isBlank(request.getParameter("estado"))
                || StringUtils.isBlank(request.getParameter("rol"))) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }
        boolean estado = request.getParameter("estado").equalsIgnoreCase("Habilitado");
        usuario.setEstado(estado);
        usuario.setRol(request.getParameter("rol"));

        return usuario;
    }

    public Usuario extraerDatosLogin(HttpServletRequest request) throws IncompletoException {
        if (StringUtils.isBlank(request.getParameter("Contraseña")) || 
                StringUtils.isBlank(request.getParameter("Usuario"))) {
            throw new IncompletoException("Faltan datos, vuelva a intentar");
        }

        Usuario usuario = new Usuario(
                request.getParameter("Usuario"),
                request.getParameter(null), // foto
                request.getParameter(null), // nombre
                request.getParameter(null), // telefono 
                request.getParameter(null), // organizaccion
                request.getParameter(null), // email
                request.getParameter("Contraseña"));

        return usuario;
    }
}
