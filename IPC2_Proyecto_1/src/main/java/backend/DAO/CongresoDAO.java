/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Congreso;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helder
 */
public class CongresoDAO implements CRUD<Congreso>{
    private final static String INSETAR_CONGRESO = "INSERT INTO Congreso (Id_Usuario, Id_Institucion, Nombre, "
            + "Descripcion, Ubicacion, Precio, Convocatoria, Fecha_Inicio, Fecha_Fin) VALUES (?,?,?,?,?,?,?,?,?)";
    private final static String SELECT_POR_NOMBRE = "SELECT * FROM Congreso where Nombre = ?";
    private final static String SELECT_POR_NOMBRE_A = "SELECT * FROM Congreso where Nombre = ? AND Id_Congreso <> ?";
    private final static String SELECT_TODO = "SELECT * FROM Congreso";
    private final static String SELECT_POR_COINCIDENCIA_DE_NOMBRE = "SELECT * FROM Congreso where Nombre LIKE ?";
    private static final String ACTUALIZAR_CONGRESO = "UPDATE Congreso SET Nombre = ?, Descripcion = ?, Ubicacion = ?, Precio = ?, Convocatoria = ?, Fecha_Inicio = ?, Fecha_Fin = ? WHERE Id_Congreso = ?";
    private static final String ELIMINAR_CONGRESO = "DELETE FROM Congreso WHERE Id_Congreso = ?";
    private final static String SELECT_POR_ID = "SELECT * FROM Congreso where Id_Congreso = ?";
    @Override
    public void insetar(Congreso t) throws SQLException, ObjetoExistenteException {
        if (existeNombre(t)) {
            throw new ObjetoExistenteException("Ya exixte un congreso registrado con el nombre propuesto");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_CONGRESO)) {
            insert.setString(1, t.getIdUsuario());
            insert.setInt(2, t.getIdInstitucion());
            insert.setString(3, t.getNombre());
            insert.setString(4, t.getDescripcion());
            insert.setString(5, t.getUbicacion());
            insert.setBigDecimal(6, t.getPrecio());
            insert.setBoolean(7, t.isConvocatoria());
            insert.setDate(8, Date.valueOf(t.getFechaInicio())); //LocalDate
            insert.setDate(9, Date.valueOf(t.getFechaFin())); //LocalDate

            insert.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public List<Congreso> seleccionar() throws SQLException {
        int max = 10;
        int contador = 0;
        List<Congreso> congresos = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_TODO)) {
            ResultSet result = query.executeQuery();
            while (result.next() && (contador < max)) {
                contador++;
                Congreso congreso = new Congreso(
                        result.getString("Id_Usuario"),
                        result.getString("Nombre"),
                        result.getString("Descripcion"),
                        result.getString("Ubicacion"),
                        result.getBigDecimal("Precio"),
                        result.getBoolean("Convocatoria"),
                        result.getDate("Fecha_Inicio").toLocalDate(),
                        result.getDate("Fecha_Fin").toLocalDate()
                );

                congreso.setIdCongreso(result.getInt("Id_Congreso"));
                congreso.setIdInstitucion(result.getInt("Id_Institucion"));
                
                congresos.add(congreso);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return congresos;
    }

    @Override
    public void actualiza(Congreso t) throws SQLException, ObjetoExistenteException {
        if (existeNombreA(t)) {
            throw new ObjetoExistenteException("Ya exixte un congreso registrado con el nombre propuesto");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_CONGRESO);) {
                insert.setString(1, t.getNombre());
                insert.setString(2, t.getDescripcion());
                insert.setString(3, t.getUbicacion());
                insert.setBigDecimal(4, t.getPrecio());
                insert.setBoolean(5, t.isConvocatoria());
                insert.setDate(6, Date.valueOf(t.getFechaInicio()));
                insert.setDate(7, Date.valueOf(t.getFechaFin()));
                insert.setInt(8, t.getIdCongreso());
                insert.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
    }

    @Override
    public void eliminar(Congreso t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ELIMINAR_CONGRESO)) {
            query.setInt(1, t.getIdCongreso());
            int filas = query.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Error al intentar eliminar, intente de nuevo");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Congreso> seleccionarPorParametroDpOE(String t) throws SQLException {
        List<Congreso> congresos = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_COINCIDENCIA_DE_NOMBRE)) {
            String busqueda = "%" + t + "%";
            query.setString(1, busqueda);
            ResultSet result = query.executeQuery();
            while (result.next() && contador < max) {
                contador++;
                Congreso congreso = new Congreso(
                        result.getString("Id_Usuario"),
                        result.getString("Nombre"),
                        result.getString("Descripcion"),
                        result.getString("Ubicacion"),
                        result.getBigDecimal("Precio"),
                        result.getBoolean("Convocatoria"),
                        result.getDate("Fecha_Inicio").toLocalDate(),
                        result.getDate("Fecha_Fin").toLocalDate()
                );

                congreso.setIdCongreso(result.getInt("Id_Congreso"));
                congreso.setIdInstitucion(result.getInt("Id_Institucion"));
                
                congresos.add(congreso);
            }
            return congresos;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Congreso seleccionarPorParametro(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertPorAdmin(Congreso t) throws SQLException, ObjetoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Congreso seleccionarPorParametro(int t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_ID)) {
            query.setInt(1, t);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Congreso congreso = new Congreso(
                        result.getString("Id_Usuario"),
                        result.getString("Nombre"),
                        result.getString("Descripcion"),
                        result.getString("Ubicacion"),
                        result.getBigDecimal("Precio"),
                        result.getBoolean("Convocatoria"),
                        result.getDate("Fecha_Inicio").toLocalDate(),
                        result.getDate("Fecha_Fin").toLocalDate()
                );

                congreso.setIdCongreso(result.getInt("Id_Congreso"));
                congreso.setIdInstitucion(result.getInt("Id_Institucion"));
                
                return congreso;
            }
            throw new SQLException("Error, no se puedo encontrar el congreso selecionado");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeNombre(Congreso t) throws SQLException{
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_NOMBRE)) {
            query.setString(1, t.getNombre());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeNombreA(Congreso t) throws SQLException{
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_NOMBRE_A)) {
            query.setString(1, t.getNombre());
            query.setInt(2, t.getIdCongreso());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
}
