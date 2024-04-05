package edu.okcu.project2;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ProfessorSignUpController extends SignUpController {

    public void onSignUpClick() throws IOException {
        super.setFile("Professors.txt");
        super.onSecondSignUpButtonClick();
    }

}
