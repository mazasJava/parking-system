package org.models;

import java.util.Date;

public class Reservation {

    private Date dateEntree;
    private Date dateSortie;

    public Reservation() {
    }

    public Reservation(Date dateEntree, Date dateSortie) {
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "dateEntree=" + dateEntree +
                ", dateSortie=" + dateSortie +
                '}';
    }
}
