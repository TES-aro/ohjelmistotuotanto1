module com.example.varausjarjestelma300_frontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.varausjarjestelma300_frontend to javafx.fxml;
    exports com.example.varausjarjestelma300_frontend;
}