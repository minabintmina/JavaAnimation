module com.conduite {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.conduite to javafx.fxml;
    exports com.conduite;
}