package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Database db = new Database();

    public ArrayList<Usuario> ListarTodos() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            cn = db.conectar();
            String sql = "SELECT * FROM usuario";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getLong("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setPassword(rs.getString("password"));
                obj.setRol(rs.getString("rol"));
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
    
    public Usuario login(String correo, String clave) {
        Usuario obj = null;
        String sql = "SELECT * FROM usuario WHERE correo = ? AND password = ?";
        
        try{
            cn = db.conectar();
            ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getLong("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setPassword(rs.getString("password"));
                obj.setRol(rs.getString("rol"));
            }
            
        } catch (Exception ex) {
            System.err.println("Error en identificación: " + ex.getMessage());
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
        return obj;
    }

    public int registrar(Usuario obj) {
        int result = 0;

        try {
            cn = db.conectar();
            String sql = "INSERT INTO usuario(nombre, correo, password, rol) "
                    + "VALUES (?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getCorreo());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getRol());

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

    public int editar(Usuario obj) {
        int result = 0;

        try {
            cn = db.conectar();
            String sql = "UPDATE usuario SET nombre=?, correo=?, password=?, rol=?"
                    + "WHERE id=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getCorreo());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getRol());
            ps.setLong(5, obj.getId());

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
            String sql = "DELETE FROM usuario WHERE id = ?";
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

    public Usuario buscaPorId(Long id) {
        Usuario obj = null;
        try {
            cn = db.conectar();
            String sql = "SELECT * FROM usuario where id = ?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                obj = new Usuario();
                obj.setId(rs.getLong("id"));
                obj.setNombre(rs.getString("nombre"));
                obj.setCorreo(rs.getString("correo"));
                obj.setPassword(rs.getString("password"));
                obj.setRol(rs.getString("rol"));

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
