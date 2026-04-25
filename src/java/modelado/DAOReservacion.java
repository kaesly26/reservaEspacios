package modelado;

import java.util.*;
import java.sql.*;
import uml.EstadoReservacion;
import uml.Reservacion;

public class DAOReservacion implements Operaciones {

    Database db = new Database();

    @Override
    public String insertar(Object obj) {
        Reservacion r = (Reservacion) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO reservacion (usuario_id, espacio_id, fecha, hora_inicio, hora_fin, estado) VALUES(?,?,?,?,?,?);";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);

            ps.setLong(1, r.getUsuarioId());
            ps.setLong(2, r.getEspacioId());
            ps.setDate(3, r.getFecha());
            ps.setTime(4, r.getHoraInicio());
            ps.setTime(5, r.getHoraFin());
            ps.setString(6, r.getEstado().name());

            int filas = ps.executeUpdate();
            respuesta = "Reserva guardada con éxito";

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
        Reservacion r = (Reservacion) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM reservacion WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setLong(1, r.getId());
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
        Reservacion r = (Reservacion) obj;
        Connection cn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE reservacion SET usuario_id=?, espacio_id=?, fecha=?, hora_inicio=?, hora_fin=?, estado=? WHERE id=?";
        String respuesta = "";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            ps.setLong(1, r.getUsuarioId());
            ps.setLong(2, r.getEspacioId());
            ps.setDate(3, r.getFecha());
            ps.setTime(4, r.getHoraInicio());
            ps.setTime(5, r.getHoraFin());
            ps.setString(6, r.getEstado().name());
            ps.setLong(7, r.getId());
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
        List<Reservacion> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM reservacion";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos.add(new Reservacion(rs.getLong("id"),
                        rs.getLong("usuario_id"),
                        rs.getLong("espacio_id"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_inicio"),
                        rs.getTime("hora_fin"),
                        EstadoReservacion.valueOf(rs.getString("estado"))));
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
        List<Reservacion> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM reservacion WHERE estado LIKE ?";
        try {
            Class.forName(db.getDriver());
            cn = DriverManager.getConnection(
                    db.getUrl(), db.getUsuario(), db.getContraseña());
            ps = cn.prepareStatement(sql);

            String patron = "%" + criterio + "%";
            ps.setString(1, patron);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservacion r = new Reservacion();
                r.setId(rs.getLong("id"));
                r.setUsuarioId(rs.getLong("usuario_id"));
                r.setEspacioId(rs.getLong("espacio_id"));
                r.setFecha(rs.getDate("fecha"));
                r.setHoraInicio(rs.getTime("hora_inicio"));
                r.setHoraFin(rs.getTime("hora_fin"));
                r.setEstado(EstadoReservacion.valueOf(rs.getString("estado")));
                datos.add(r);
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
