package org.controllers;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.controllers.users.HistoriqueController;
import org.mainapp.App;
import org.models.Car;
import org.models.Historique;

import java.io.IOException;

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
    public void createCar(){
//        mongoClient = DbConnection.getConnection();
//        database = DbConnection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("cars");

        Car carObject = new Car(id,"test103");
        Document car = new Document();

        car.append("_id",carObject.getId())
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

//    public void setCarHistorique(ObjectId carId)
//    {
//        MongoCollection<Document> collection = database.getCollection("historiques");
//        Historique historiqueObject = new Historique(carId,"stat","dateEntered","dateRelease");
//        Document historique = new Document();
//
//        historique.append("car_id",historiqueObject.getCarId())
//                .append("state",historiqueObject.getState())
//                .append("dateEntered",historiqueObject.getDateEntered())
//                .append("dateRelease",historiqueObject.getDateRelease());
//
//        try {
//            collection.insertOne(historique);
//            System.out.println("Successfully inserted documents. \n");
//        } catch (MongoWriteException mwe) {
//            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
//                System.out.println("Document with that id already exists");
//            }
//        }
//    }

}
