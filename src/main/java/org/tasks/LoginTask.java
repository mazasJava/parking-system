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

import java.util.ArrayList;
import java.util.List;

public class LoginTask extends Task<Integer> {
    private final String emailTextField;
    private final String passwordTextField;
    private  ProgressIndicator logProg ;

    public LoginTask(String emailTextField,String passwordTextField,ProgressIndicator logProg) {
        this.emailTextField = emailTextField;
        this.passwordTextField = passwordTextField;
        this.logProg = logProg;
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
        updateProgress(4,10);
        try {
            if (!emailTextField.equals("") && !passwordTextField.equals("")){
                if(!emailTextField.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                    alert("WARNING","EMAIL NON VALID!","LOGIN ERROR");
                }
                else{
                    logProg.setVisible(true);
                    updateProgress(2,10);
                    Thread.sleep(250);
                    DbConnection.connect();
                    updateProgress(4,10);
                    MongoDatabase database = DbConnection.database;
                    updateProgress(5,10);
                    MongoCollection<BasicDBObject> col = database.getCollection("users",BasicDBObject.class);
                    updateProgress(6,10);
                    List<BasicDBObject> fd = col.find().into(new ArrayList<BasicDBObject>());
                    updateProgress(7,10);
                    for (BasicDBObject bo : fd){
                        if(emailTextField.equals(bo.get("email")) && passwordTextField.equals(bo.get("password"))){
                            updateProgress(8,10);
                            Thread.sleep(250);
                            updateProgress(10,10);
                            try{
                                App.setRoot("car");
                            }catch(Exception e ){
                                System.out.println("eeexception"+e.getMessage());
                            }
                            return 1;
                        }
                        updateProgress(9,10);
                    }
                    updateProgress(8,10);
                    Thread.sleep(250);
                    updateProgress(10,10);
                    logProg.setVisible(false);
                    alert("WARNING","EMAIL OR PASSWORD IS INCORRECT!","LOGIN ERROR");
                }
            }else{
                updateProgress(10,10);
                logProg.setVisible(false);
                alert("WARNING","EMAIL OR PASSWORD IS EMPTY","LOGIN ERROR");

            }

        }catch (Exception e) {
            System.out.println("exception"+e.getMessage());
        }
        finally {
            updateProgress(10,10);
            logProg.setVisible(false);
            System.out.println("finally");
        }
        return 1;
    }
}