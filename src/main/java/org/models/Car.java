package org.models;

import java.util.Date;

public class Car {

    private int idVeh;
    private String iMatricule;

    public Car() {
    }

    public Car(int idVeh, String iMatricule) {
        this.idVeh = idVeh;
        this.iMatricule = iMatricule;
    }

    public int getIdVeh() {
        return idVeh;
    }

    public void setIdVeh(int idVeh) {
        this.idVeh = idVeh;
    }

    public String getiMatricule() {
        return iMatricule;
    }

    public void setiMatricule(String iMatricule) {
        this.iMatricule = iMatricule;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "idVeh=" + idVeh +
                ", iMatricule='" + iMatricule + '\'' +
                '}';
    }
}
