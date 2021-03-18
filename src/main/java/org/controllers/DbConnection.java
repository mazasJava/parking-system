package org.controllers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DbConnection {
    MongoClient mongoClient;

    public boolean connect() {
        try {
            this.mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net/"));
            System.out.println("Successful database connection established. \n");
            return true;
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            return false;
        }
    }
    public MongoClient getConnection() {
        return mongoClient;
    }
    public static void main(String [] args){
        try {
            System.out.println("aksjklajsdlk");
           final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net/"));
            System.out.println("Successful database connection established. \n");
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }
}
