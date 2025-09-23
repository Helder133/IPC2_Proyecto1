/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Institucion;
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
public class InstitucionDAO implements CRUD<Institucion> {

    private static final String INSETAR_INSTITUCION = "INSERT INTO Institucion (Nombre, Direccion, Telefono, Email) VALUES (?,?,?,?)";
    private static final String SELECCIONAR_TODAS_LAS_INSTITUCIONES = "SELECT * FROM Institucion";
    private static final String SELECCIONAR_INSTITUCION_FILTRADO_POR_NOMBRE = "SELECT * FROM Institucion where Nombre LIKE ?";
    private static final String VERIFICAR_NOMBRE = "SELECT * FROM Institucion WHERE Nombre = ?";
    private static final String VERIFICAR_NOMBRE_A = "SELECT * FROM Institucion WHERE Nombre = ? AND Id_Institucion <> ?";
    private static final String VERIFICAR_NUMERO = "SELECT * FROM Institucion WHERE Telefono = ?";
    private static final String VERIFICAR_NUMERO_A = "SELECT * FROM Institucion WHERE Telefono = ? AND Id_Institucion <> ?";
    private static final String VERIFICAR_EMAIL = "SELECT * FROM Institucion WHERE Email = ?";
    private static final String VERIFICAR_EMAIL_A = "SELECT * FROM Institucion WHERE Email = ? AND Id_Institucion <> ?";
    private static final String SELECCIONAR_POR_ID = "SELECT * FROM Institucion WHERE Id_Institucion = ?";
    private static final String ELIMINAR_INSTITUCION = "DELETE FROM Institucion WHERE Id_Institucion = ?";
    private static final String ACTUALIZAR_INSTITUCION = "update Institucion set Nombre = ?, Direccion = ?, Telefono = ?, Email = ? where Id_Institucion = ?";

    @Override
    public void insetar(Institucion t) throws SQLException, ObjetoExistenteException {
        if (verificarEmail(t)) {
            throw new ObjetoExistenteException("El correo ingresado ya esta asociado a otra institucion");
        } else if (verificarNombre(t)) {
            throw new ObjetoExistenteException("El nombre ya esta asociando a otra institucion");
        } else if (verificarNumero(t)) {
            throw new ObjetoExistenteException("El Telefono ya esta aosciado a otra institucion");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_INSTITUCION)) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getDireccion());
            insert.setString(3, t.getTelefono());
            insert.setString(4, t.getEmail());

            insert.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public List<Institucion> seleccionar() throws SQLException {
        int max = 10;
        int contador = 0;
        List<Institucion> instituciones = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_TODAS_LAS_INSTITUCIONES)) {
            ResultSet result = query.executeQuery();
            while (result.next() && (contador < max)) {
                contador++;
                Institucion institucion = new Institucion(
                        result.getString("Nombre"),
                        result.getString("Direccion"),
                        result.getString("Telefono"),
                        result.getString("Email")
                );

                institucion.setId(result.getInt("Id_Institucion"));
                instituciones.add(institucion);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return instituciones;
    }

    @Override
    public void actualiza(Institucion t) throws SQLException, ObjetoExistenteException {
        if (verificarEmailA(t)) {
            throw new ObjetoExistenteException("El correo ingresado ya esta asociado a otra institucion");
        } else if (verificarNombreA(t)) {
            throw new ObjetoExistenteException("El nombre ya esta asociando a otra institucion");
        } else if (verificarNumeroA(t)) {
            throw new ObjetoExistenteException("El Telefono ya esta aosciado a otra institucion");
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_INSTITUCION);) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getDireccion());
            insert.setString(3, t.getTelefono());
            insert.setString(4, t.getEmail());
            insert.setInt(5, t.getId());
            
            int filas = insert.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("Error al intentar Actualizar");
            }
            
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void eliminar(Institucion t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ELIMINAR_INSTITUCION)) {
            query.setInt(1, t.getId());
            int filas = query.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Error al intentar eliminar, intente de nuevo");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Institucion> seleccionarPorParametroDpOE(String t) throws SQLException {
        List<Institucion> instituciones = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_INSTITUCION_FILTRADO_POR_NOMBRE)) {
            String busqueda = "%" + t + "%";
            query.setString(1, busqueda);
            ResultSet result = query.executeQuery();
            while (result.next() && contador < max) {
                contador++;
                Institucion institucion = new Institucion(
                        result.getString("Nombre"),
                        result.getString("Direccion"),
                        result.getString("Telefono"),
                        result.getString("Email"));
                institucion.setId(result.getInt("Id_Institucion"));

                instituciones.add(institucion);
            }
            return instituciones;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Institucion seleccionarPorParametro(String t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_NOMBRE)) {
            query.setString(1, t);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                Institucion institucion = new Institucion(
                        result.getString("Nombre"),
                        result.getString("Direccion"),
                        result.getString("Telefono"),
                        result.getString("Email"));
                institucion.setId(result.getInt("Id_Institucion"));
                return institucion;
            } else {
                throw new SQLException("Error, intente de nuevo");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void insertPorAdmin(Institucion t) throws SQLException, ObjetoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean verificarNombre(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_NOMBRE)) {
            query.setString(1, ins.getNombre());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean verificarNumero(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_NUMERO)) {
            query.setString(1, ins.getTelefono());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean verificarEmail(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_EMAIL)) {
            query.setString(1, ins.getEmail());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    private boolean verificarNombreA(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_NOMBRE_A)) {
            query.setString(1, ins.getNombre());
            query.setInt(2, ins.getId());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean verificarNumeroA(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_NUMERO_A)) {
            query.setString(1, ins.getTelefono());
            query.setInt(2, ins.getId());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean verificarEmailA(Institucion ins) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(VERIFICAR_EMAIL_A)) {
            query.setString(1, ins.getEmail());
            query.setInt(2, ins.getId());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Institucion seleccionarPorParametro(int t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_POR_ID)) {
            query.setInt(1, t);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                Institucion institucion = new Institucion(
                        result.getString("Nombre"),
                        result.getString("Direccion"),
                        result.getString("Telefono"),
                        result.getString("Email")); //contraseña

                institucion.setId(result.getInt("Id_Institucion"));

                String telefono = institucion.getTelefono();

                char espacio = ' ';

                int index = telefono.indexOf(espacio);
                String codigo = telefono.substring(0, index);
                String numero = telefono.substring(index + 1, telefono.length());

                institucion.setCodigo(codigo);
                institucion.setNumero(numero);

                return institucion;
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
