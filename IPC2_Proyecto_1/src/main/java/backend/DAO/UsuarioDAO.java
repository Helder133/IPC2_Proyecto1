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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author helder
 */
public class UsuarioDAO implements CRUD<Usuario> {

    private static final String ENCONTRAR_USUARIO_POR_CODIGO_QUERY = "select * from Usuario where DPI_o_Pasaporte = ?";
    private static final String ENCONTRAR_USUARIO_POR_EMAIL_QUERY = "select * from Usuario where Email = ?";
    private static final String ENCONTRAR_USUARIO_POR_EMAIL_QUERY_A = "select * from Usuario where Email = ? AND DPI_o_Pasaporte <> ?";
    private static final String ENCONTRAR_USUARIO_POR_TELEFONO_QUERY = "select * from Usuario where Telefono = ?";
    private static final String ENCONTRAR_USUARIO_POR_TELEFONO_QUERY_A = "select * from Usuario where Telefono = ? AND DPI_o_Pasaporte <> ?";
    private static final String INSETAR_USUARIO = "insert into Usuario (DPI_o_Pasaporte, Foto, Nombre, Telefono, Organizacion, Email, Contraseña) values (?,?,?,?,?,?,?);";
    private static final String INSETAR_USUARIO_POR_ADMIN = "insert into Usuario (DPI_o_Pasaporte, Foto, Nombre, Telefono, Organizacion, Email, Contraseña, Estado, Rol) values (?,?,?,?,?,?,?,?,?);";
    private static final String ENCONTRAR_USUARIO_LOGIN = "select * from Usuario where DPI_o_Pasaporte = ? and Contraseña = ?";
    private static final String SELECCIONAR_TODOS_LOS_USUARIOS = "select * from Usuario";
    private static final String ENCONTRAR_USUARIO_POR_ID_O_NOMBRE = "select * from Usuario where DPI_o_Pasaporte LIKE ? OR Nombre LIKE ?";
    private static final String ACTUALIZAR_USUARIO_SIN_CONTRASEÑA_SIN_IMAGEN_QUERY = "update Usuario set Nombre = ?, Telefono = ?, Organizacion = ?, Email = ?, Estado = ?, Rol = ? where DPI_o_Pasaporte = ?";
    private static final String ACTUALIZAR_USUARIO_SIN_CONTRASEÑA_CON_IMAGEN_QUERY = "update Usuario set Foto = ?, Nombre = ?, Telefono = ?, Organizacion = ?, Email = ?, Estado = ?, Rol = ? where DPI_o_Pasaporte = ?";
    private static final String ACTUALIZAR_USUARIO_CON_CONTRASEÑA_SIN_IMAGEN_QUERY = "update Usuario set Nombre = ?, Telefono = ?, Organizacion = ?, Email = ?, Contraseña = ?, Estado = ?, Rol = ? where DPI_o_Pasaporte = ?";
    private static final String ACTUALIZAR_USUARIO_CON_CONTRASEÑA_CON_IMAGEN_QUERY = "update Usuario set Foto = ?, Nombre = ?, Telefono = ?, Organizacion = ?, Email = ?, Contraseña = ?, Estado = ?, Rol = ? where DPI_o_Pasaporte = ?";

    @Override
    public void insetar(Usuario t) throws SQLException, ObjetoExistenteException {
        if (existeUsuario(t)) {
            throw new ObjetoExistenteException(String.format("Ya hay un usuario asociado con el documento '%s'", t.getDPI_o_Pasaporte()));
        } else if (existeCorreo(t)) {
            throw new ObjetoExistenteException(String.format("El correo '%s' ya esta asociado a otro usuario", t.getEmail()));
        } else if (existeTelefono(t)) {
            throw new ObjetoExistenteException(String.format("El Telefono '%s' ya esta asociado a otro usuario", t.getTelefono()));
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_USUARIO)) {
            insert.setString(1, t.getDPI_o_Pasaporte());
            insert.setString(2, t.getFoto());
            insert.setString(3, t.getNombre());
            insert.setString(4, t.getTelefono());
            insert.setString(5, t.getOrganizacion());
            insert.setString(6, t.getEmail());
            insert.setString(7, t.getContraseña());
            insert.executeUpdate();
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
    
    private boolean existeTelefono(Usuario usuario) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_TELEFONO_QUERY)) {
            query.setString(1, usuario.getTelefono());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private boolean existeCorreoA(Usuario usuario) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_EMAIL_QUERY_A)) {
            query.setString(1, usuario.getEmail());
            query.setString(2, usuario.getDPI_o_Pasaporte());
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
    private boolean existeTelefonoA(Usuario usuario) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_TELEFONO_QUERY_A)) {
            query.setString(1, usuario.getTelefono());
            query.setString(2, usuario.getDPI_o_Pasaporte());
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
        int max = 10;
        int contador = 0;
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_TODOS_LOS_USUARIOS);) {
            ResultSet result = query.executeQuery();
            while (result.next() && contador < max) {
                contador++;
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
    public void actualiza(Usuario t) throws SQLException, ObjetoExistenteException {
        if (existeCorreoA(t)) {
            throw new ObjetoExistenteException(String.format("El correo '%s' ya esta asociado a otro usuario", t.getEmail()));
        } else if (existeTelefonoA(t)) {
            throw new ObjetoExistenteException(String.format("El Telefono '%s' ya esta asociado a otro usuario", t.getTelefono()));
        } else if (t.getRol().equals("Administrador Congreso")){
            if(!existeOrganizacion(t.getOrganizacion())){
                throw new ObjetoExistenteException(String.format("La organizacion a la que quiere asociar "
                        + "al usuario: '%s' no existe", t.getOrganizacion()));
            }
        }
        
        Connection connection = DBConnections.getInstance().getConnection();
        if (StringUtils.isBlank(t.getContraseña()) && StringUtils.isBlank(t.getFoto())) {
        try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_SIN_CONTRASEÑA_SIN_IMAGEN_QUERY);) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getTelefono());
            insert.setString(3, t.getOrganizacion());
            insert.setString(4, t.getEmail());
            insert.setBoolean(5, t.getEstado());
            insert.setString(6, t.getRol());
            insert.setString(7, t.getDPI_o_Pasaporte());
            insert.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else if (StringUtils.isBlank(t.getContraseña()) && StringUtils.isNotBlank(t.getFoto())) {
            try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_SIN_CONTRASEÑA_CON_IMAGEN_QUERY);) {
            insert.setString(1, t.getFoto());
            insert.setString(2, t.getNombre());
            insert.setString(3, t.getTelefono());
            insert.setString(4, t.getOrganizacion());
            insert.setString(5, t.getEmail());
            insert.setBoolean(6, t.getEstado());
            insert.setString(7, t.getRol());
            insert.setString(8, t.getDPI_o_Pasaporte());
            insert.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else if (StringUtils.isNotBlank(t.getContraseña()) && StringUtils.isBlank(t.getFoto())) {
            try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_CON_CONTRASEÑA_SIN_IMAGEN_QUERY);) {
            insert.setString(1, t.getNombre());
            insert.setString(2, t.getTelefono());
            insert.setString(3, t.getOrganizacion());
            insert.setString(4, t.getEmail());
            insert.setString(5, t.getContraseña());
            insert.setBoolean(6, t.getEstado());
            insert.setString(7, t.getRol());
            insert.setString(8, t.getDPI_o_Pasaporte());
            insert.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } else {
            try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_USUARIO_CON_CONTRASEÑA_CON_IMAGEN_QUERY);) {
            insert.setString(1, t.getFoto());
            insert.setString(2, t.getNombre());
            insert.setString(3, t.getTelefono());
            insert.setString(4, t.getOrganizacion());
            insert.setString(5, t.getEmail());
            insert.setString(6, t.getContraseña());
            insert.setBoolean(7, t.getEstado());
            insert.setString(8, t.getRol());
            insert.setString(9, t.getDPI_o_Pasaporte());
            insert.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
    }

    @Override
    public void eliminar(Usuario t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> seleccionarPorParametroDpOE(String t) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        int max = 10;
        int contador = 0;
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_POR_ID_O_NOMBRE)) {
            String busqueda = "%" + t + "%";
            query.setString(1, busqueda);
            query.setString(2, busqueda);
            ResultSet result = query.executeQuery();
            while (result.next() && contador < max) {
                contador++;
                Usuario usuario = new Usuario(
                        result.getString("DPI_o_Pasaporte"),
                        result.getString("Foto"),
                        result.getString("Nombre"),
                        result.getString("Telefono"),
                        result.getString("Organizacion"),
                        result.getString("Email"),
                        ""); //contraseña
                usuario.setEstado(result.getBoolean("Estado"));
                usuario.setRol(result.getString("Rol"));

                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
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
                        ""); //contraseña
                usuario.setEstado(result.getBoolean("Estado"));
                usuario.setRol(result.getString("Rol"));

                String telefono = usuario.getTelefono();
                char espacio = ' ';

                int index = telefono.indexOf(espacio);
                String codigo = telefono.substring(0, index);
                String numero = telefono.substring(index + 1, telefono.length());

                usuario.setCodigo(codigo);
                usuario.setNumero(numero);

                return usuario;
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void insertPorAdmin(Usuario t) throws SQLException, ObjetoExistenteException {
        if (existeUsuario(t)) {
            throw new ObjetoExistenteException(String.format("Ya hay un usuario asociado con el documento '%s'", t.getDPI_o_Pasaporte()));
        } else if (existeCorreo(t)) {
            throw new ObjetoExistenteException(String.format("El correo '%s' ya esta asociado a otro usuario", t.getEmail()));
        } else if (existeTelefono(t)) {
            throw new ObjetoExistenteException(String.format("El Telefono '%s' ya esta asociado a otro usuario", t.getTelefono()));
        }
        
        if (t.getRol().equals("Administrador Congreso")){
            if(!existeOrganizacion(t.getOrganizacion())){
                throw new ObjetoExistenteException(String.format("La organizacion a la que quiere asociar "
                        + "al usuario: '%s' no existe", t.getOrganizacion()));
            }
        }
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(INSETAR_USUARIO_POR_ADMIN);) {
            insert.setString(1, t.getDPI_o_Pasaporte());
            insert.setString(2, t.getFoto());
            insert.setString(3, t.getNombre());
            insert.setString(4, t.getTelefono());
            insert.setString(5, t.getOrganizacion());
            insert.setString(6, t.getEmail());
            insert.setString(7, t.getContraseña());
            insert.setBoolean(8, t.getEstado());
            insert.setString(9, t.getRol());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Usuario seleccionarPorParametro(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean existeOrganizacion(String organizacion) throws SQLException{
        String query1 = "select * from Institucion where Nombre = ?";
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(query1)) {
            query.setString(1, organizacion);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
