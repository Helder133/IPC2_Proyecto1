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
            
            if (usuario1.getRol().equalsIgnoreCase("Administrador Sistema")) {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/homeAdminSistema.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/loginGenerico.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IncompletoException | ObjetoExistenteException | SQLException e) {
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
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
