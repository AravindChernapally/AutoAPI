module com.example.demo_test {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo_test to javafx.fxml;
    exports com.example.demo_test;
}