module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens org.bootstrap to javafx.fxml;
    opens org.controllers to javafx.fxml;
    opens org.controllers.users to javafx.fxml;
    exports org.bootstrap;
    exports org.controllers;
    exports org.controllers.users;
    exports org.models;

}