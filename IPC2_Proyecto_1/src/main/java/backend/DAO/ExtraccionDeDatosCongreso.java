/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.exceptions.IncompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.ConfiguracionDelSistema;
import backend.modelos.Congreso;
import backend.modelos.Institucion;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author helder
 */
public class ExtraccionDeDatosCongreso {

    public Congreso extraerCongresoFormulario(HttpServletRequest request, String id, String organizacion)
            throws IncompletoException,
            ObjetoExistenteException,
            NumberFormatException,
            SQLException {
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        InstitucionDAO institucionDAO = new InstitucionDAO();
        ConfiguracionDelSistemaDAO configDAO = new ConfiguracionDelSistemaDAO();
        ConfiguracionDelSistema config = configDAO.seleccionarPorParametro(1);
        if (!(precio.compareTo(config.getPrecioMinimo()) >= 0)) {
            throw new ObjetoExistenteException(String.format("El precio del congreso es menor al precio minimo establecido, el cual es Q%s", config.getPrecioMinimo()));
        }
        Institucion institucion = institucionDAO.seleccionarPorParametro(organizacion);
        Congreso congreso = new Congreso(
                id,
                request.getParameter("nombre"), // ruta de la foto
                request.getParameter("descripcion"),
                institucion.getDireccion(),
                precio,
                request.getParameter("convocatoria").equals("Habilitado"),
                LocalDate.parse(request.getParameter("fechaInicio")),
                LocalDate.parse(request.getParameter("fechaFin")));

        congreso.setIdInstitucion(institucion.getId());
        if (congreso.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        } else if (congreso.esValidoLaFecha()) {
            throw new IncompletoException("La fecha de fin no puede ser igual o anterior a la fecha de inicio");
        }

        return congreso;
    }

    public Congreso extraerCongresoFormularioActualizar(HttpServletRequest request, String id, String organizacion) throws IncompletoException,
            ObjetoExistenteException,
            NumberFormatException,
            SQLException {
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        InstitucionDAO institucionDAO = new InstitucionDAO();

        Institucion institucion = institucionDAO.seleccionarPorParametro(organizacion);
        Congreso congreso = new Congreso(
                id,
                request.getParameter("nombre"), // ruta de la foto
                request.getParameter("descripcion"),
                institucion.getDireccion(),
                precio,
                request.getParameter("convocatoria").equals("Habilitado"),
                LocalDate.parse(request.getParameter("fechaInicio")),
                LocalDate.parse(request.getParameter("fechaFin")));

        congreso.setIdInstitucion(institucion.getId());
        int idCongreso = Integer.parseInt(request.getParameter("id"));
        congreso.setIdCongreso(idCongreso);

        if (congreso.esValido()) {
            throw new IncompletoException("Faltan datos, Vuelva a intentar");
        } else if (congreso.esValidoLaFecha()) {
            throw new IncompletoException("La fecha de fin no puede ser igual o anterior a la fecha de inicio");
        }

        return congreso;
    }
}
