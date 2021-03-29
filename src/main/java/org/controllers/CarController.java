package org.controllers;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;

import java.io.IOException;

public class CarController {

    public MongoClient mongoClient;
    public MongoDatabase database;
    public static final ObjectId id = new ObjectId();

    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }
    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }

    @FXML
    public void createCar(){
        mongoClient = DbConnection.getConnection();
        database = DbConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id,"test101");

        Document car = new Document();
        car.append("_id",id)
                .append("matricule", carObject.getmatricule());
        try {
            collection.insertOne(car);
            System.out.println("Successfully inserted documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
    }

}
