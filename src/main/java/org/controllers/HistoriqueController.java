package org.controllers;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.controllers.DbConnection;
import org.models.Historique;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class HistoriqueController {

    static MongoClient mongoClient = DbConnection.getConnection();
    static MongoDatabase database = DbConnection.getDatabase();


    public static void setCarHistorique(ObjectId carId) {
        mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net"));
        database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        MongoCollection<Document> collection = database.getCollection("historiques");
        Historique historiqueObject = new Historique(carId, "stat", date, null);
        Document historique = new Document();

        historique.append("car_id", historiqueObject.getCarId())
                .append("state", historiqueObject.getState())
                .append("dateEntered", historiqueObject.getDateEntered())
                .append("dateRelease", historiqueObject.getDateRelease());

        try {
            collection.insertOne(historique);
            System.out.println("Successfully inserted documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
    }

}
