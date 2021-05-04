module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens org.mainapp to javafx.fxml;
    opens org.controllers to javafx.fxml;
    opens org.controllers.users to javafx.fxml;
    exports org.mainapp;
    exports org.controllers;
    exports org.controllers.users;
    exports org.models;

    // Mail
    requires slf4j.api;
    requires mail;

}