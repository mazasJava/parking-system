package org.controllers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DbConnection {
    static MongoClient Connection;

    public static boolean connect() {
        try {
            Connection = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net/"));
            System.out.println("Successful database connection established. \n");
            return true;
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
            return false;
        }
    }
    public static MongoClient getConnection() {
        return Connection;
    }

}
