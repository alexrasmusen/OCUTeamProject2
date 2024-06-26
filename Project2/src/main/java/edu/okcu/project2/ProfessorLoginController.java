package edu.okcu.project2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class ProfessorLoginController extends LoginController {
    Professor professor;

    public void onLoginPress() {
        super.setFile("Professors.txt");
        try {
            if (super.onSecondLoginButtonClick()) {
                setID(super.getID());
                setName(super.getName());



                //initialize a new professor with the found name and ID
                professor = new Professor(getName(), getID());
                // we need an FXML loader. this will allow us to get the FXML file and the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("professor-view.fxml"));
                Parent root = loader.load();
                //open up the professor's view
                ProfessorTableController controller = loader.getController();
                controller.setProfessor(professor);
                Stage stage = new Stage();
                Scene scene = new Scene(root, 500, 450);
                Helper.setDarkTheme(scene);
                stage.setScene(scene);
               stage.show();

                //close login window
                super.closeCurrentWindow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
