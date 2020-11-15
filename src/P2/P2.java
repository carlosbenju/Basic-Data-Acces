package P2;

import Pojos.Departament;
import Pojos.Empleat;
import com.thoughtworks.xstream.XStream;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class P2 {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        P2 program = new P2();
        program.menu();
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("---- Ficheros con objetos ----\n" +
                    "1. Crear XML empleados\n" +
                    "2. Leer XML empleados\n" +
                    "3. Crear XML departamentos\n" +
                    "4. Leer XML departamentos\n" +
                    "5. Salir");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    try {
                        crearXMLEmpleados();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        leerXMLEmpleados();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        crearXMLDepartamentos();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        leerXMLDepartamentos();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Cerrando el programa...");
                    break;
            }
        } while (opcion != 5);
    }

    public void crearXMLEmpleados() throws IOException, ClassNotFoundException {
        File fichero = new File("FicheroPersona.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        ListaPersonas list = new ListaPersonas();
        try {
            while (true) {
                Empleat empleado = (Empleat) dataIS.readObject();
                list.add(empleado);
            }
        } catch (EOFException ex){}
        dataIS.close();
        try {
            XStream xstream = new XStream();
            xstream.alias("ListaEmpleados", ListaPersonas.class);
            xstream.alias("DatosEmpleado", Empleat.class);
            xstream.addImplicitCollection(ListaPersonas.class, "empleados");
            xstream.toXML(list, new FileOutputStream("Empleados.xml"));
            System.out.println("Creado el fichero XML.");
        } catch (IOException ex) {}
    }

    public void crearXMLDepartamentos() throws IOException, ClassNotFoundException {
        File fichero = new File("FicheroDepartamento.dat");
        FileInputStream filein = new FileInputStream(fichero);
        ObjectInputStream dataIS = new ObjectInputStream(filein);
        ListaDepartamentos list = new ListaDepartamentos();
        try {
            while (true) {
                Departament departament = (Departament) dataIS.readObject();
                list.add(departament);
            }
        } catch (EOFException ex){}
        dataIS.close();
        try {
            XStream xstream = new XStream();
            xstream.alias("ListaEmpleados", ListaPersonas.class);
            xstream.alias("DatosDepartamento", Departament.class);
            xstream.addImplicitCollection(ListaPersonas.class, "departamentos");
            xstream.toXML(list, new FileOutputStream("Departamentos.xml"));
            System.out.println("Creado el fichero XML.");
        } catch (IOException ex) {}
    }

    public void leerXMLEmpleados() throws IOException {
        XStream xstream = new XStream();

        xstream.alias("ListaEmpleados", ListaPersonas.class);
        xstream.alias("DatosPersona", Empleat.class);
        xstream.addImplicitCollection(ListaPersonas.class, "empleados");

        ListaPersonas list = (ListaPersonas) xstream.fromXML(new FileInputStream("Empleados.xml"));
        List<Empleat> listaEmpleados = new ArrayList<Empleat>();
        listaEmpleados = list.getListaPersonas();

        Iterator iterador = listaEmpleados.listIterator();
        while (iterador.hasNext()) {
            Empleat empl = (Empleat) iterador.next();
            System.out.printf("Id: " + empl.getId() + "\nCognom: " + empl.getCognom() + "\nDepartament: " + empl.getDepartament() + "\nSalari: " + empl.getSalari());
        }
    }

    public void leerXMLDepartamentos() throws IOException {
        XStream xstream = new XStream();

        xstream.alias("ListaEmpleados", ListaPersonas.class);
        xstream.alias("DatosPersona", Empleat.class);
        xstream.addImplicitCollection(ListaPersonas.class, "departamentos");

        ListaDepartamentos list = (ListaDepartamentos) xstream.fromXML(new FileInputStream("Departamentos.xml"));
        List<Departament> listaDepartamentos = new ArrayList<Departament>();
        listaDepartamentos = list.getListaDepartamentos();

        Iterator iterador = listaDepartamentos.listIterator();
        while (iterador.hasNext()) {
            Departament department = (Departament) iterador.next();
            System.out.printf("Id: " + department.getId() + "\nNom: " + department.getNom() + "\nLocalitat: " + department.getLocalitat());
        }
    }
}
