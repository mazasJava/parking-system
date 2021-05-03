package org.models;

import org.bson.types.ObjectId;

public class Client {

    private ObjectId id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String carPlate;

    public Client() {
    }

    public Client(ObjectId id, String name, String email, String phone, String password, String carPlate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.carPlate = carPlate;
    }

    public ObjectId getId() {
        return id;
    }

    public Client setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Client setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Client setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public Client setCarPlate(String carPlate) {
        this.carPlate = carPlate;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
