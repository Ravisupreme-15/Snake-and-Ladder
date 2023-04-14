module com.example.demosnakeladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demosnakeladder to javafx.fxml;
    exports com.example.demosnakeladder;
}