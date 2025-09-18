/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author helder
 */
public class DBConnections {
    
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA =   "gestion_de_congreso";
    private static final String USER_NAME = "Usuario1";
    private static final String PASSWORD = "Usuario12025*";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;
    private static DBConnections instance;
    private final Connection connection;
    
    private DBConnections() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
    
    public Connection getConnection() {
        return connection;
    }

    public static DBConnections getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnections();
        }
        return instance;
    }
    
}
