module com.animation {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.animation to javafx.fxml;
    exports com.animation;
}