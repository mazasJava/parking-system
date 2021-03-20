package org.models;

public class Agent {

    private String lastName;
    private String userName;
    private String cin;

    public Agent() {
    }

    public Agent(String lastName, String userName, String cin) {
        this.lastName = lastName;
        this.userName = userName;
        this.cin = cin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}
