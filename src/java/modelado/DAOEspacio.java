package modelado;

import java.util.*;
import java.sql.*;
import uml.Espacio;

public class DAOEspacio implements Operaciones {

    Database db = new Database();

    @Override
    public String insertar(Object obj) {
        Espacio e = (Espacio) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO espacio (nombre, capacidad, ubicacion, descripcion) VALUES(?,?,?,?);";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getCapacidad());
            ps.setString(3, e.getUbicacion());
            ps.setString(4, e.getDescripcion());
            
            int filas = ps.executeUpdate();
            respuesta = "Espacio guardado con éxito";

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            respuesta = "Error al guardar: " + ex.getMessage();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    @Override
    public String eliminar(Object obj) {
        Espacio e = (Espacio) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM espacio WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setLong(1, e.getId());
            int filas = ps.executeUpdate();
            respuesta = "Se eliminaron " + filas + " elementos";

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    @Override
    public String modificar(Object obj) {
        Espacio e = (Espacio) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE espacio SET nombre=?, capacidad=?, ubicacion=?, descripcion=? WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getCapacidad());
            ps.setString(3, e.getUbicacion());
            ps.setString(4, e.getDescripcion());
            ps.setLong(5, e.getId());
            int filas = ps.executeUpdate();
            respuesta = "Se modificaron " + filas + " elementos";

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return respuesta;
    }

    @Override
    public List<?> consultar() {
        List<Espacio> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM espacio";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos.add(new Espacio(rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getInt("capacidad"),
                        rs.getString("ubicacion"),
                        rs.getString("descripcion")));
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }

        return datos;
    }

    @Override
    public List<?> filtrar(String campo, String criterio) {
        List<Espacio> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM espacio WHERE nombre LIKE ? OR ubicacion LIKE ? OR descripcion LIKE ?";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);

            String patron = "%" + criterio + "%";
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, patron);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Espacio e = new Espacio();
                e.setId(rs.getLong("id"));
                e.setNombre(rs.getString("nombre"));
                e.setCapacidad(rs.getInt("capacidad"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setDescripcion(rs.getString("descripcion"));
                datos.add(e);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }

        return datos;
    }

}
