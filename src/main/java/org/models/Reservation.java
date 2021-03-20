package org.models;

import java.util.Date;

public class Reservation {

    private Date dateEntered;
    private Date dateRelease;

    public Reservation() {
    }

    public Reservation(Date dateEntered, Date dateRelease) {
        this.dateEntered = dateEntered;
        this.dateRelease = dateRelease;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "dateEntered=" + dateEntered +
                ", dateRelease=" + dateRelease +
                '}';
    }
}
