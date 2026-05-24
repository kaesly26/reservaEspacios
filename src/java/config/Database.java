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
        // Tip de actualización: Si usas un conector moderno de MySQL (versión 8.0+), 
        // es mejor cambiar "com.mysql.jdbc.Driver" por "com.mysql.cj.jdbc.Driver"
        this.driver = "com.mysql.jdbc.Driver"; 
        this.url = "jdbc:mysql://localhost:3306/reserva_aulas_cerete?useSSL=false&serverTimezone=UTC";
        this.usuario = "root";
        this.contraseña = "";
    }
    
    public Connection conectar() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("CONEXIÓN EXITOSA A LA BASE DE DATOS");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ERROR SQL: Verifica puerto, usuario o contraseña: " + e.getMessage());
        }
        return cn;
    }

    // ==========================================
    // METODO MAIN TEMPORAL PARA PRUEBAS
    // ==========================================
    public static void main(String[] args) {
        Database db = new Database();
        Connection pruebaConexion = db.conectar();
        
        // Verificamos si no es nula
        if(pruebaConexion != null) {
            System.out.println("¡Felicitaciones! Java se comunicó con XAMPP perfectamente.");
            try {
                pruebaConexion.close(); // Cerramos la conexión de prueba
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("La conexión falló. Revisa los mensajes de error arriba.");
        }
    }

    // Tus Getters se quedan aquí abajo...
    public String getDriver() { return driver; }
    public String getUrl() { return url; }
    public String getUsuario() { return usuario; }
    public String getContraseña() { return contraseña; }
}