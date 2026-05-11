package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import dao.UsuarioDAO;

public class UsuarioControlador extends HttpServlet {

    private UsuarioDAO usuDao = new UsuarioDAO();
    private final String pagListar = "/view/listar.jsp";
    private final String pagNuevo = "/view/nuevo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("usuarioLogueado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        int result = usuDao.eliminar(id);

        if (result > 0) {
            request.getSession().setAttribute("success", "Usuario con id " + id + " fue eliminado!");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar al usuario");
        }
        response.sendRedirect("UsuarioControlador?accion=listar");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Usuario obj = usuDao.buscaPorId(id);

        if (obj != null) {
            request.setAttribute("usuario", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontro usuario con ese ID " + id);
            response.sendRedirect("UsuarioControlador?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario obj = new Usuario();
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            obj.setId(0L);
        } else {
            obj.setId(Long.parseLong(idStr));
        }

        obj.setNombre(request.getParameter("nombre"));
        obj.setCorreo(request.getParameter("correo"));
        obj.setPassword(request.getParameter("password"));
        obj.setRol(request.getParameter("rol"));

        int result;

        if (obj.getId() == 0L) {
            result = usuDao.registrar(obj);
        } else {
            result = usuDao.editar(obj);
        }

        if (result > 0) {
            request.getSession().setAttribute("success", "Datos guardados!");
            response.sendRedirect("UsuarioControlador?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar datos");
            request.setAttribute("usuario", obj);
            request.getRequestDispatcher(pagNuevo).forward(request, response);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(0L);
        request.setAttribute("usuario", nuevoUsuario);
        request.getRequestDispatcher(pagNuevo).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("usuario", usuDao.ListarTodos());
        request.getRequestDispatcher(pagListar).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
