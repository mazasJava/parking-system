//package org.controllers.users;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.model.Filters;
//import javafx.application.Platform;
//import javafx.concurrent.Task;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.*;
//import org.controllers.DbConnection;
//import org.bootstrap.App;
//import org.models.User;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class RegisterController implements Initializable {
//
//    @FXML
//    public Button loginButton;
//    @FXML
//    TextField fullNameTextField;
//    @FXML
//    TextField emailTextField;
//    @FXML
//    TextField passwordTextField;
//    @FXML
//    PasswordField confirmPasswordTextField;
//    @FXML
//    ProgressIndicator logProRegister;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        logProRegister.setVisible(false);
//        fullNameTextField.requestFocus();
//    }
//
//    public void alert(String title,String message,String header){
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle(title);
//            alert.setContentText(message);
//            alert.setHeaderText(header);
//            alert.showAndWait();
//        });
//    }
//    class RegisterTask extends Task<Void>{
//
//        @Override
//        protected Void call() throws Exception {
//            logProRegister.setVisible(true);
//            try {
//                if (!fullNameTextField.getText().equals("") && !emailTextField.getText().equals("") && !passwordTextField.getText().equals("") && !confirmPasswordTextField.getText().equals("")) {
//                    if(!emailTextField.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")){
//                        alert("WARNING","EMAIL NON VALID!","LOGIN ERROR");
//                        updateProgress(4,10);
//                        return null;
//                    }
//                    else if(passwordTextField.getText().length() < 6){
//                        alert("WARNING","Password must have more than 6 characters!","LOGIN ERROR");
//                        updateProgress(4,10);
//                        return null;
//                    }
//                    else if(!passwordTextField.getText().equals(confirmPasswordTextField.getText())){
//                        alert("WARNING","PASSWORD AND CONFIRM PASSWORD DOES NOT MATCH!","REGISTER ERROR");
//                        updateProgress(4,10);
//                        return null;
//                    }else{
//                        logProRegister.setVisible(true);
//                        MongoCollection<User> userMongoCollection = DbConnection.database.getCollection("users", User.class);
//                        updateProgress(6,10);
//                        User newUser = userMongoCollection.find(Filters.eq("email",emailTextField.getText())).first();
//                        if(newUser != null){
//                            alert("WARNING","EMAIL ALREADY EXIST!","REGISTER ERROR");
//                        }
//                        else{
//                            newUser = new User();
//                            newUser.setFullName(fullNameTextField.getText());
//                            newUser.setEmail(emailTextField.getText());
//                            newUser.setPassword(passwordTextField.getText());
//                            userMongoCollection.insertOne(newUser);
//                            updateProgress(8,10);
//                            alert("INFORMATION","ACCOUNT CREATED SUCCESFULLY!","REGISTER SUCCESS!");
//                            App.setRoot("Login");
//                        }
//                    }
//                } else {
//                    alert("WARNING","REQUIRED FIELDS ARE EMPTY!","REGISTER ERROR");
//                }
//            } catch (Exception e) {
//                System.out.println("EXCEPTION REGISTER:" + e.getMessage());
//            } finally {
//                updateProgress(10,10);
//                logProRegister.setVisible(false);
//            }
//            return null;
//        }
//    }
//    @FXML
//    public void creatUser() {
//        try{
//            RegisterTask registerTask = new RegisterTask();
//            logProRegister.progressProperty().bind(registerTask.progressProperty());
//            new Thread(registerTask).start();
//        }catch(Exception e){
//            System.out.println("Login Exception : "+e.getMessage());
//        }
//    }
//    @FXML
//    public void ihaveaacount() throws IOException {
//        App.setRoot("login");
//    }
//
//}