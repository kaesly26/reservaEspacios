package com.reserva.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Katy Lorena
 */
public class Conexion {
    
    private final String base = "reserva_aulas_cerete"; 
    private final String user = "root";
    private final String password = "nuevaconexion";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión: " + e);
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return con;
    }
}