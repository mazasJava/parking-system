package org.models;

import java.util.Date;

public class Reclamation {

    private int numeroRec;
    private String typeRecl;
    private Date dateRecl;
    private String statut;

    public Reclamation() {
    }

    public Reclamation(int numeroRec, String typeRecl, Date dateRecl, String statut) {
        this.numeroRec = numeroRec;
        this.typeRecl = typeRecl;
        this.dateRecl = dateRecl;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "numeroRec=" + numeroRec +
                ", typeRecl='" + typeRecl + '\'' +
                ", dateRecl=" + dateRecl +
                ", statut='" + statut + '\'' +
                '}';
    }
}
