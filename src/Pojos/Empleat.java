package Pojos;

import java.io.Serializable;

public class Empleat implements Serializable {
    int id;
    String cognom;
    int departament;
    double salari;

    public Empleat(int id, String cognom, int departament, double salari) {
        this.id = id;
        this.cognom = cognom;
        this.departament = departament;
        this.salari = salari;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getDepartament() {
        return departament;
    }

    public void setDepartament(int departament) {
        this.departament = departament;
    }

    public double getSalari() {
        return salari;
    }

    public void setSalari(double salari) {
        this.salari = salari;
    }

    @Override
    public String toString() {
        return "Empleat{" +
                "id=" + id +
                ", cognom='" + cognom + '\'' +
                ", departament=" + departament +
                ", salari=" + salari +
                '}';
    }
}
