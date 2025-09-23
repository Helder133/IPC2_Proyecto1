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
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
@MultipartConfig
@WebServlet(name = "ControllerCongreso", urlPatterns = {"/ControllerCongreso"})
public class ControllerCongreso extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CongresoDAO congresoDAO = new CongresoDAO();
        try {
            if (obtenerTodos(request)) {
                request.setAttribute("congresos", congresoDAO.seleccionar());

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Congreso/congreso.jsp");
                dispatcher.forward(request, response);
            } else {
                String id = request.getParameter("id");
                System.out.println(id);
                List<Congreso> congresos = congresoDAO.seleccionarPorParametroDpOE(request.getParameter("id"));
                request.setAttribute("congresos", congresos);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Congreso/congreso.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            
            request.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("id");
            String organizacion = (String) session.getAttribute("organizacion");
            ExtraccionDeDatosCongreso extraccion = new ExtraccionDeDatosCongreso();
            Congreso congreso = extraccion.extraerCongresoFormulario(request, idUsuario, organizacion);
            CongresoDAO congresoDAO = new CongresoDAO();
            congresoDAO.insetar(congreso);

            request.setAttribute("congreso", congreso);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/congresoAgregado.jsp");
            dispatcher.forward(request, response);
        } catch (IncompletoException | ObjetoExistenteException | NumberFormatException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Congreso/congresoAgregado.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("id"));
    }

}
