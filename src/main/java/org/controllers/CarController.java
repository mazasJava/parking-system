package org.controllers;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mainapp.App;
import org.models.Car;
import org.models.Historique;

import java.io.IOException;
import java.util.*;

public class CarController {
    static MongoClient mongoClient;
    static MongoDatabase database;
    public static final ObjectId id = new ObjectId();

    @FXML
    public void switchToParkingState() throws IOException {
        App.setRoot("parkingState");
    }

    @FXML
    public void switchToParkingCar() throws IOException {
        App.setRoot("car");
    }

    /**
     * @throws IOException Creat car object and insert object in data base
     */
    @FXML
    public static void createCar() throws IOException {
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id, "LO0RVU");
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

    /**
     * @return Return car list with information about the cars
     */
    @FXML
    public static List<Car> getCarsWithHistorique() {
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
        List<Car> carList = new ArrayList<>();
        List<Historique> historiqueList = new ArrayList<>();

        for (Document document : tr) {
            Historique carHistoricList = new Historique(id, document.getString("state"), document.getDate("dateEntered"), document.getDate("dateRelease"));
            Car carList2 = new Car(id, document.getString("carhistory"), carHistoricList);
            historiqueList.add(carHistoricList);
            carList.add(carList2);
        }
        return carList;
    }

    /**
     * Find and update car release date with current date
     */
    public static void releaseCar() {
        MongoCollection<Document> collection = database.getCollection("historiques");
        Date date = new Date(System.currentTimeMillis());
        collection.updateOne(Filters.eq("_id", new ObjectId("606610df1bb9d7007dce7abe")), Updates.set("dateRelease", date));
        System.out.println("Document update successfully...");
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void main(String[] args) throws IOException {
        DbConnection.connect();
        mongoClient = DbConnection.getConnection();
        database = DbConnection.getDatabase();
        createCar();
        System.out.println(getCarsWithHistorique().toString());
    }
}
