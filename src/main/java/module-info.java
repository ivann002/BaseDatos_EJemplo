module com.example.basedatos_ejemplo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.basedatos_ejemplo to javafx.fxml;
    exports com.example.basedatos_ejemplo;
}