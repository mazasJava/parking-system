package org.controllers.users;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.controllers.DbConnection;
import org.mainapp.App;
import org.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    public Button loginButton;
    @FXML
    TextField fullNameTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    PasswordField confirmPasswordTextField;
    @FXML
    ProgressIndicator logProRegister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logProRegister.setVisible(false);
        fullNameTextField.requestFocus();
    }
    @FXML
    public void creatUser(ActionEvent event) {
        getregisterTask task = new getregisterTask();
        logProRegister.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

    }
    @FXML
    public void ihaveaacount(MouseEvent mouseEvent) throws IOException {
        App.setRoot("login");
    }
    public void alert(String title,String message,String header){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.setHeaderText(header);
            alert.showAndWait();
        });
    }
    class getregisterTask extends Task<Integer> {
        @Override
        protected Integer call() throws Exception {
            logProRegister.setVisible(true);
            Thread thread = new Thread();
            String password = passwordTextField.getText();
            String confirmation = confirmPasswordTextField.getText();
            updateProgress(2, 20);
            try {
                if (!fullNameTextField.getText().equals("") && !emailTextField.getText().equals("") && !passwordTextField.getText().equals("") && !confirmPasswordTextField.getText().equals("")) {
                    if(!password.equals(confirmation)){
                        alert("WARNING","PASSWORD AND CONFIRM PASSWORD DOES NOT MATCH!","REGISTER ERROR");
                    }else{
                        logProRegister.setVisible(true);
                        DbConnection.connect();
                        updateProgress(4, 20);
                        MongoDatabase database = DbConnection.database;
                        MongoCollection<BasicDBObject> col = database.getCollection("users", BasicDBObject.class);
                        List<BasicDBObject> fd = col.find().into(new ArrayList<BasicDBObject>());
                        updateProgress(7, 20);
                        boolean tr = false;
                        for (BasicDBObject bo : fd) {
                            if (emailTextField.getText().equals(bo.get("email"))) {
                                tr = true;
                            }
                        }
                        updateProgress(14, 20);
                        if (tr == true) {
                            alert("WARNING","EMAIL ALREADY EXIST!","REGISTER ERROR");
                            updateProgress(20, 20);
                        } else {
                            MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);
                            User newUser = new User();
                            updateProgress(16, 20);
                            newUser.setFullName(fullNameTextField.getText());
                            newUser.setEmail(emailTextField.getText());
                            newUser.setPassword(passwordTextField.getText());
                            updateProgress(18, 20);
                            try {
                                userMongoCollection.insertOne(newUser);
                                alert("INFORMATION","ACCOUNT CREATED SUCCESFULLY!","REGISTER SUCCESS!");
                                updateProgress(20, 20);
                                App.setRoot("Login");
                            } catch (Exception e) {
                                updateProgress(20, 20);
                                logProRegister.setVisible(false);
                                System.out.println("EXCEPTION 1: " + e.getMessage());
                            }
                        }
                    }
                } else {
                    alert("WARNING","REQUIRED FIELDS ARE EMPTY!","REGISTER ERROR");
                }
            } catch (Exception e) {
                System.out.println("EXCEPTION 2:" + e.getMessage());
            } finally {
                updateProgress(20, 20);
                thread.sleep(2000);
                logProRegister.setVisible(false);
            }
            return 1;
        }
    }
}