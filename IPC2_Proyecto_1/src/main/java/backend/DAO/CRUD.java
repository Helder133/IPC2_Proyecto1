/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.DAO;

import backend.exceptions.ObjetoExistenteException;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public interface CRUD <T> {
    T insetar (T t) throws SQLException,ObjetoExistenteException;
    T[] seleccionar () throws SQLException;
    void actualiza (T t) throws SQLException;
    void eliminar (T t) throws SQLException;
    T seleccionarPorParametro (String t) throws SQLException;
    T seleccionarPorParametro (int t) throws SQLException;
}
