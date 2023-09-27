module View {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens view to javafx.fxml;
    exports view;

    opens be.g56172.stibride to javafx.fxml;
    exports be.g56172.stibride;

}