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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerInstitucionActualizarYEliminar", urlPatterns = {"/ControllerInstitucionActualizarYEliminar"})
public class ControllerInstitucionActualizarYEliminar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            InstitucionDAO institucionDAO = new InstitucionDAO();
            Institucion institucion = institucionDAO.seleccionarPorParametro(id);

            request.setAttribute("institucion", institucion);

        } catch (NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/login/Administrador Sistema/actualizarInstitucion.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InstitucionDAO institucionDAO = new InstitucionDAO();
        try {
            boolean opcion = StringUtils.isNotBlank(request.getParameter("opcion"));
            if (opcion && request.getParameter("opcion").equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Institucion institucion = institucionDAO.seleccionarPorParametro(id);
                institucionDAO.eliminar(institucion);

                response.sendRedirect(request.getContextPath() + "/ControllerInstitucion");
            } else {
                ExtraccionDeDatos extraer = new ExtraccionDeDatos();
                Institucion institucion = extraer.extraerInstitucionFormulario(request);
                institucionDAO.actualiza(institucion);

                request.setAttribute("institucion", institucion);

            }
        } catch (IncompletoException | ObjetoExistenteException
                | ServletException | IOException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/login/Administrador Sistema/institucionActualizar.jsp");
        dispatcher.forward(request, response);
    }

}
