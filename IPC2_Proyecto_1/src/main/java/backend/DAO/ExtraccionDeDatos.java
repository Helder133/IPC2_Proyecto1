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
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class ExtraccionDeDatos {

    private static final String RELATIVE_PATH = "fotos";

    public Usuario extraerUsuarioFormulario(HttpServletRequest request) throws IncompletoException,
            ObjetoExistenteException,
            IOException,
            ServletException,
            NumberFormatException {
        String codigo = request.getParameter("codigo");
        String numero = request.getParameter("telefono");

        if (codigo == null || numero == null) {
            throw new IncompletoException("El código de país y el número de teléfono son obligatorios");
        }

        if (!codigo.startsWith("+")) {
            throw new IncompletoException("El código del país debe comenzar con '+'");
        }

        String soloNumerosCodigo = codigo.substring(1);
        if (!soloNumerosCodigo.matches("\\d+")) {
            throw new IncompletoException("El código del país solo puede contener números después del '+'");
        }

        if (!numero.matches("\\d+")) {
            throw new IncompletoException("El número de teléfono solo puede contener dígitos numéricos");
        }

        String telefono = codigo + " " + numero;

        Usuario usuario = new Usuario(
                request.getParameter("dpi"),
                pathFoto(request), // ruta de la foto
                request.getParameter("nombre"),
                telefono,
                request.getParameter("organizacion"),
                request.getParameter("email"),
                request.getParameter("contraseña"));

        if (usuario.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }

        return usuario;
    }

    public Usuario extraerUsuarioFormularioY_OActualizar(HttpServletRequest request) throws IncompletoException,
            ObjetoExistenteException,
            IOException,
            ServletException,
            NumberFormatException {
        String codigo = request.getParameter("codigo");
        String numero = request.getParameter("telefono");

        if (codigo == null || numero == null) {
            throw new IncompletoException("El código de país y el número de teléfono son obligatorios");
        }

        if (!codigo.startsWith("+")) {
            throw new IncompletoException("El código del país debe comenzar con '+'");
        }

        String soloNumerosCodigo = codigo.substring(1);
        if (!soloNumerosCodigo.matches("\\d+")) {
            throw new IncompletoException("El código del país solo puede contener números después del '+'");
        }

        if (!numero.matches("\\d+")) {
            throw new IncompletoException("El número de teléfono solo puede contener dígitos numéricos");
        }

        String telefono = codigo + " " + numero;

        Usuario usuario = new Usuario(
                request.getParameter("dpi"),
                pathFoto(request), // ruta de la foto
                request.getParameter("nombre"),
                telefono,
                request.getParameter("organizacion"),
                request.getParameter("email"),
                request.getParameter("contraseña"));

        if (usuario.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }

        boolean estado = request.getParameter("estado").equalsIgnoreCase("Habilitado");
        usuario.setRol(request.getParameter("rol"));
        usuario.setEstado(estado);

        return usuario;
    }

    public Usuario extraerUsuarioParaActulizar(HttpServletRequest request) throws IncompletoException,
            ObjetoExistenteException,
            IOException,
            ServletException,
            NumberFormatException {
        String codigo = request.getParameter("codigo");
        String numero = request.getParameter("telefono");

        if (codigo == null || numero == null) {
            throw new IncompletoException("El código de país y el número de teléfono son obligatorios");
        }

        if (!codigo.startsWith("+")) {
            throw new IncompletoException("El código del país debe comenzar con '+'");
        }

        String soloNumerosCodigo = codigo.substring(1);
        if (!soloNumerosCodigo.matches("\\d+")) {
            throw new IncompletoException("El código del país solo puede contener números después del '+'");
        }

        if (!numero.matches("\\d+")) {
            throw new IncompletoException("El número de teléfono solo puede contener dígitos numéricos");
        }

        String telefono = codigo + " " + numero;

        String contraseña;
        if (StringUtils.isBlank(request.getParameter("contraseña"))) {
            contraseña = "1";
        } else {
            contraseña = request.getParameter("contraseña");
        }

        Usuario usuario = new Usuario(
                request.getParameter("dpi"),
                pathFoto(request), // ruta de la foto
                request.getParameter("nombre"),
                telefono,
                request.getParameter("organizacion"),
                request.getParameter("email"),
                contraseña);

        if (usuario.esValido() || StringUtils.isBlank(request.getParameter("estado"))
                || StringUtils.isBlank(request.getParameter("rol"))) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }
        boolean estado = request.getParameter("estado").equalsIgnoreCase("Habilitado");
        usuario.setRol(request.getParameter("rol"));
        usuario.setEstado(estado);

        if (StringUtils.isBlank(request.getParameter("contraseña"))) {
            usuario.setContraseña(" ");
        }

        return usuario;
    }

    public Usuario extraerDatosLogin(HttpServletRequest request) throws IncompletoException {
        if (StringUtils.isBlank(request.getParameter("Contraseña"))
                || StringUtils.isBlank(request.getParameter("Usuario"))) {
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

    private String pathFoto(HttpServletRequest request) throws IOException,
            ServletException {

        String absolutePath = null;

        Part filePart = request.getPart("foto");
        if (filePart != null && filePart.getSize() > 0) {

            String webPath = request.getServletContext().getRealPath("");
            if (StringUtils.isBlank(webPath)) {
                throw new IOException("Ruta no encontrada");
            }

            String path = webPath + File.separator + RELATIVE_PATH;

            //Extraer Extension
            String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = "";
            int index = originalName.lastIndexOf(".");
            if (index > 0) {
                extension = originalName.substring(index);
            }

            //generar nombre unico con fecha y hora
            String fechaHora = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
            String newName = fechaHora + extension;

            //Verificamos que exista la ruta, sino la creamos
            File fotos = new File(path);
            if (!fotos.exists()) {
                fotos.mkdirs();
            }

            absolutePath = path + File.separator + newName;
            filePart.write(absolutePath);
            return RELATIVE_PATH + File.separator + newName;
        }
        return null;
    }
}
