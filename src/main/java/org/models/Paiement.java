package org.models;

import java.util.Date;

public class Paiement {

    private int numero;
    private Date date;

    public Paiement() {
    }

    public Paiement(int numero, Date date) {
        this.numero = numero;
        this.date = date;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Paiement{" +
                "numero=" + numero +
                ", date=" + date +
                '}';
    }
}
