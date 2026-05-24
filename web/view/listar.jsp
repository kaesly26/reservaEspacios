<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Usuario</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="card">
                <div class="card-body">
                    <a href="LoginControlador?accion=salir" class="btn btn-danger">
                        <i class="fas fa-sign-out-alt"></i> Cerrar Sesión
                    </a>
                    <h3>Gestión de Usuarios</h3>
                    <hr />

                    <a href="UsuarioControlador?accion=nuevo" class="btn btn-success btn-sm">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>

                    <a href="${pageContext.request.contextPath}/ReservacionControlador?accion=listar" class="btn btn-info btn-sm">
                        <i class="fa fa-calendar-alt"></i> Gestionar Reservas
                    </a>
                    <jsp:include page="../components/Mensajes.jsp"/>

                    <table class="table table-bordered table-striped mt-2">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Pass</th>
                                <th>Rol</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuario}" var="item">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.nombre}</td>
                                    <td>${item.correo}</td>
                                    <td>${item.password}</td>
                                    <td>${item.rol}</td>
                                    <td>
                                        <a href="UsuarioControlador?accion=editar&id=${item.id}" class="btn btn-info btn-sm">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a href="UsuarioControlador?accion=eliminar&id=${item.id}"
                                           onclick="return confirm('Está seguro que desea eliminar al empleado con id ${item.id}')"
                                           class="btn btn-danger btn-sm">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${usuario.size() == 0}">
                                <tr>
                                    <td colspan="5">No hay registros</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
