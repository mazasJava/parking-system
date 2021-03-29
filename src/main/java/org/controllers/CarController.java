package org.controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.controllers.users.HistoriqueController;
import org.mainapp.App;
import org.models.Car;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import com.mongodb.MongoClient;


public class CarController {

    public MongoClient mongoClient = DbConnection.getConnection();
    public MongoDatabase database = DbConnection.getDatabase();
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
    public void createCar() throws IOException{
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id, "test103");
        Document car = new Document();

        car.append("_id", carObject.getId())
                .append("matricule", carObject.getmatricule());
        try {
            collection.insertOne(car);
            HistoriqueController.setCarHistorique(carObject.getId());
            System.out.println("Successfully inserted documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
    }
    @FXML
    public void getCarsWithHistorique(){
        System.out.println("ewewew");
        MongoCollection<Document> collection = database.getCollection("historiques");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$lookup",
                new Document("from", "cars")
                        .append("localField", "car_id")
                        .append("foreignField", "_id")
                        .append("as", "carhistory"))));

        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
