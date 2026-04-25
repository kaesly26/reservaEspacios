package modelado;

import java.util.List;

public interface Operaciones {

    public String insertar(Object obj);

    public String eliminar(Object obj);

    public String modificar(Object obj);

    public List<?> consultar();

    public List<?> filtrar(String campo, String criterio);

}
