package org.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public class History {

    private ObjectId id;
    private ObjectId carId;
    private String dateEntered;
    private String dateRelease;
    private String matricule;


    public String getMatricule() {
        return matricule;
    }

    public History setMatricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public History(ObjectId id, ObjectId carId, String dateEntered, String dateRelease, String matricule) {
        this.id = id;
        this.carId = carId;
        this.dateEntered = dateEntered;
        this.dateRelease = dateRelease;
        this.matricule = matricule;
    }

    public History(ObjectId id, ObjectId carId, String dateEntered, String dateRelease) {
        this.id = id;
        this.carId = carId;
        this.dateEntered = dateEntered;
        this.dateRelease = dateRelease;
    }

    public History() {
    }

    public ObjectId getId() {
        return id;
    }

    public History setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public ObjectId getCarId() {
        return carId;
    }

    public History setCarId(ObjectId carId) {
        this.carId = carId;
        return this;
    }


    public String getDateEntered() {
        return dateEntered;
    }

    public History setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
        return this;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public History setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
        return this;
    }

    @Override
    public String toString() {
        return "History{" +
                "\n"+
                "id=" + id +
                ", carId=" + carId +
                ", dateEntered=" + dateEntered +
                ", dateRelease=" + dateRelease +
                ", mat=" + matricule +
                '}'+
                "\n";
    }
}
