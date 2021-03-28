package org.controllers.users;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.controllers.DbConnection;
import org.mainapp.App;
import org.models.User;

import java.io.IOException;
import java.util.*;

public class LoginController {
    public MongoClient mongoClient;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;


    @FXML
    public void switchToCar() throws IOException {
        App.setRoot("car");
    }

    @FXML
    public void checkAuth(ActionEvent event) throws IOException {
        mongoClient = DbConnection.getConnection();
        DB db = mongoClient.getDB("PARKING_MANAGEMENT_SYSTEM");
        DBCollection collection = db.getCollection("users");
        selectAllRecordByRecordNumber(collection);
    }

    public void selectAllRecordByRecordNumber(DBCollection collection) throws IOException {
        BasicDBObject whereQuery1 = new BasicDBObject();
        BasicDBObject whereQuery2 = new BasicDBObject();

        whereQuery1.put("name", usernameTextField.getText());
        whereQuery2.put("password", passwordTextField.getText());

        DBCursor cursor = collection.find(whereQuery1,whereQuery2);
        if(cursor.hasNext())
        {
            while(cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            switchToCar();
        }
        else {
            System.out.println("fail");
        }

    }

}
