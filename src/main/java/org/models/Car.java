package org.models;

import org.bson.types.ObjectId;

import java.util.Objects;

public final class Car {

    private ObjectId id;
    private String matricule;
    private History carInfo;

    public Car(ObjectId id, String matricule, History carInfo) {
        this.id = id;
        this.matricule = matricule;
        this.carInfo = carInfo;
    }

    public Car(String matricule, History carInfo) {
        this.matricule = matricule;
        this.carInfo = carInfo;
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


    public ObjectId getId() {
        return id;
    }

    public Car setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getMatricule() {
        return matricule;
    }

    public Car setMatricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public History getCarInfo() {
        return carInfo;
    }

    public Car setCarInfo(History carInfo) {
        this.carInfo = carInfo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id) &&
                matricule.equals(car.matricule) &&
                carInfo.equals(car.carInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matricule, carInfo);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
//                ",Entered Date=" + carInfo.getDateEntered() +
//                ", Release Date=" + carInfo.getDateRelease() +
                "\n" +
                '}';
    }

}
