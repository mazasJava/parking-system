//package org.controllers.users;
//
//import com.mongodb.ErrorCategory;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoWriteException;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import org.bson.Document;
//import org.controllers.DbConnection;
//
//public class RegisterController {
//    public MongoClient mongoClient;
//    @FXML
//    public Button loginButton;
//    @FXML
//    public TextField usernameTextField;
//    @FXML
//    public TextField passwordTextField;
//    @FXML
//    public TextField firstNameTextField;
//    @FXML
//    public TextField lastNameTextField;
//    @FXML
//    public TextField emailTextField;
//    @FXML
//    public PasswordField confirmPasswordTextField;
//
//    @FXML
//    public void creatUser(ActionEvent event) {
//        mongoClient = DbConnection.getConnection();
//        MongoDatabase database = mongoClient.getDatabase("PARKING_MANAGEMENT_SYSTEM");
//        MongoCollection<Document> collection = database.getCollection("users");
//        Document user = new Document();
//        user.append("firstName", firstNameTextField.getText().toString())
//                .append("lastName", lastNameTextField.getText().toString())
//                .append("email", emailTextField.getText().toString())
//                .append("password", passwordTextField.getText().toString())
//                .append("name", confirmPasswordTextField.getText().toString());
//        try {
//            collection.insertOne(user);
//            System.out.println("Successfully inserted documents. \n");
//        } catch (MongoWriteException mwe) {
//            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
//                System.out.println("Document with that id already exists");
//            }
//        }
//    }
//
//    @FXML
//    public int checkLoginInfo(ActionEvent event) {
//        return 0;
//    }
//
//}
