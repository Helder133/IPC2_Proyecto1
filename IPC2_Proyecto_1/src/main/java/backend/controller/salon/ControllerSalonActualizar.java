/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.salon;

import backend.DAO.ExtraccionDeDatosSalon;
import backend.DAO.SalonDAO;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Salon;
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
@WebServlet(name = "ControllerSalonActualizar", urlPatterns = {"/ControllerSalonActualizar"})
public class ControllerSalonActualizar extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SalonDAO salonDAO = new SalonDAO();
            Salon salon = salonDAO.seleccionarPorParametro(id);
            
            request.setAttribute("salon", salon);
        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/login/Administrador Congreso/actualizarSalon.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            HttpSession session = request.getSession();
            String organizacion = (String) session.getAttribute("organizacion");
            ExtraccionDeDatosSalon extraccion = new ExtraccionDeDatosSalon();
            Salon salon = extraccion.extraerSalonFormularioActualizar(request, organizacion);
            SalonDAO salonDAO = new SalonDAO();
            salonDAO.actualiza(salon);
            
            request.setAttribute("salon", salon);
        } catch (IncompletoException | ObjetoExistenteException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        
        RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/salonActualizado.jsp");
            dispatcher.forward(request, response);
    }
}
