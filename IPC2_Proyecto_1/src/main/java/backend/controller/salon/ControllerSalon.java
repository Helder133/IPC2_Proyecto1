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
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerSalon", urlPatterns = {"/ControllerSalon"})
public class ControllerSalon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SalonDAO salonDAO = new SalonDAO();
        try {
            if (obtenerTodos(request)) {
                request.setAttribute("salones", salonDAO.seleccionar());

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Congreso/salon.jsp");
                dispatcher.forward(request, response);
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                List<Salon> salones = salonDAO.seleccionarPorParametroDpOE(request.getParameter("id"));
                request.setAttribute("salones", salones);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Congreso/salon.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {     
            request.setAttribute("error", e.getMessage());
        } 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String organizacion = (String) session.getAttribute("organizacion");
            ExtraccionDeDatosSalon extraccion = new ExtraccionDeDatosSalon();
            Salon salon = extraccion.extraerSalonFormulario(request, organizacion);
            SalonDAO salonDAO = new SalonDAO();
            salonDAO.insetar(salon);

            request.setAttribute("salon", salon);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/salonAgregado.jsp");
            dispatcher.forward(request, response);
        } catch (IncompletoException | ObjetoExistenteException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/salonAgregado.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("id"));
    }
}
