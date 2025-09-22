/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.usaro.AS;

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
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerUsuario", urlPatterns = {"/ControllerUsuario"})
public class ControllerUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExtraccionDeDatos extraccion = new ExtraccionDeDatos();
            Usuario usuario = extraccion.extraerUsuarioFormulario(request);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.insetar(usuario);
            request.setAttribute("usuario", usuario);

        } catch (IncompletoException | ObjetoExistenteException | SQLException | IOException e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/nuevoUsuario/usuario-creado.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (obtenerTodos(request)) {
            try {
                List<Usuario> usuarios = usuarioDAO.seleccionar();
                HttpSession session = request.getSession();
                String id = (String) session.getAttribute("id");
                for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).getDPI_o_Pasaporte().equals(id)) {
                        usuarios.remove(i);
                    }
                }
                request.setAttribute("usuarios", usuarios);
            } catch (SQLException ex) {
                request.setAttribute("error", "Error en la base de datos: " + ex.getErrorCode());
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/error/errorSqlSistema.jsp");
                dispatcher.forward(request, response);
            }

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/usuario.jsp");
            dispatcher.forward(request, response);
        } else {
            // busco el evento por codigo y redirijo a la vista
            try {
                List<Usuario> usuario = usuarioDAO.seleccionarPorParametroDpOE(request.getParameter("id"));
                HttpSession session = request.getSession();
                String id = (String) session.getAttribute("id");
                for (int i = 0; i < usuario.size(); i++) {
                    if(usuario.get(i).getDPI_o_Pasaporte().equals(id)){
                        usuario.remove(i);
                    }
                }
                request.setAttribute("usuarios", usuario);
            } catch (SQLException e) {
                request.setAttribute("error", "Error en la base de datos: " + e.getErrorCode());
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/error/errorSqlSistema.jsp");
                dispatcher.forward(request, response);
            }

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/usuario.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("id"));
    }

}
