package org.models;

import java.util.Date;

public class Reclamation {

    private int idRec;
    private String typeRecl;
    private Date dateRecl;
    private String statut;

    public Reclamation() {
    }

    public Reclamation(int idRec, String typeRecl, Date dateRecl, String statut) {
        this.idRec = idRec;
        this.typeRecl = typeRecl;
        this.dateRecl = dateRecl;
        this.statut = statut;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getTypeRecl() {
        return typeRecl;
    }

    public void setTypeRecl(String typeRecl) {
        this.typeRecl = typeRecl;
    }

    public Date getDateRecl() {
        return dateRecl;
    }

    public void setDateRecl(Date dateRecl) {
        this.dateRecl = dateRecl;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idRec=" + idRec +
                ", typeRecl='" + typeRecl + '\'' +
                ", dateRecl=" + dateRecl +
                ", statut='" + statut + '\'' +
                '}';
    }
}
