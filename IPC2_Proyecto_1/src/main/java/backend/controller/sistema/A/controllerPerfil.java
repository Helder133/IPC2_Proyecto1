/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.sistema.A;

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
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "controllerPerfil", urlPatterns = {"/controllerPerfil"})
public class controllerPerfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String id = (String) session.getAttribute("id");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.seleccionarPorParametro(id);

            request.setAttribute("usuario", usuario);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/actualizarPerfil.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/perfilActualizado.jsp");
            dispatcher.forward(request, response);
        } catch (IncompletoException | ObjetoExistenteException | ServletException | IOException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/perfilActualizado.jsp");
            dispatcher.forward(request, response);
        }
    }

}
