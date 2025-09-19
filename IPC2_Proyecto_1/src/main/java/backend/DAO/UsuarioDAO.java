/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.Usuario;
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
public class UsuarioDAO implements CRUD<Usuario> {

    private static final String ENCONTRAR_USUARIO_POR_CODIGO_QUERY = "select * from Usuario where DPI_o_Pasaporte = ?";
    private static final String ENCONTRAR_USUARIO_POR_EMAIL_QUERY = "select * from Usuario where Email = ?";
    private static final String INSETAR_USUARIO = "insert into Usuario (DPI_o_Pasaporte, Foto, Nombre, Telefono, Organizacion, Email, Contraseña) values (?,?,?,?,?,?,?);";
    private static final String ENCONTRAR_USUARIO_LOGIN = "select * from Usuario where DPI_o_Pasaporte = ? and Contraseña = ?";
    private static final String SELECCIONAR_TODOS_LOS_USUARIOS = "select * from Usuario";
    @Override
    public Usuario insetar(Usuario t) throws SQLException, ObjetoExistenteException {
        if (existeUsuario(t)) {
            throw new ObjetoExistenteException(String.format("El Usuario '%s' con documento '%s' ya existe", t.getNombre(), t.getDPI_o_Pasaporte()));
        } else if (existeCorreo(t)) {
            throw new ObjetoExistenteException(String.format("El correo '%s' ya existe", t.getEmail()));
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

    private boolean existeCorreo(Usuario usuario) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_EMAIL_QUERY)) {
            query.setString(1, usuario.getEmail());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Usuario extraerUsuarioRegistradoLogin(Usuario usuario) throws SQLException, ObjetoExistenteException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_LOGIN)) {
            query.setString(1, usuario.getDPI_o_Pasaporte());
            query.setString(2, usuario.getContraseña());
            ResultSet result = query.executeQuery();
            if (result.next()) {
                usuario.setNombre(result.getString("Nombre"));
                usuario.setFoto(result.getString("Foto"));
                usuario.setTelefono(result.getString("Telefono"));
                usuario.setOrganizacion(result.getString("Organizacion"));
                usuario.setEmail(result.getString("Email"));
                usuario.setContraseña("");
                usuario.setEstado(result.getBoolean("Estado"));
                usuario.setRol(result.getString("Rol"));
                return usuario;
            } else {
                throw new ObjetoExistenteException("Usuario o contraseña incorrecta, vuelva a intentar");
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Usuario> seleccionar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_TODOS_LOS_USUARIOS);) {
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Usuario usuario = new Usuario(
                        result.getString("DPI_o_Pasaporte"),
                        result.getString("Foto"),
                        result.getString("Nombre"),
                        result.getString("Telefono"),
                        result.getString("Organizacion"),
                        result.getString("Email"),
                        "");//contraseña
                usuario.setEstado(result.getBoolean("Estado"));
                usuario.setRol(result.getString("Rol"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return usuarios;
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
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_CODIGO_QUERY)) {
            query.setString(1, t);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                Usuario usuario = new Usuario(
                        result.getString("DPI_o_Pasaporte"),
                        result.getString("Foto"),
                        result.getString("Nombre"),
                        result.getString("Telefono"),
                        result.getString("Organizacion"),
                        result.getString("Email"),
                        "");
                usuario.setEstado(result.getBoolean("Estado"));
                usuario.setRol(result.getString("Rol"));
                return usuario;
            } 
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }

    @Override
    public Usuario seleccionarPorParametro(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
