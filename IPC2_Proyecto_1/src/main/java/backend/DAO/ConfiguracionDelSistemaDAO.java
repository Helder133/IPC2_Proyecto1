/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.DAO;

import backend.db.DBConnections;
import backend.exceptions.ObjetoExistenteException;
import backend.modelos.ConfiguracionDelSistema;
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
public class ConfiguracionDelSistemaDAO implements CRUD<ConfiguracionDelSistema>{
    private static final String SELECCIONAR_CONFIGURACION = "SELECT * FROM Configuracion_Del_Sistema";
    private static final String ACTUALIZAR_CONFIGURACION = "update Configuracion_Del_Sistema set Porcentaje_Comision = ?, Precio_Minimo_Congreso = ? where Id_Configuracion = ?";
    private static final String SELECCIONAR_CONFIGURACION_POR_ID = "SELECT * FROM Configuracion_Del_Sistema WHERE Id_Configuracion = ?";
    
    @Override
    public void insetar(ConfiguracionDelSistema t) throws SQLException, ObjetoExistenteException {
        
    }

    @Override
    public List<ConfiguracionDelSistema> seleccionar() throws SQLException {
        int max = 10;
        int contador = 0;
        List<ConfiguracionDelSistema> configuraciones = new ArrayList<>();
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_CONFIGURACION)) {
            ResultSet result = query.executeQuery();
            while (result.next() && (contador < max)) {
                contador++;
                ConfiguracionDelSistema institucion = new ConfiguracionDelSistema(
                        result.getBigDecimal("Porcentaje_Comision"),
                        result.getBigDecimal("Precio_Minimo_Congreso")
                );

                institucion.setId(result.getInt("Id_Configuracion"));
                configuraciones.add(institucion);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return configuraciones;
    }

    @Override
    public void actualiza(ConfiguracionDelSistema t) throws SQLException, ObjetoExistenteException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement insert = connection.prepareStatement(ACTUALIZAR_CONFIGURACION);) {
            insert.setBigDecimal(1, t.getPorcentajeComicion());
            insert.setBigDecimal(2, t.getPrecioMinimo());
            insert.setInt(3, t.getId());
            
            int filas = insert.executeUpdate();
            
            if (filas == 0) {
                throw new SQLException("Error al intentar Actualizar");
            }
            
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void eliminar(ConfiguracionDelSistema t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ConfiguracionDelSistema> seleccionarPorParametroDpOE(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ConfiguracionDelSistema seleccionarPorParametro(String t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertPorAdmin(ConfiguracionDelSistema t) throws SQLException, ObjetoExistenteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ConfiguracionDelSistema seleccionarPorParametro(int t) throws SQLException {
        Connection connection = DBConnections.getInstance().getConnection();
        try (PreparedStatement query = connection.prepareStatement(SELECCIONAR_CONFIGURACION_POR_ID)) {
            query.setInt(1, t);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                ConfiguracionDelSistema coniguracion = new ConfiguracionDelSistema(
                        result.getBigDecimal("Porcentaje_Comision"),
                        result.getBigDecimal("Precio_Minimo_Congreso"));

                coniguracion.setId(result.getInt("Id_Configuracion"));

                return coniguracion;
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    
}
