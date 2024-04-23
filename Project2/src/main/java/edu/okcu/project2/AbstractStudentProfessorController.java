package edu.okcu.project2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The only reason this class exists is so I didn't have to copy and paste the sign-out click method. IntelliJ kept bugging me about it
 */
public abstract class AbstractStudentProfessorController {
    public void onSignoutButtonClick(Button btnSignout){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("welcome-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 400);
            stage.setScene(scene);
            Helper.setDarkTheme(scene);
            stage.show();

            Stage currentStage = (Stage) btnSignout.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
