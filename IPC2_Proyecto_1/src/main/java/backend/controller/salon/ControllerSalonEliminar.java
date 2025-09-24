/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.salon;

import backend.DAO.SalonDAO;
import backend.modelos.Salon;
import jakarta.servlet.annotation.MultipartConfig;
import java.io.IOException;
import jakarta.servlet.ServletException;
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
@WebServlet(name = "ControllerSalonEliminar", urlPatterns = {"/ControllerSalonEliminar"})
public class ControllerSalonEliminar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SalonDAO salonDAO = new SalonDAO();
            Salon salon = salonDAO.seleccionarPorParametro(id);
            salonDAO.eliminar(salon);
            response.sendRedirect(request.getContextPath() + "/ControllerSalon");
        } catch (NumberFormatException | SQLException e) {

        }
    }

}
