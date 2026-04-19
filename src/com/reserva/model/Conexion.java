package com.reserva.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    private final String base = "reserva_aulas_cerete"; 
    private final String user = "katy";
    private final String password = "987456123";
    private final String url = "jdbc:mysql://localhost:3306/" + base + "?useSSL=false&serverTimezone=UTC&transformedBitIsBoolean=false";
    private Connection con = null;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el Driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
        }
        return con;
    }
}