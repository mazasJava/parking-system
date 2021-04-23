package org.controllers.users;

import com.mongodb.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.controllers.DbConnection;
import org.mainapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public MongoClient mongoClient;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Label labelError;

    @FXML
    ProgressIndicator logProg;

    @FXML
    public void switchToCar() throws IOException {
        App.setRoot("car");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(888888);
    }

    class getUserTask extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {

            updateProgress(0, 10);
            Thread.sleep(500);
            try {
                if (!usernameTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
                    logProg.setVisible(true);
                    System.out.println("user: " + usernameTextField.getText() + ", mdp: " + passwordTextField.getText());
                    updateProgress(5, 10);
                    Thread.sleep(500);
                    //put the script to fetch database here

                    if (usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")) {
                        updateProgress(10, 10);
                        Thread.sleep(500);
                        switchToCar();
                    } else {
                        updateProgress(10, 10);
                        Thread.sleep(500);
                        System.out.println("Aucun utisateur corresponde avec ce login!!!");
                        logProg.setVisible(false);
                    }
                } else {
                    System.out.println("Inputs vides!!!");
                    logProg.setVisible(false);
                }
            } catch (Exception e) {
            }
            return 1;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            updateMessage("Cancelled!!");
            return super.cancel(mayInterruptIfRunning);
        }

        @Override
        protected void updateProgress(double workDone, double max) {
            updateMessage("progresse! " + workDone);
            super.updateProgress(workDone, max);
        }
    }


    @FXML
    public void login() throws IOException {

        getUserTask task = new getUserTask();
        logProg.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

        //DbConnection.connect();
        //@Deprecated
        //DB db = mongoClient.getDB("PARKING_MANAGEMENT_SYSTEM");
        //DBCollection collection = db.getCollection("users");
        //checkAuth(collection);
    }


    public void checkAuth(DBCollection collection) throws IOException {
        BasicDBObject whereQuery1 = new BasicDBObject();
        BasicDBObject whereQuery2 = new BasicDBObject();
        whereQuery1.put("name", usernameTextField.getText());
        whereQuery2.put("password", passwordTextField.getText());
        DBCursor cursor = collection.find(whereQuery1, whereQuery2);
        if (cursor.hasNext()) {
            System.out.println(cursor.next());
            switchToCar();
        } else {
            labelError.setText("username or password is incorrect");
        }

    }

    // Add focus Enter . 
    public void clickEnterUserName(ActionEvent actionEvent) {
        passwordTextField.requestFocus();
    }
}
