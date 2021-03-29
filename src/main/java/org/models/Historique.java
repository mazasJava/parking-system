package org.models;

import org.bson.types.ObjectId;

import java.util.Date;

public class Historique {

    private ObjectId id;
    private ObjectId carId;
    private String state;
    private String dateEntered;
    private String dateRelease;

    public Historique() {
    }

    public Historique( ObjectId carId, String state, String dateEntered, String dateRelease) {
        this.carId = carId;
        this.state = state;
        this.dateEntered = dateEntered;
        this.dateRelease = dateRelease;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getCarId() {
        return carId;
    }

    public String getState() {
        return state;
    }

    public String getDateEntered() {
        return dateEntered;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setCarId(ObjectId carId) {
        this.carId = carId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDateEntered(String dateEntered) {
        this.dateEntered = dateEntered;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }
}
