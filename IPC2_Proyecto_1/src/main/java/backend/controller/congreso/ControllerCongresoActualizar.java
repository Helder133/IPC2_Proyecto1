/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.congreso;

import backend.DAO.CongresoDAO;
import backend.DAO.ExtraccionDeDatosCongreso;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Congreso;
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
@WebServlet(name = "ControllerCongresoActualizar", urlPatterns = {"/ControllerCongresoActualizar"})
public class ControllerCongresoActualizar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            CongresoDAO congresoDAO = new CongresoDAO();
            Congreso congreso = congresoDAO.seleccionarPorParametro(id);
            
            request.setAttribute("congreso", congreso);
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/login/Administrador Congreso/actualizarCongreso.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("id");
            String organizacion = (String) session.getAttribute("organizacion");
            ExtraccionDeDatosCongreso extraccion = new ExtraccionDeDatosCongreso();
            Congreso congreso = extraccion.extraerCongresoFormularioActualizar(request, idUsuario, organizacion);
            CongresoDAO congresoDAO = new CongresoDAO();
            congresoDAO.actualiza(congreso);
            
            request.setAttribute("congreso", congreso);
        } catch (IncompletoException | ObjetoExistenteException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        
        RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/congresoActualizado.jsp");
            dispatcher.forward(request, response);
    }

}
