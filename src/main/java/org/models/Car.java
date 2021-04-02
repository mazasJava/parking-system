package org.models;

import org.bson.types.ObjectId;

public class Car {

    private ObjectId id;
    private String matricule;
    private Historique carInfo;

    public Car(ObjectId id, String matricule, Historique carInfo) {
        this.id = id;
        this.matricule = matricule;
        this.carInfo = carInfo;
    }

    public ObjectId getId() {
        return id;
    }

    public Car() {
    }

    public Car(ObjectId id, String matricule) {
        this.id = id;
        this.matricule = matricule;
    }

    public Car(String matricule) {
        this.matricule = matricule;
    }

    public String getmatricule() {
        return matricule;
    }

    public void setmatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Car{" +
                ", matricule='" + matricule + '\'' +
                ", Date Entered=" + carInfo.getDateEntered() +
                ", Date Release=" + carInfo.getDateRelease() +
                "\n"+
                '}';
    }
}
