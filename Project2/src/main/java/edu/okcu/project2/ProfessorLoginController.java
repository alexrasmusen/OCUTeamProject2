package edu.okcu.project2;

import java.io.File;

public class ProfessorLoginController extends LoginController {

    public void onLoginPress() {
        super.setFile(new File("Professors.txt"));
    }

}
