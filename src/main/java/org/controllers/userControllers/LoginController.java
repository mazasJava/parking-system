package org.controllers.userControllers;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.controllers.DbConnection;

import java.util.Arrays;

import static com.mongodb.client.model.Aggregates.match;

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    public MongoClient mongoClient;
    String username, password;

    @FXML
    public String getUserInfo() {
        return usernameTextField.getText().toString() + "/" + passwordTextField.getText().toString();
    }

    @FXML
    public void showUser(ActionEvent event) {
        mongoClient = DbConnection.getConnection();
        MongoDatabase database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
        MongoCollection<Document> collection = database.getCollection("users");
        String user = getUserInfo();
        getCOllection(collection);
        Document user1 = new Document();
        user1
                .append("name", user.split("/")[0])
                .append("email", user.split("/")[1])
                .append("phone", "0635808481");

        try {
            collection.insertOne(user1);
            System.out.println("Successfully inserted documents. \n");
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Document with that id already exists");
            }
        }
//        BasicDBObject query;
//        query = new BasicDBObject("name", new BasicDBObject("$regex", "ADMIN"));
//        System.out.println(query.toJson());
    }

    @FXML
    public boolean checkLoginInfo(ActionEvent event) {
        return false;
    }

    public void getCOllection(MongoCollection collection) {
        System.out.println("Print the documents.");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }

        } finally {
            cursor.close();
        }
    }
}
