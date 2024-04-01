package edu.okcu.project2;

import java.io.File;

public class StudentLoginController extends LoginController {

    public void onLoginPress() {
        super.setFile(new File("Students.txt"));
    }

}
