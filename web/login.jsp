<%-- 
    Document   : login
    Created on : 11/05/2026, 11:52:33 AM
    Author     : Katy Perez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>Login page</title>
    </head>
    <body>
        <form action="LoginControlador" method="post">
            <input type="hidden" name="accion" value="ingresar">
            <div class="mb-3">
                <label>Correo:</label>
                <input type="email" name="txtCorreo" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Contraseña:</label>
                <input type="password" name="txtPass" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Entrar</button>
            <c:if test="${not empty error}">
                <p class="text-danger mt-2">${error}</p>
            </c:if>
        </form>
    </body>
</html>
