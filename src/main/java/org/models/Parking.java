package org.models;

public class Parking {

    private int numberPark;
    private String adresse;
    private int numberVec;

    public Parking() {
    }

    public Parking(int numberPark, String adresse, int numberVec) {
        this.numberPark = numberPark;
        this.adresse = adresse;
        this.numberVec = numberVec;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "numberPark=" + numberPark +
                ", adresse='" + adresse + '\'' +
                ", numberVec=" + numberVec +
                '}';
    }
}
