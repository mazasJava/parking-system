module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.views to javafx.fxml;
    exports org.views;
    exports org.controllers;
}