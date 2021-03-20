package org.models;

import java.util.Date;

public class Historique {

    private int id;
    private String state;
    private Date dateEntered;
    private Date dateRelease;

    public Historique() {
    }

    public Historique(int id, String state, Date dateEntered, Date dateRelease) {
        this.id = id;
        this.state = state;
        this.dateEntered = dateEntered;
        this.dateRelease = dateRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "Historique{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", dateEntered=" + dateEntered +
                ", dateRelease=" + dateRelease +
                '}';
    }
}
