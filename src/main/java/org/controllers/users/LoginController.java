package org.controllers.users;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.controllers.DbConnection;

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    MongoClient mongoClient;

    @FXML
    public String getUserInfo() {
        return usernameTextField.getText().toString() + "/" + passwordTextField.getText().toString();
    }

    @FXML
    public void showUser(ActionEvent event) {
        mongoClient = DbConnection.getConnection();
        final MongoDatabase database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
        MongoCollection<Document> collection = database.getCollection("users");

        String user = getUserInfo();
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
    }

    @FXML
    public int checkLoginInfo(ActionEvent event) {
        return 0;
    }

}
