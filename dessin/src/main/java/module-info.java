module com.dessin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dessin to javafx.fxml;
    exports com.dessin;
}