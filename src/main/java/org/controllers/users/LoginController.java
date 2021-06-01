package org.controllers.users;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.bson.Document;
import org.controllers.DbConnection;
import org.bootstrap.App;
import org.models.History;
import org.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    ProgressIndicator logProg;

    @FXML
    ImageView preloader;

    @FXML
    CheckBox chkRememberMe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);

//        MongoCollection<Document> historyMongoCollection = DbConnection.database.getCollection("users");
//        if (historyMongoCollection.find(Filters.eq("email","zakria@gmail.com")).iterator().hasNext() && historyMongoCollection.find(Filters.eq("password","123456")).iterator().hasNext()) {
//            System.out.println("yes");
//        }
//        else
//        {
//            System.out.println("no");
//        }

    }

    @FXML
    public void login() throws IOException {
        MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);

        if(emailTextField.getText().equals("") || passwordTextField.getText().equals("")  )
        {
            System.out.println("weewew");
        }
        else{
            preloader.setVisible(true);
            if (userMongoCollection.find(Filters.eq("email",emailTextField.getText())).iterator().hasNext() && userMongoCollection.find(Filters.eq("password",passwordTextField.getText())).iterator().hasNext()) {
                App.setRoot("car");
            }        }


    }

}