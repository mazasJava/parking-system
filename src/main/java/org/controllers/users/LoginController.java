package org.controllers.users;

import com.mongodb.*;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controllers.DbConnection;
import org.mainapp.App;

import java.io.IOException;

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
    public void login() throws IOException {
        mongoClient = DbConnection.getConnection();
        @Deprecated
        DB db = mongoClient.getDB("PARKING_MANAGEMENT_SYSTEM");
        DBCollection collection = db.getCollection("users");
        checkAuth(collection);
    }

    public void checkAuth(DBCollection collection) throws IOException {
        BasicDBObject whereQuery1 = new BasicDBObject();
        BasicDBObject whereQuery2 = new BasicDBObject();

        whereQuery1.put("name", usernameTextField.getText());
        whereQuery2.put("password", passwordTextField.getText());

        DBCursor cursor = collection.find(whereQuery1, whereQuery2);

        if (cursor.hasNext()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            switchToCar();
        } else {
            System.out.println("fail");
        }

    }
}
