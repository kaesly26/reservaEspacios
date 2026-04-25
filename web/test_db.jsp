<%-- 
    Document   : test_db
    Created on : 25/04/2026, 10:51:44 AM
    Author     : Katy Lorena
--%>

<%@page import="java.sql.Connection"%>
<%@page import="modelado.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Prueba de Conexión</title></head>
    <body>
        <%
            Database db = new Database();
            Connection cn = db.conectar();
            if (cn != null) {
                out.print("<h1 style='color:green'>¡Conexión Exitosa!</h1>");
                cn.close();
            } else {
                out.print("<h1 style='color:red'>Fallo en la conexión. Revisa la consola de GlassFish.</h1>");
            }
        %>
    </body>
</html>
