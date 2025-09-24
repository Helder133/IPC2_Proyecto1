/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Institucion;
import backend.modelos.Salon;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class ExtraccionDeDatosSalon {

    public Salon extraerSalonFormulario(HttpServletRequest request, String organizacion)
            throws IncompletoException,
            ObjetoExistenteException,
            NumberFormatException,
            SQLException {

        InstitucionDAO institucionDAO = new InstitucionDAO();

        Institucion institucion = institucionDAO.seleccionarPorParametro(organizacion);
        Salon salon = new Salon(
                request.getParameter("nombre"),
                institucion.getId(), // ruta de la foto
                request.getParameter("ubicacion"),
                Integer.parseInt(request.getParameter("capacidad")));
        if (salon.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        } if (salon.esValidoNumero()) {
            throw new IncompletoException("No se puede mandar numeros negativos, Vuelva a intentar");
        }
        return salon;
    }
    
    public Salon extraerSalonFormularioActualizar(HttpServletRequest request, String organizacion)
            throws IncompletoException,
            ObjetoExistenteException,
            NumberFormatException,
            SQLException {

        InstitucionDAO institucionDAO = new InstitucionDAO();

        Institucion institucion = institucionDAO.seleccionarPorParametro(organizacion);
        Salon salon = new Salon(
                request.getParameter("nombre"),
                institucion.getId(), // ruta de la foto
                request.getParameter("ubicacion"),
                Integer.parseInt(request.getParameter("capacidad")));
        
        salon.setIdSalon(Integer.parseInt(request.getParameter("id")));
        if (salon.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        }
        return salon;
    }
}
