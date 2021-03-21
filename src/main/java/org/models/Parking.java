package org.models;

public class Parking {

    private int numeroPark;
    private String adresse;
    private int numberVec;

    public Parking() {
    }

    public Parking(int numeroPark, String adresse, int numberVec) {
        this.numeroPark = numeroPark;
        this.adresse = adresse;
        this.numberVec = numberVec;
    }

    public int getNumeroPark() {
        return numeroPark;
    }

    public void setNumeroPark(int numeroPark) {
        this.numeroPark = numeroPark;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumberVec() {
        return numberVec;
    }

    public void setNumberVec(int numberVec) {
        this.numberVec = numberVec;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "numeroPark=" + numeroPark +
                ", adresse='" + adresse + '\'' +
                ", numberVec=" + numberVec +
                '}';
    }
}
