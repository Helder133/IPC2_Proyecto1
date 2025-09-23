/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.congreso;

import backend.DAO.CongresoDAO;
import backend.modelos.Congreso;
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
@WebServlet(name = "ControllerCongresoEliminar", urlPatterns = {"/ControllerCongresoEliminar"})
public class ControllerCongresoEliminar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            CongresoDAO congresoDAO = new CongresoDAO();
            Congreso congreso = congresoDAO.seleccionarPorParametro(id);
            congresoDAO.eliminar(congreso);
            response.sendRedirect(request.getContextPath() + "/ControllerCongreso");
        } catch (NumberFormatException | SQLException e) {
            
        }
    }

}
