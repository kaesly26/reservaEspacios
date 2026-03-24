package com.reserva.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    // Datos de tu base de datos en Cereté
    private final String base = ""; // Nombre de tu DB en MySQL
    private final String user = "root";                // Usuario por defecto
    private final String password = "";                // Tu contraseña de MySQL
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection con = null;

    public Connection getConexion() {
        try {
            // Cargar el driver (Asegúrate de haber agregado la librería JAR)
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión: " + e);
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return con;
    }
}