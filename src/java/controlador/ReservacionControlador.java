package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reservacion;
import dao.ReservacionDAO;

@javax.servlet.annotation.WebServlet(name = "ReservacionControlador", urlPatterns = {"/ReservacionControlador"})

public class ReservacionControlador extends HttpServlet {

    private ReservacionDAO resDao = new ReservacionDAO();
    private final String pagReservas = "/view/reservas.jsp";

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
        int result = resDao.eliminar(id);

        if (result > 0) {
            request.getSession().setAttribute("success", "Reserva con id " + id + " fue eliminado!");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar la reservación");
        }
        response.sendRedirect("ReservacionControlador?accion=listar");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Reservacion obj = resDao.buscaPorId(id);

        if (obj != null) {
            request.setAttribute("reservacion", obj);
            request.getRequestDispatcher(pagReservas).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontro reserva con ese ID " + id);
            response.sendRedirect("ReservacionControlador?accion=listar");
        }
    }

    private void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Reservacion obj = new Reservacion();
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            obj.setId(0L);
        } else {
            obj.setId(Long.parseLong(idStr));
        }

        // IDs convertidos usando valueOf para quitar la advertencia del IDE
        String userParam = request.getParameter("usuario_id");
        String espacioParam = request.getParameter("espacio_id");
        obj.setUsuarioId((userParam != null && !userParam.isEmpty()) ? Long.valueOf(userParam) : 0L);
        obj.setEspacioId((espacioParam != null && !espacioParam.isEmpty()) ? Long.valueOf(espacioParam) : 0L);

        // Control de Fecha segura
        String fechaStr = request.getParameter("fecha");
        if (fechaStr != null && !fechaStr.trim().isEmpty()) {
            obj.setFecha(java.sql.Date.valueOf(fechaStr));
        }

        // Control de Horas seguras
        String horaInicioStr = request.getParameter("hora_inicio");
        String horaFinStr = request.getParameter("hora_fin");

        if (horaInicioStr != null && !horaInicioStr.trim().isEmpty()) {
            obj.setHoraInicio(java.sql.Time.valueOf(horaInicioStr + ":00"));
        }
        if (horaFinStr != null && !horaFinStr.trim().isEmpty()) {
            obj.setHoraFin(java.sql.Time.valueOf(horaFinStr + ":00"));
        }

        obj.setEstado(request.getParameter("estado"));

        int result;

        if (obj.getId() == 0L) {
            result = resDao.registrar(obj);
        } else {
            result = resDao.editar(obj);
        }

        if (result > 0) {
            request.getSession().setAttribute("success", "Datos guardados!");
            response.sendRedirect("ReservacionControlador?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar datos");
            request.setAttribute("reservacion", obj);
            request.getRequestDispatcher(pagReservas).forward(request, response);
        }
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Reservacion nuevoReserva = new Reservacion();
        nuevoReserva.setId(0L);
        request.setAttribute("reservacion", nuevoReserva);
        request.getRequestDispatcher(pagReservas).forward(request, response);
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("listaReservas", resDao.ListarTodos());
        request.getRequestDispatcher(pagReservas).forward(request, response);

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
