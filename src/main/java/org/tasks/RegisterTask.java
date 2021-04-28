package org.tasks;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import org.controllers.DbConnection;
import org.mainapp.App;
import org.models.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterTask extends Task<Integer> {
    private final String fullNameField;
    private final String emailTextField;
    private final String passwordTextField;
    private final String confirmPasswordTextField;
    private final ProgressIndicator logProRegister ;

    public RegisterTask(String fullNameField,String emailTextField,String passwordTextField,String confirmPasswordTextField, ProgressIndicator logProRegister) {
        this.fullNameField = fullNameField;
        this.emailTextField = emailTextField;
        this.passwordTextField = passwordTextField;
        this.confirmPasswordTextField = confirmPasswordTextField;
        this.logProRegister = logProRegister;
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

    @Override
    protected Integer call() throws Exception {
        logProRegister.setVisible(true);
        updateProgress(2, 20);
        try {
            if (!fullNameField.equals("") && !emailTextField.equals("") && !passwordTextField.equals("") && !confirmPasswordTextField.equals("")) {
                if(!emailTextField.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")){
                    alert("WARNING","EMAIL NON VALID!","LOGIN ERROR");
                    return null;
                }
                if(passwordTextField.length() < 6){
                    alert("WARNING","Password must have more than 6 characters!","LOGIN ERROR");
                    return null;
                }
                if(!passwordTextField.equals(confirmPasswordTextField)){
                    alert("WARNING","PASSWORD AND CONFIRM PASSWORD DOES NOT MATCH!","REGISTER ERROR");
                }else{
                    logProRegister.setVisible(true);
                    DbConnection.connect();
                    updateProgress(4, 20);
                    MongoDatabase database = DbConnection.database;
                    MongoCollection<BasicDBObject> col = database.getCollection("users", BasicDBObject.class);
                    List<BasicDBObject> fd = col.find().into(new ArrayList<>());
                    updateProgress(7, 20);
                    boolean finded = false;
                    for (BasicDBObject bo : fd) {
                        if (emailTextField.equals(bo.get("email"))) {
                            finded = true;
                        }
                    }
                    updateProgress(14, 20);
                    if (finded == true) {
                        alert("WARNING","EMAIL ALREADY EXIST!","REGISTER ERROR");
                        updateProgress(20, 20);
                    } else {
                        MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);
                        User newUser = new User();
                        updateProgress(16, 20);
                        newUser.setFullName(fullNameField);
                        newUser.setEmail(emailTextField);
                        newUser.setPassword(passwordTextField);
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
            Thread.sleep(2000);
            logProRegister.setVisible(false);
        }
        return 1;
    }
}
