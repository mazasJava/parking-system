package org.tasks;


import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import org.controllers.DbConnection;
import org.controllers.ForgotPassword;
import org.models.User;

public class ForgotPasswordTask extends Task<Void> {
    private final String emailSend;

    public void alert(String title, String message, String header){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.setHeaderText(header);
            alert.showAndWait();
        });
    }

    public ForgotPasswordTask(String emailSend) {
        this.emailSend = emailSend;
    }

    @Override
    protected Void call() throws Exception {
        try {
            DbConnection.connect();
            MongoDatabase database = DbConnection.database;
            User user = database.getCollection("users", User.class).find(Filters.eq("email",emailSend)).first();
            if(user != null){
                ForgotPassword.getPassword = user.getPassword();
            }else{
                alert("FORGOT PASSWORD", "THIS EMAIL DOES NOT EXISTS", "ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
