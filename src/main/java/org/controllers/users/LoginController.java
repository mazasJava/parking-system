package org.controllers.users;
import com.mongodb.client.model.Filters;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.controllers.DbConnection;
import org.mainapp.App;
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
    CheckBox chkRememberMe;
    public static String rememberEmail = "", rememberPassword = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!rememberEmail.equals("") && !rememberPassword.equals("")){
            chkRememberMe.setSelected(true);
            emailTextField.setText(rememberEmail);
            passwordTextField.setText(rememberPassword);
        }
        else {
            chkRememberMe.setSelected(false);
        }
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

    class LoginTask extends Task<Void>{

        @Override
        protected Void call() throws Exception {
            try {
                logProg.setVisible(true);
                updateProgress(2, 10);
                if (!emailTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
                    if (!emailTextField.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                        alert("WARNING", "EMAIL NON VALID!", "LOGIN ERROR");
                    } else {
                        updateProgress(4, 10);
                        User user = DbConnection.database.getCollection("users", User.class).find(Filters.and(Filters.eq("email", emailTextField.getText()), Filters.eq("password", passwordTextField.getText()))).first();
                        updateProgress(6, 10);
                        if (user != null) {
                            updateProgress(8, 10);
                            App.setRoot("dashboard");
                        } else {
                            alert("WARNING", "EMAIL OR PASSWORD IS INCORRECT!", "LOGIN ERROR");
                        }
                    }
                } else {
                    alert("WARNING", "EMAIL OR PASSWORD IS EMPTY", "LOGIN ERROR");
                }
            } catch (Exception e) {
                System.out.println("Exception Login :" + e.getMessage());
            } finally {
                updateProgress(10, 10);
                logProg.setVisible(false);
            }
            return null;
        }

    }
    @FXML
    public void login(){

        if(chkRememberMe.isSelected()){
            rememberEmail = emailTextField.getText();
            rememberPassword = passwordTextField.getText();
            System.out.println("is checked" + chkRememberMe.isSelected());
        }else {
            rememberEmail = "";
            rememberPassword = "";
        }
        try{
            LoginTask loginTask = new LoginTask();
            logProg.progressProperty().bind(loginTask.progressProperty());
            new Thread(loginTask).start();
        }catch(Exception e){
            System.out.println("Login Exception : "+e.getMessage());
        }


    }
    // Add focus Enter .
    public void clickEnterUserName() {
        passwordTextField.requestFocus();
    }


    public void clickForgotPassWord(MouseEvent mouseEvent) throws IOException {
        App.setRoot("forgotPassword");
    }
}