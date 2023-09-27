module com.example.thread {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.thread to javafx.fxml;
    exports com.example.thread;
    opens view to javafx.fxml;
    exports view;
    opens controller to javafx.fxml;
    exports controller;
}