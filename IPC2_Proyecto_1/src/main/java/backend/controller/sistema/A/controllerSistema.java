/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller.sistema.A;

import backend.DAO.ConfiguracionDelSistemaDAO;
import backend.DAO.ExtraccionDeDatos;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
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
        System.out.println(request.getParameter("id"));
        try {
            if (obtenerTodos(request)) {
                request.setAttribute("configuraciones", configDAO.seleccionar());
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/ajustesSistema.jsp");
                dispatcher.forward(request, response);
            } else {
                System.out.println("Entrando al get con id");
                int id = Integer.parseInt(request.getParameter("id"));
                ConfiguracionDelSistema config = configDAO.seleccionarPorParametro(id);
                
                request.setAttribute("configuracion", config);
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/login/Administrador Sistema/actualizarConfig.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ExtraccionDeDatos extraccion = new ExtraccionDeDatos();
            ConfiguracionDelSistemaDAO configDAO = new ConfiguracionDelSistemaDAO();
            ConfiguracionDelSistema config = extraccion.extraerConfigruacionFormularioActualizar(request);
            configDAO.actualiza(config);

            request.setAttribute("configuracion", config);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/configActualizada.jsp");
            dispatcher.forward(request, response);

        } catch (IncompletoException | ObjetoExistenteException | NumberFormatException | SQLException e) {
        request.setAttribute("Error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/login/Administrador Sistema/configActualizada.jsp");
            dispatcher.forward(request, response);
        }

    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("id"));
    }
}
