/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.usuario.AS;

import backend.DAO.ExtraccionDeDatos;
import backend.DAO.UsuarioDAO;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerUsuarioActualizadoAdminS", urlPatterns = {"/ControllerUsuarioActualizadoAdminS"})
public class ControllerUsuarioActualizadoAdminS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.seleccionarPorParametro(request.getParameter("id"));
            request.setAttribute("usuario", usuario);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/actualizarUsuario.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | SQLException e) {
           request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/error/error.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExtraccionDeDatos extraccion = new ExtraccionDeDatos();
            Usuario usuario = extraccion.extraerUsuarioFormularioY_OActualizar(request);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.actualiza(usuario);
            request.setAttribute("usuario", usuario);
        } catch (IncompletoException | ObjetoExistenteException | ServletException | IOException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/usuarioActualizado.jsp");
            dispatcher.forward(request, response);
    }

}
