/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Salon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helder
 */
public class SalonDAO implements CRUD<Salon> {

    private final static String INSERT_SALON = "INSERT INTO Salon (Nombre, Id_Institucion, Ubicacion, Capacidad) VALUES (?,?,?,?)";
    private final static String SELECT_TODOS_LOS_SALONES = "SELECT * FROM Salon";
    private static final String ACTUALIZAR_SALON = "UPDATE Salon SET Nombre = ?, Ubicacion = ?, Capacidad = ? WHERE Id_Salon = ?";
    private final static String SELECT_POR_NOMBRE_A = "SELECT * FROM Salon where Nombre = ? AND Id_Salon <> ?";
    private final static String SELECT_POR_NOMBRE = "SELECT * FROM Salon where Nombre = ?";
    private final static String SELECT_POR_ID = "SELECT * FROM Salon WHERE Id_Salon = ?";
    private static final String ELIMINAR_SALON = "DELETE FROM Salon WHERE Id_Salon = ?";
    private static final String SELECT_POR_CAPACIDAD = "SELECT * FROM Salon WHERE Capacidad <= ?";

    @Override
    public void insetar(Salon t) throws SQLException, ObjetoExistenteException {
        if (existeNombre(t)) {
            throw new ObjetoExistenteException("Ya existe un salon registrado con el nombre propuesto");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSERT_SALON)) {
            insert.setString(1, t.getNombre());
            insert.setInt(2, t.getIdInstitucion());
            insert.setString(3, t.getUbicacion());
            insert.setInt(4, t.getCapacidad());
            int filas = insert.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Error al intentar eliminar, intente de nuevo");
            }
        }
    }

    @Override
    public List<Salon> seleccionar() throws SQLException {
        int max = 10;
        int contador = 0;
        List<Salon> salones = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_TODOS_LOS_SALONES)) {
            ResultSet result = query.executeQuery();
            while (result.next() && (contador < max)) {
                contador++;
                Salon salon = new Salon(
                        result.getString("Nombre"),
                        result.getInt("Id_Institucion"),
                        result.getString("Ubicacion"),
                        result.getInt("Capacidad")
                );

                salon.setIdSalon(result.getInt("Id_Salon"));

                salones.add(salon);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return salones;
    }

    @Override
    public void actualiza(Salon t) throws SQLException, ObjetoExistenteException {
        if (existeNombreA(t)) {
            throw new ObjetoExistenteException("Ya existe un salon registrado con el nombre propuesto");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_SALON)) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getUbicacion());
            insert.setInt(3, t.getCapacidad());
            insert.setInt(4, t.getIdSalon());
            insert.executeUpdate();
        } catch (Exception e) {
        }

    }

    @Override
    public void eliminar(Salon t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ELIMINAR_SALON)) {
            query.setInt(1, t.getIdSalon());
            int filas = query.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Error al intentar eliminar, intente de nuevo");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Salon> seleccionarPorParametroDpOE(String t) throws SQLException {
        int cantidad = Integer.parseInt(t);
        int max = 10;
        int contador = 0;
        List<Salon> salones = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_CAPACIDAD)) {
            query.setInt(1, cantidad);
            ResultSet result = query.executeQuery();
            while (result.next() && contador < max) {
                contador ++;
                Salon salon = new Salon(
                        result.getString("Nombre"),
                        result.getInt("Id_Institucion"),
                        result.getString("Ubicacion"),
                        result.getInt("Capacidad"));

                salon.setIdSalon(result.getInt("Id_Salon"));
                
                salones.add(salon);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return salones;
        
    }

    @Override
    public Salon seleccionarPorParametro(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertPorAdmin(Salon t) throws SQLException, ObjetoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Salon seleccionarPorParametro(int t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_ID)) {
            query.setInt(1, t);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Salon salon = new Salon(
                        result.getString("Nombre"),
                        result.getInt("Id_Institucion"),
                        result.getString("Ubicacion"),
                        result.getInt("Capacidad"));

                salon.setIdSalon(result.getInt("Id_Salon"));

                return salon;
            }
            throw new SQLException("Error, no se puedo encontrar el Salon selecionado");
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeNombre(Salon t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_NOMBRE)) {
            query.setString(1, t.getNombre());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeNombreA(Salon t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECT_POR_NOMBRE_A)) {
            query.setString(1, t.getNombre());
            query.setInt(2, t.getIdSalon());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
