package edu.okcu.project2;

public class StudentSignUpController extends SignUpController{

    public void onSignUpClick() {
        super.setFile("Students.txt");
    }
}
