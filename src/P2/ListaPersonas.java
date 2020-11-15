package P2;

import Pojos.Empleat;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonas {
    private List<Empleat> empleados = new ArrayList<Empleat>();

    public ListaPersonas() {

    }

    public void add(Empleat empleado) {
        empleados.add(empleado);
    }

    public List<Empleat> getListaPersonas() {
        return empleados;
    }
}
