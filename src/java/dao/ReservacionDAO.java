package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Reservacion;

public class ReservacionDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Database db = new Database();

    public ArrayList<Reservacion> ListarTodos() {
        ArrayList<Reservacion> lista = new ArrayList<Reservacion>();
        try {
            cn = db.conectar();
            String sql = "SELECT * FROM reservacion";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservacion obj = new Reservacion();
                obj.setId(rs.getLong("id"));
                obj.setUsuarioId(rs.getLong("usuario_id"));
                obj.setEspacioId(rs.getLong("espacio_id"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setHoraInicio(rs.getTime("hora_inicio"));
                obj.setHoraFin(rs.getTime("hora_fin"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return lista;
    }

    public int registrar(Reservacion obj) {
        int result = 0;

        try {
            cn = db.conectar();

            String sql = "INSERT INTO reservacion(usuario_id, espacio_id, fecha, hora_inicio, hora_fin, estado) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, obj.getUsuarioId());
            ps.setLong(2, obj.getEspacioId());
            ps.setDate(3, obj.getFecha());
            ps.setTime(4, obj.getHoraInicio());
            ps.setTime(5, obj.getHoraFin());
            ps.setString(6, obj.getEstado());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public int editar(Reservacion obj) {
        int result = 0;

        try {
            cn = db.conectar();

            String sql = "UPDATE reservacion SET usuario_id=?, espacio_id=?, fecha=?, hora_inicio=?, hora_fin=?, estado=? "
                    + "WHERE id=?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, obj.getUsuarioId());
            ps.setLong(2, obj.getEspacioId());
            ps.setDate(3, obj.getFecha());
            ps.setTime(4, obj.getHoraInicio());
            ps.setTime(5, obj.getHoraFin());
            ps.setString(6, obj.getEstado());
            ps.setLong(7, obj.getId());

            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public int eliminar(Long id) {
        int result = 0;

        try {
            cn = db.conectar();
            String sql = "DELETE FROM reservacion WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, id);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }
        return result;
    }

    public Reservacion buscaPorId(Long id) {
        Reservacion obj = null;
        try {
            cn = db.conectar();
            String sql = "SELECT * FROM reservacion where id = ?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Reservacion();
                obj.setId(rs.getLong("id"));
                obj.setUsuarioId(rs.getLong("usuario_id"));
                obj.setEspacioId(rs.getLong("espacio_id"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setHoraInicio(rs.getTime("hora_inicio"));
                obj.setHoraFin(rs.getTime("hora_fin"));
                obj.setEstado(rs.getString("estado"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {

            }
        }

        return obj;
    }
}
