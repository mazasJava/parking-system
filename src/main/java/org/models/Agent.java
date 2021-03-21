package org.models;

public class Agent {

    private String fastName;
    private String userName;
    private String cin;

    public Agent() {
    }

    public Agent(String nom, String prenom, String numCin) {
        this.fastName = nom;
        this.userName = prenom;
        this.cin = numCin;
    }

    public String getNom() {
        return fastName;
    }

    public void setNom(String fastName) {
        this.fastName = fastName;
    }

    public String getPrenom() {
        return userName;
    }

    public void setPrenom(String userName) {
        this.userName = userName;
    }

    public String getNumCin() {
        return cin;
    }

    public void setNumCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "nom='" + fastName + '\'' +
                ", prenom='" + userName + '\'' +
                ", numCin='" + cin + '\'' +
                '}';
    }
}
