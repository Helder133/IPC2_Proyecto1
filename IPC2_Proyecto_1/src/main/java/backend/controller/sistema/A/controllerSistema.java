/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.sistema.A;

import backend.DAO.ConfiguracionDelSistemaDAO;
import backend.modelos.ConfiguracionDelSistema;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "controllerSistema", urlPatterns = {"/controllerSistema"})
public class controllerSistema extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfiguracionDelSistemaDAO configDAO = new ConfiguracionDelSistemaDAO();
        try {
            if (obtenerTodos(request)) {
                request.setAttribute("configuraciones", configDAO.seleccionar());
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/ajustesSistema.jsp");
                dispatcher.forward(request, response);
            } else{
                int id = Integer.parseInt(request.getParameter("id"));
                ConfiguracionDelSistema config = configDAO.seleccionarPorParametro(id);
                request.setAttribute("configuracion", config);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/ajustesSistema.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("id"));
    }
}
