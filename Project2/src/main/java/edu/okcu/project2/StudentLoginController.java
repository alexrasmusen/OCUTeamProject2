package edu.okcu.project2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;

public class StudentLoginController extends LoginController {


    public void onLoginPress() {
        super.setFile("Students.txt");
        try {
            super.onSecondLoginButtonClick();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
