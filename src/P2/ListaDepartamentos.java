package P2;

import Pojos.Departament;
import Pojos.Empleat;

import java.util.ArrayList;
import java.util.List;

public class ListaDepartamentos {
    private List<Departament> departamentos = new ArrayList<Departament>();

    public ListaDepartamentos() {

    }

    public void add(Departament departament) {
        departamentos.add(departament);
    }

    public List<Departament> getListaDepartamentos() {
        return departamentos;
    }
}
