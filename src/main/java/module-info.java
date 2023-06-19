module com.example.basedatos_ejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;



    opens com.example.basedatos_ejemplo to javafx.fxml;
    exports com.example.basedatos_ejemplo;
}