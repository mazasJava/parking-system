package org.models;

public class Places {

    private int numPlace;
    private String Division;

    public Places() {
    }

    public Places(int numPlace, String division) {
        this.numPlace = numPlace;
        Division = division;
    }

    public int getNumPlace() {
        return numPlace;
    }

    @Override
    public String toString() {
        return "Places{" +
                "numPlace=" + numPlace +
                ", Division='" + Division + '\'' +
                '}';
    }

    public void setNumPlace(int numPlace) {
        this.numPlace = numPlace;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }
}
