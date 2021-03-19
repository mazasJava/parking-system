module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens org.mainapp to javafx.fxml;
    exports org.mainapp;
    exports org.controllers;
    exports org.controllers.users;
}