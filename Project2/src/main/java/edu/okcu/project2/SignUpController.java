package edu.okcu.project2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.*;
import java.util.UUID;


public class SignUpController {

    @FXML
    private Label lblSignUp, lblName, lblEmail, lblPassword;
    @FXML
    private TextField txtfieldName, txtfieldEmail, txtfieldPassword;
    @FXML
    private Button buttonCancel;


    //Here are my variables
    String SignUpName;
    String SignUpEmail;
    String SignUpPassword;


    private String file = "";


    //Here is my method that controls what happens when the SignUp Button is cli
    public void onSecondSignUpButtonClick() {
        SignUpName = txtfieldName.getText();
        SignUpEmail = txtfieldEmail.getText();
        SignUpPassword = txtfieldPassword.getText();
        var hashedPassword = BCrypt.hashpw(SignUpPassword, BCrypt.gensalt(10));
        String uniqueID = UUID.randomUUID().toString();

        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(uniqueID + " , " + SignUpName + " , " + SignUpEmail + " , " + hashedPassword + "\n");

            onCancelButtonClick();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        }
    //This method closes the application if the cancel button is clicked
    public void onCancelButtonClick() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("welcome-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 400);
            stage.setScene(scene);
            Helper.setDarkTheme(scene);
            stage.show();

            Stage currentStage = (Stage) buttonCancel.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(String file) {
        this.file = file;
    }


}
