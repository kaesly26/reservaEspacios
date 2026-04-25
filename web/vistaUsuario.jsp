<%@page import ="modelado.DAOUsuario"%>
<%@page import ="java.util.*" %>
<%@page import ="uml.Usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista Persona</title>
        <script lang="JavaScript">
            function cargar(id, nombre, correo, rol){
                document.formPersona.txtId.value=id;
                document.formPersona.txtNombre.value=nombre;
                document.formPersona.txtCorreo.value=correo;
                document.formPersona.txtRol.value=rol;
            }
        </script>
    </head>

    <%
        DAOUsuario dao = new DAOUsuario();
        List<Usuario> datos = new ArrayList();
    %>

    <body>
        <h3>Registros encontrados: <%= datos.size()%></h3>
        <form name="formUsuario" method="POST" action="SERVUsuario">
            Id de persona<input type="text" name="txtId"><br>
            Nombre: <input type="text" name="txtNombre"><br>
            Correo: <input type="text" name="txtCorreo"><br>
            Rol: <input type="text" name="txtRol"><br>
            <input type="submit" name="btnInsertar" value="Insertar">
            <input type="submit" name="btnModificar" value="Modificar">
            <input type="submit" name="btnEliminar" value="Eliminar">
            <hr> 
            Buscar: <input type="text" name="txtCriterio">
            En base a: <input type="text" name="txtCampo">
            <input type="submit" name="btnFiltrar" value="Filtrar">
            <input type="submit" name="btnReinicia" value="Reiniciar">
        </form>
    <hr><center>
        <table border="1px">
            <tr>
                <td>ID</td><td>NOMBRE</td><td>CORREO</td><td>ROL</td><td>ACCIÓN</td>
            </tr>
            <%
                if (request.getAttribute("filtro") != null) {
                    datos = (List<Usuario>) request.getAttribute("filtro");
                } else if (request.getAttribute("reinicio") != null) {
                    datos = dao.consultar();
                } else {
                    datos = dao.consultar();
                }

                for (Usuario u : datos) {
            %>
            <tr>
                <td> <%=u.getId()%> </td>
                <td> <%=u.getNombre()%> </td>
                <td> <%=u.getCorreo()%> </td>
                <td> <%=u.getRol()%> </td>
                <td> <a href="">Cargar</a> </td>
            </tr>
            <%
                }
            %>
            
        </table>
    </center>



</body>
</html>
