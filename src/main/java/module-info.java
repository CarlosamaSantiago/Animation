module icesi.com.animation {
    requires javafx.controls;
    requires javafx.fxml;


    opens icesi.com.animation to javafx.fxml;
    exports icesi.com.animation;
}