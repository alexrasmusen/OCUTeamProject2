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


public class LoginController {

    @FXML
    private Label lblLogin, lblEmail, lblPassword;
    @FXML
    private TextField txtfieldEmail, txtfieldPassword;
    @FXML
    private Button buttonCancel;
    String LoginEmail;
    String LoginPassword;

    File file;

    public void login(Stage stage) throws IOException {

    }

    public void onSecondLoginButtonClick() throws FileNotFoundException {
        LoginEmail = txtfieldEmail.getText();
        LoginPassword = txtfieldPassword.getText();


        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            var row = fileScanner.nextLine();

            var items = row.split(" , ");
            var IDFromFile = items[0];
            var nameFromFile = items[1];
            var emailFromFile = items[2];
            var passwordFromFile = items[3];

            var decryptedPass = BCrypt.checkpw(LoginPassword, passwordFromFile);

            if (LoginEmail.equals(emailFromFile) && decryptedPass) {
                System.out.println("successfully logged in");
                break;
            }
            else if(!fileScanner.hasNext()){
                System.out.println("login failed");
            }
        }
    }

    public void setLblLogin(Label lblLogin) {
        this.lblLogin = lblLogin;
    }

    public void onSecondCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
        }

        public void setFile(String fileName) {
        file = new File(fileName);
        }
}


