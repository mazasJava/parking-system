package org.models;

import java.util.Date;

public class Historique {

    private int id;
    private String etat;
    private Date dateEntree;
    private Date dateSortie;

    public Historique() {
    }

    public Historique(int id, String etat, Date dateEntree, Date dateSortie) {
        this.id = id;
        this.etat = etat;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Historique{" +
                "id=" + id +
                ", etat='" + etat + '\'' +
                '}';
    }

}
