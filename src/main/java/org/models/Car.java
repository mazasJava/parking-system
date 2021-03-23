package org.models;

import java.util.Date;

public class Car {

    private static int idCar;
    private String matricule;

    public Car() {
    }

    public Car(String matricule) {
        this.idCar++;
        this.matricule = matricule;
    }

    public int getidCar() {
        return idCar;
    }

    public void setidCar(int idCar) {
        this.idCar = idCar;
    }

    public String getmatricule() {
        return matricule;
    }

    public void setmatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "idCar=" + idCar +
                ", matricule='" + matricule + '\'' +
                '}';
    }
}
