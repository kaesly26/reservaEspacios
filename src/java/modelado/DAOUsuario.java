package modelado;

import java.util.*;
import java.sql.*;
import uml.RolUsuario;
import uml.Usuario;

public class DAOUsuario implements Operaciones {

    Database db = new Database();

    @Override
    public String insertar(Object obj) {
        Usuario u = (Usuario) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO usuario (nombre, correo, rol) VALUES(?,?,?);";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getRol().name());

            int filas = ps.executeUpdate();
            respuesta = "Usuario registrado con éxito";

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
        Usuario u = (Usuario) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM usuario WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setLong(1, u.getId());
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
        Usuario u = (Usuario) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE usuario SET nombre=?, correo=?, rol=? WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getRol().name());
            ps.setLong(4, u.getId());
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
    public List<Usuario> consultar() {
        List<Usuario> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos.add(new Usuario(rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        RolUsuario.valueOf(rs.getString("rol"))));
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
    public List<Usuario> filtrar(String campo, String criterio) {
        List<Usuario> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM usuario WHERE nombre LIKE ? OR correo LIKE ?";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);

            String patron = "%" + criterio + "%";
            for (int i = 1; i <= 2; i++) {
                ps.setString(i, patron);
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                String rolStr = rs.getString("rol");
                u.setRol(RolUsuario.valueOf(rolStr));
                datos.add(u);
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
