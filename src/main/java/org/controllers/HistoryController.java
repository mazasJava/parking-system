package org.controllers;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.models.History;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryController {

    public static void setCarHistorique(ObjectId carId) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String formattedDate= formatter.format(date);


        MongoCollection<History> historyMongoCollection = DbConnection.database.getCollection("historys", History.class);

        History newHistory = new History().setCarId(carId).setDateEntered(formattedDate).setDateRelease(null);

        try {
            historyMongoCollection.insertOne(newHistory);
            System.out.println("Successfully inserted History documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("History Document fails");
            }
        }
    }

}
