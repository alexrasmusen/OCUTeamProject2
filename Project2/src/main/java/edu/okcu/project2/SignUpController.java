package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;


public class SignUpController {

    @FXML
    public Button buttonSignUp;
    @FXML
    private Label lblSignUp;
    @FXML
    private Label lblName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField txtfieldName;
    @FXML
    private TextField txtfieldEmail;
    @FXML
    private TextField txtfieldPassword;
    @FXML
    private Button buttonCancel;


    //Here are my variables
    String SignUpName;
    String SignUpEmail;
    String SignUpPassword;


    String file = "StudentSignUp.txt";

    //This is my method to open the SignUp FXML
    public void signUp(Stage stage) throws IOException {

    }

    //Here is my method that controls what happens when the SignUp Button is cli
    public void onSecondSignUpButtonClick(ActionEvent actionEvent) throws IOException {
        SignUpName = txtfieldName.getText();
        SignUpEmail = txtfieldEmail.getText();
        SignUpPassword = txtfieldPassword.getText();
        var hashedPassword = BCrypt.hashpw(SignUpPassword, BCrypt.gensalt(10));
        
        FileWriter fw = new FileWriter(file, true);
        fw.write(SignUpName);
        fw.write(" , ");
        fw.write(SignUpEmail);
        fw.write(" , ");
        fw.write(hashedPassword);
        fw.write("\n");

        fw.close();

        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        }
    //This method closes the application if the cancel button is clicked
    public void onCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

}
