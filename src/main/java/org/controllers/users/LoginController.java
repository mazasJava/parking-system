package org.controllers.users;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.mainapp.App;
import org.models.User;

import java.io.IOException;
import java.util.Arrays;

public class LoginController {


    @FXML
    public void switchToCar() throws IOException {
        App.setRoot("car");
    }


    @FXML
    public void checkAuth(ActionEvent event)
    {
        User user = new User("admin","admin");
        System.out.println("user created");
        MongoCredential credential = MongoCredential.createCredential(user.getName(), "users", user.getPassword().toCharArray());
        System.out.println(credential);

        try {
            @Deprecated
            MongoClient mongoClient = new MongoClient(new ServerAddress("mongodb+srv://mehdi-java:Password1234@cluster0.dw27l.mongodb.net/PARKING_MANAGEMENT_SYSTEM?retryWrites=true&w=majority"),Arrays.asList(credential));

        }
        catch (Exception e)
        {
            System.out.println("err");
        }
//        MongoClient mongoClients = new MongoClient();

        System.out.println("con db 2 ");
    }

}
