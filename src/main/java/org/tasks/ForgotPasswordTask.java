package org.tasks;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.controllers.DbConnection;
import org.controllers.ForgotPassword;
import org.models.User;

import java.util.ArrayList;
import java.util.List;

public class ForgotPasswordTask extends Task<Void> {
    private final String emailSend;

    public ForgotPasswordTask(String emailSend) {
        this.emailSend = emailSend;
    }

    public void alert(String title, String message, String header){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.setHeaderText(header);
            alert.showAndWait();
        });
    }

    @Override
    protected Void call() throws Exception {
        try {
            DbConnection.connect();
            MongoDatabase database = DbConnection.database;
            User user = database.getCollection("users",User.class).find(Filters.eq("email",emailSend)).first();
            if(user != null){
                ForgotPassword.getPassword = user.getPassword();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
