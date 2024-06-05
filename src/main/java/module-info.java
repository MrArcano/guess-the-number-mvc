module com.exercises.guessthenumbermvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exercises.guessthenumbermvc to javafx.fxml;
    exports com.exercises.guessthenumbermvc;
}