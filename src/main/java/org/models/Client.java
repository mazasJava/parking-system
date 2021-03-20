package org.models;

public class Client {

    private int id;
    private String lastname;
    private String userName;
    private String phone;
    private String adresse;
    private String cin;

    public Client() {
    }

    public Client(int id, String lastname, String userName, String phone, String adresse, String cin) {
        this.id = id;
        this.lastname = lastname;
        this.userName = userName;
        this.phone = phone;
        this.adresse = adresse;
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}
