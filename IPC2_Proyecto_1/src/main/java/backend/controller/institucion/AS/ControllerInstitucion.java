/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.institucion.AS;

import backend.DAO.ExtraccionDeDatos;
import backend.DAO.InstitucionDAO;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Institucion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerInstitucion", urlPatterns = {"/ControllerInstitucion"})
public class ControllerInstitucion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InstitucionDAO institucionDAO = new InstitucionDAO();
        try {
            if (obtenerTodos(request)) {
                request.setAttribute("instituciones", institucionDAO.seleccionar());

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/institucion.jsp");
                dispatcher.forward(request, response);
            } else {
                List<Institucion> institucion = institucionDAO.seleccionarPorParametroDpOE(request.getParameter("nombre"));
                request.setAttribute("instituciones", institucion);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/institucion.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            //request.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExtraccionDeDatos extraccion = new ExtraccionDeDatos();
            Institucion institucion = extraccion.extraerInstitucionFormulario(request);
            InstitucionDAO institucionDAO = new InstitucionDAO();
            institucionDAO.insetar(institucion);

            request.setAttribute("institucion", institucion);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/institucionAgregada.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | IncompletoException | ObjetoExistenteException | ServletException | IOException | NumberFormatException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/institucionAgregada.jsp");
            dispatcher.forward(request, response);
        }

    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("nombre"));
    }
}
