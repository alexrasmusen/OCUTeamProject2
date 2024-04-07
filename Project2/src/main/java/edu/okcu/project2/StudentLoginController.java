package edu.okcu.project2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class StudentLoginController extends LoginController {

    Student student;


    public void onLoginPress() {
        super.setFile("Students.txt");
        try {
            if (super.onSecondLoginButtonClick()) {
                setID(super.getID());
                setName(super.getName());


                //initialize a new student with the found name and ID
                student = new Student(getName(), getID());
                // we need an FXML loader. this will allow us to get the FXML file and the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("student-view.fxml"));
                Parent root = loader.load();
                StudentTableController controller = loader.getController();
                controller.setStudent(student);
                Stage stage = new Stage();
                Scene scene = new Scene(root, 400, 400);
                Helper.setDarkTheme(scene);
                stage.setScene(scene);
                stage.show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent() {
        return this.student;
    }

}
