module com.example.brakeout {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.brakeout to javafx.fxml;
    exports com.example.brakeout;
}