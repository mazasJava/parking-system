module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens org.views to javafx.fxml;
    exports org.views;
    exports org.controllers;
    exports org.controllers.userControllers;
}