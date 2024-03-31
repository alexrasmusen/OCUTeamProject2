module edu.okcu.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;


    opens edu.okcu.project2 to javafx.fxml;
    exports edu.okcu.project2;
}