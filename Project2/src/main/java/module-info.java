module edu.okcu.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;
    requires com.google.gson;


    opens edu.okcu.project2 to javafx.fxml;
    exports edu.okcu.project2;
}