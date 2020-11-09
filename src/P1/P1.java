package P1;

import Pojos.Departament;
import Pojos.Empleat;

import java.io.*;
import java.util.Scanner;

public class P1 {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        P1 program = new P1();
        program.menu();
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("---- Ficheros con objetos ----\n" +
                    "1. Crear fichero empleados\n" +
                    "2. Crear fichero departamentos\n" +
                    "3. Leer fichero empleados\n" +
                    "4. Leer fichero departamentos\n" +
                    "5. Salir");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    fitxerEmpleats();
                    break;
                case 2:
                    fitxerDepartaments();
                    break;
                case 3:
                    llegirEmpleats();
                    break;
                case 4:
                    llegirDepartaments();
                    break;
                case 5:
                    System.out.println("Cerrando el programa...");
                    break;
            }
        } while (opcion != 5);
    }

    public void fitxerEmpleats() {
        Empleat empleado;
        String[] apellidos = {"Carlos", "Daniel", "Marc", "Didac", "Marcos"};
        int[] idEmpleat = {1, 2, 3, 4, 5};
        double[] salario = {2000, 1500, 2500, 1000, 3000};
        int[] departamento = {10, 20, 30, 40, 50};
        File fichero = new File("FicheroPersona.dat");
        ObjectOutputStream objectOS = null;

        try {
            objectOS = new ObjectOutputStream(new FileOutputStream(fichero));
            for (int i = 0; i < apellidos.length; i++) {
                empleado = new Empleat(idEmpleat[i], apellidos[i], departamento[i], salario[i]);
                objectOS.writeObject(empleado);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            objectOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Empleados introducidos.");
    }

    public void fitxerDepartaments() {
        Departament departament;
        String[] nombreDep = {"Recursos humanos", "Desarrolladores", "Sistemas", "Administración", "Contables"};
        int[] idDepartment = {10, 20, 30, 40, 50};
        String[] localitat = {"Barcelona", "Hospitalet", "Badalona", "Tarragona", "Girona"};

        File ficheroDepartamentos = new File("FicheroDepartamento.dat");
        ObjectOutputStream objectOS = null;
        try {
            objectOS = new ObjectOutputStream(new FileOutputStream(ficheroDepartamentos));
            for (int i = 0; i < nombreDep.length; i++) {
                departament = new Departament(idDepartment[i], nombreDep[i], localitat[i]);
                objectOS.writeObject(departament);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            objectOS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Departamentos introducidos.");
    }

    public void llegirEmpleats() {
        Empleat empleado;
        File fichero = new File("FicheroPersona.dat");
        ObjectInputStream dataIS = null;

        try {
            dataIS = new ObjectInputStream(new FileInputStream(fichero));
            int i = 1;
            while (true) {
                empleado = (Empleat) dataIS.readObject();
                System.out.println(i + "=>");
                i++;
                System.out.println("Id: " + empleado.getId() + " Cognom: " + empleado.getCognom() + " Departament: " + empleado.getDepartament() + " Salario " + empleado.getSalari());
            }
        } catch (EOFException eo) {
            System.out.println("------------------------");
        } catch (StreamCorruptedException x) {

        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException cl) {

        }
        try {
            dataIS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void llegirDepartaments() {
        Departament departament;
        File ficheroDepartments = new File("FicheroDepartamento.dat");
        ObjectInputStream dataIS = null;
        try {
            dataIS = new ObjectInputStream(new FileInputStream(ficheroDepartments));
            int i = 1;
            while (true) {
                departament = (Departament) dataIS.readObject();
                System.out.println(i + "=>");
                i++;
                System.out.printf("Id Departament: " + departament.getId() + " Nom: " + departament.getNom() + " Localitat: " + departament.getLocalitat()+ "\n");
            }
        } catch (EOFException eo) {
            System.out.println("Fin de lectura");
        } catch (StreamCorruptedException x) {

        } catch (IOException ex) {
            ex.getMessage();
        } catch (ClassNotFoundException cl) {

        }
        try {
            dataIS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
