module com.example.numbersystems {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.numbersystems to javafx.fxml;
    exports com.example.numbersystems;
}