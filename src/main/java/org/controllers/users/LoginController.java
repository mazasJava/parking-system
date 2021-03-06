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
    Label labelError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelError.setVisible(false);
    }

    /**
     * check user auth  and login to the main app
     * @throws IOException
     */
    @FXML
    public void login() throws IOException {
        MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);

        if(emailTextField.getText().equals("") || passwordTextField.getText().equals("")  )
        {
            labelError.setVisible(true);
        }
        else{
            preloader.setVisible(true);
            if (userMongoCollection.find(Filters.eq("email",emailTextField.getText())).iterator().hasNext() && userMongoCollection.find(Filters.eq("password",passwordTextField.getText())).iterator().hasNext()) {
                preloader.setVisible(false);
                App.setRoot("car");
                labelError.setVisible(false);
            }else{
                labelError.setVisible(true);
                preloader.setVisible(false);
            }

        }

    }

}