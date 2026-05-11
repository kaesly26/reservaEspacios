<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Formulario Usuario</title>
    </head>
    <body>
        <div class="container mt-3">
            <div class="card">
                <div class="card-body">
                    <h3>${usuario.id == 0 ? "Nuevo": "Editar"} Usuario</h3>
                    <hr />
                    <form action="UsuarioControlador" method="post">
                        <input type="hidden" name="id" value="${usuario.id != null ? usuario.id : 0}">
                        <div class="mb-3">
                            <label class="form-label">Nombre:</label>
                            <input value="${usuario.nombre}" name="nombre" type="text" maxlength="50" class="form-control" required="">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Correo:</label>
                            <input value="${usuario.correo}" name="correo" type="text" maxlength="50" class="form-control" required="">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password:</label>
                            <input value="${usuario.password}" name="password" type="text" maxlength="50" class="form-control" required="">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Rol:</label>
                            <select name="rol" class="form-select" required="">
                                <option value="" ${usuario.rol == null ? 'selected' : ''} disabled>Seleccione un rol...</option>
                                <option value="Admin" ${usuario.rol == 'Admin' ? 'selected' : ''}>Admin</option>
                                <option value="User" ${usuario.rol == 'User' ? 'selected' : ''}>User</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <input type="hidden" name="accion" value="guardar">
                            <button type="submit" class="btn btn-success">
                                <i class="fas fa-save"></i> Guardar Usuario
                            </button>
                            <a href="UsuarioControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
