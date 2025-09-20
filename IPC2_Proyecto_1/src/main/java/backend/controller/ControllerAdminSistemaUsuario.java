/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.controller;

import backend.DAO.ExtraccionDeDatos;
import backend.DAO.UsuarioDAO;
import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "ControllerAdminSistemaUsuario", urlPatterns = {"/ControllerAdminSistemaUsuario"})
public class ControllerAdminSistemaUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("hola ControllerAdminSistemaUsuario");
            ExtraccionDeDatos extraccion = new ExtraccionDeDatos();
            Usuario usuario = extraccion.extraerUsuarioPorAdmin(request);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.insertPorAdmin(usuario);
            request.setAttribute("usuario", usuario);

        } catch (IncompletoException | ObjetoExistenteException | SQLException | IOException e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/login/Administrador Sistema/usuario-creado.jsp");
        dispatcher.forward(request, response);
        
    }

}
