module com.example.numbersystems {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.numbersystems to javafx.fxml;
    exports com.project.numbersystems;
}