/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.usuario.AS;

import backend.DAO.ExtraccionDeDatos;
import backend.DAO.UsuarioDAO;
import backend.exceptions.DesabledUserExeption;
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
@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin"})
public class ControllerLogin extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExtraccionDeDatos extracion = new ExtraccionDeDatos();
            Usuario usuario = extracion.extraerDatosLogin(request);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario1 = usuarioDAO.extraerUsuarioRegistradoLogin(usuario);
            request.setAttribute("usuario", usuario1);

            HttpSession session = request.getSession(true);

            session.setAttribute("id", usuario1.getDPI_o_Pasaporte());
            session.setAttribute("rol", usuario1.getRol());
            session.setAttribute("nombre", usuario1.getNombre());

            renderizarPagina(usuario1, request, response, session);
        } catch (IncompletoException | ObjetoExistenteException | SQLException |DesabledUserExeption e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/error/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false); // no crear si no existe
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    private void renderizarPagina(Usuario usuario, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        switch (usuario.getRol()) {
            case "Administrador Sistema": {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/homeAdminSistema.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "Administrador Congreso": {
                session.setAttribute("organizacion", usuario.getOrganizacion());
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Congreso/homeAdminCongreso.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/loginGenerico.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

}
