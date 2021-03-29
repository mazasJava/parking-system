package org.controllers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DbConnection {
    static MongoClient mongoClient;
    static MongoDatabase database;

    public static void connect() {
        try {
            System.out.println("Successful. \n");
            mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net"));
            database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
            System.out.println("Successful database connection established. \n");
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }
    public static MongoClient getConnection() {
        return mongoClient;
    }
    public static MongoDatabase getDatabase() { return database; }

}
