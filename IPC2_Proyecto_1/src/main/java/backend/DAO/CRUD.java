/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.DAO;

import backend.exceptions.ObjetoExistenteException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author helder
 * @param <T>
 */
public interface CRUD <T> {
    void insetar (T t) throws SQLException,ObjetoExistenteException;
    List<T> seleccionar () throws SQLException;
    void actualiza (T t) throws SQLException, ObjetoExistenteException;
    void eliminar (T t) throws SQLException;
    List<T> seleccionarPorParametroDpOE (String t) throws SQLException;
    T seleccionarPorParametro (String t) throws SQLException;
    void insertPorAdmin (T t) throws SQLException, ObjetoExistenteException;
    T seleccionarPorParametro (int t) throws SQLException;
    
}
