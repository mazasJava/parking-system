package org.controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;
import org.models.Historique;

import java.io.IOException;
import java.util.*;

import static java.util.List.*;


public class CarController {
    static MongoClient mongoClient;
    static MongoDatabase database;
    //    public MongoDatabase database = DbConnection.getDatabase();
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
    public static void createCar() throws IOException {
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net"));
        database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id, "test10HHHH3");
        Document car = new Document();

        car.append("_id", carObject.getId())
                .append("matricule", carObject.getmatricule());
        try {
            collection.insertOne(car);
            HistoriqueController.setCarHistorique(carObject.getId());
            System.out.println("Successfully inserted documents. \n");
            getCarsWithHistorique();
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
    }

    @FXML
    public static void getCarsWithHistorique() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net"));
        database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
        System.out.println("get car list :");
        MongoCollection<Document> collection = database.getCollection("historiques");
        AggregateIterable<Document> tr = collection.aggregate(List.of(Aggregates.lookup("cars", "car_id", "_id", "carhistory"), Aggregates.project(Projections.fields(
                Projections.excludeId(),
                Projections.include("state"),
                Projections.include("dateEntered"),
                Projections.include("dateRelease"),
                Projections.computed(
                        "carhistory",
                        new Document("$arrayElemAt", Arrays.asList("$carhistory.matricule", 0))
                )
        ))));
        Historique carHistoricList;
        Car carList;
        for (Document document : tr) {
            carHistoricList = new Historique(id,
                    document.getString("state"),
                    document.getDate("dateEntered"),
                    document.getDate("dateRelease"));
            carList = new Car(id,
                    document.getString("carhistory"),
                    carHistoricList);
            System.out.println(carList.toString());
        }
    }
    public static void main(String[] args) throws IOException {
        createCar();
//        getCarsWithHistorique();
    }
}
