package org.controllers.users;

import com.mongodb.*;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
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
        logProg.setVisible(false);

    }
    public void alert(String title,String message,String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    public void createCount(MouseEvent mouseEvent) throws IOException {
        //App.setRoot("register");
    }
    class getUserTask extends Task<Integer> {
        @Override
        protected Integer call() throws Exception {
            Thread thread = new Thread();
            updateProgress(4,10);
            try {
                if (!usernameTextField.getText().equals("") && !passwordTextField.getText().equals("")){
                    logProg.setVisible(true);
                    updateProgress(2,10);
                    thread.sleep(250);
                    DbConnection.connect();
                    updateProgress(4,10);
                    MongoDatabase database = DbConnection.database;
                    updateProgress(5,10);
                    MongoCollection<BasicDBObject> col = database.getCollection("users",BasicDBObject.class);
                    updateProgress(6,10);
                    List<BasicDBObject> fd = col.find().into(new ArrayList<BasicDBObject>());
                    updateProgress(7,10);
                    boolean tr = false;

                    for (BasicDBObject bo : fd){
                        if(usernameTextField.getText().equals(bo.get("name")) && passwordTextField.getText().equals(bo.get("password"))){
                            updateProgress(8,10);
                            thread.sleep(250);
                            updateProgress(10,10);
                            try{
                                switchToCar();
                            }catch(Exception e ){
                                System.out.println("eeexception"+e.getMessage());
                            }
                            return 1;
                        }
                        updateProgress(9,10);
                    }
                    if(tr == false){
                        updateProgress(8,10);
                        thread.sleep(250);
                        updateProgress(10,10);
                        logProg.setVisible(false);
                        Platform.runLater(() -> {
                            alert("WARNING","EMAIL OR PASSWORD IS INCORRECT!","LOGIN ERROR");
                        });
                    }
                }else{
                    updateProgress(10,10);
                    logProg.setVisible(false);
                    Platform.runLater(() -> {
                        alert("WARNING","EMAIL OR PASSWORD IS EMPTY","LOGIN ERROR");
                    });
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

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            updateMessage("Cancelled!!");
            return super.cancel(mayInterruptIfRunning);
        }
        @Override
        protected void updateProgress(double workDone, double max) {
            updateMessage("progresse! "+ workDone);
            super.updateProgress(workDone, max);
        }
    }
    @FXML
    public void login() throws IOException {

        getUserTask task = new getUserTask();
        logProg.progressProperty().bind(task.progressProperty());
        new Thread(task).start();    }

    // Add focus Enter .
    public void clickEnterUserName(ActionEvent actionEvent) {
        passwordTextField.requestFocus();
    }

}