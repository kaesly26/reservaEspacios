package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import dao.UsuarioDAO;

/**
 *
 * @author Katy Perez
 */
@WebServlet(name = "LoginControlador", urlPatterns = {"/LoginControlador"})
public class LoginControlador extends HttpServlet {

    private UsuarioDAO usuDao = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (accion.equals("ingresar")) {
            String correo = request.getParameter("txtCorreo");
            String clave = request.getParameter("txtPass");

            Usuario user = usuDao.login(correo, clave);

            if (user != null) {
                System.out.println("Usuario encontrado. Rol en BD: " + user.getRol());
                if (user.getRol() != null && user.getRol().equals("Admin")) {
                    request.getSession().setAttribute("usuarioLogueado", user);
                    response.sendRedirect("UsuarioControlador?accion=listar");
                } else {
                    request.setAttribute("error", "Acceso denegado: Solo administradores");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Correo o clave incorrectos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (accion.equals("salir")) {
            // CERRAR SESIÓN
            request.getSession().invalidate();
            response.sendRedirect("login.jsp");
        }
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
