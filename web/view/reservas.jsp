<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestión de Reservas - Cereté</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body class="container mt-5">

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>🗓️ Panel de Reservas de Aulas</h2>
            
            <div class="d-flex gap-2">
                <a href="${pageContext.request.contextPath}/UsuarioControlador?accion=listar" class="btn btn-secondary">
                    <i class="fas fa-arrow-left"></i> Volver a Usuarios
                </a>
                
                <a href="${pageContext.request.contextPath}/ReservacionControlador?accion=nuevo" class="btn btn-primary">
                    ➕ Solicitar Nueva Reserva
                </a>
            </div>
        </div>

        <table class="table table-striped table-bordered text-center">
            <thead class="table-dark">
                <tr>
                    <th>Código Reserva</th>
                    <th>ID Usuario</th>
                    <th>ID Aula</th>
                    <th>Fecha</th>
                    <th>Hora Inicio</th>
                    <th>Hora Fin</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reserva" items="${listaReservas}">
                    <tr>
                        <td>${reserva.id}</td>
                        <td>${reserva.usuarioId}</td>
                        <td>${reserva.espacioId}</td>
                        <td>${reserva.fecha}</td>
                        <td>${reserva.horaInicio}</td>
                        <td>${reserva.horaFin}</td>
                        <td>
                            <span class="badge ${reserva.estado == 'Ocupado' ? 'bg-danger' : 'bg-success'}">
                                ${reserva.estado}
                            </span>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/ReservacionControlador?accion=editar&id=${reserva.id}" class="btn btn-warning btn-sm">✏️ Editar</a>
                            <a href="${pageContext.request.contextPath}/ReservacionControlador?accion=eliminar&id=${reserva.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Seguro que deseas cancelar esta reserva?')">❌ Cancelar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>