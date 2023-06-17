module com.suivie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.suivie to javafx.fxml;
    exports com.suivie;
}