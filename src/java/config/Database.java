package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String driver;
    private final String url;
    private final String usuario;
    private final String contraseña;

    public Database() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/reserva_aulas_cerete?useSSL=false&serverTimezone=UTC&transformedBitIsBoolean=false";
        this.usuario = "katy";
        this.contraseña = "987456123";
    }
    
    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("CONEXIÓN EXITOSA A: " + url);
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ERROR SQL: Verifica puerto, usuario o contraseña: " + e.getMessage());
        }
        return cn;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
}
