/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.IcompletoException;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class UsuarioDAO implements CRUD<Usuario> {

    private static final String ENCONTRAR_USUARIO_POR_CODIGO_QUERY = "select * from Usuario where DPI_o_Pasaporte = ?";
    private static final String INSETAR_USUARIO = "insert into Usuario (DPI_o_Pasaporte, Foto, Nombre, Telefono, Organizacion, Email, Contraseña) values (?,?,?,?);";
    @Override
    public Usuario insetar(Usuario t) throws SQLException, ObjetoExistenteException {
        if (existeUsuario(t)) {
            throw new ObjetoExistenteException(String.format("El Usuario '%s' con documento '%s' ya existe", t.getNombre(), t.getDPI_o_Pasaporte()));
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_USUARIO);) {
            insert.setString(1, t.getDPI_o_Pasaporte());
            insert.setString(2, t.getFoto());
            insert.setString(3, t.getNombre());
            insert.setString(4, t.getTelefono());
            insert.setString(5, t.getOrganizacion());
            insert.setString(6, t.getEmail());
            insert.setString(7, t.getContraseña());
            insert.executeUpdate();
            return t;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeUsuario(Usuario usuario) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_CODIGO_QUERY)) {
            query.setString(1, usuario.getDPI_o_Pasaporte());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Usuario[] seleccionar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualiza(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario seleccionarPorParametro(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario seleccionarPorParametro(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
