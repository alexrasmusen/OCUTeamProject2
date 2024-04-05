package edu.okcu.project2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StudentSignUpController extends SignUpController{

    public void onSignUpClick() throws IOException {
        super.setFile("Students.txt");
        super.onSecondSignUpButtonClick();
    }
}
