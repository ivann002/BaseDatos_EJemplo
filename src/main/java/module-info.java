module com.example.basedatos_ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.basedatos_ejemplo to javafx.fxml;
    exports com.example.basedatos_ejemplo;
}